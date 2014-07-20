package com.dalsps.net;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jivesoftware.smack.AndroidConnectionConfiguration;
import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionCreationListener;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.SmackAndroid;
//import org.jivesoftware.smack.SmackAndroid;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.StreamError;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.MultipleRecipientManager;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.XHTMLManager;
import org.jivesoftware.smackx.muc.MultiUserChat;
import org.jivesoftware.smackx.ping.PingFailedListener;
import org.jivesoftware.smackx.ping.PingManager;


import com.dalsps.QueryService;
import com.dalsps.net.xmpp.ChatPacketListener;
import com.dalsps.net.xmpp.XmppConnectionChangeListener;
import com.dalsps.net.xmpp.XmppMsg;
import com.dalsps.net.xmpp.XmppSocketFactory;
import com.dalsps.net.xmpp.ext.QueryPacket;
import com.dalsps.net.xmpp.ext.QueryPacketHelper;
import com.dalsps.net.xmpp.ext.QueryPacketListener;
import com.dalsps.net.xmpp.ext.QueryPacketProvider;
import com.dalsps.util.Helper;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.util.Log;


public class XmppManager {

    
	public static final String TAG = "XMPP Manager";
    // my first measuring showed that the disconnect in fact does not hang
    // but takes sometimes a lot of time
    // disconnectED xmpp connection. Took: 1048.576 s
    public static final int DISCON_TIMEOUT = 1000 * 10; // 10s
    // The timeout for XMPP connections that get created with
    // DNS SRV information
    public static final int DNSSRV_TIMEOUT = 1000 * 30; // 30s
    
    public static final int DISCONNECTED = 1;
    // A "transient" state - will only be CONNECTING *during* a call to start()
    public static final int CONNECTING = 2;
    public static final int CONNECTED = 3;
    // A "transient" state - will only be DISCONNECTING *during* a call to stop()
    public static final int DISCONNECTING = 4;
    // This state means we are waiting for a retry attempt etc.
    // mostly because a connection went down
    public static final int WAITING_TO_CONNECT = 5;
    // We are waiting for a valid data connection
    public static final int WAITING_FOR_NETWORK = 6;
    
    private static XmppManager sXmppManager = null;
    private static int sReusedConnectionCount = 0;
    private static int sNewConnectionCount = 0;
    
    // Indicates the current state of the service (disconnected/connecting/connected)
    private int mStatus = DISCONNECTED;
    
    private List<XmppConnectionChangeListener> mConnectionChangeListeners;
    private XMPPConnection mConnection = null;
    private PacketListener mPacketListener = null; 
    private ConnectionListener mConnectionListener = null;    
    private PacketListener mQueryPacketListener = null;
    private PingManager mPingManager = null;
        
        
    // Our current retry attempt, plus a runnable and handler to implement retry
    private int mCurrentRetryCount = 0;
    private Runnable mReconnectRunnable = new Runnable() {
        public void run() {
            Log.i(TAG,"attempting reconnection by issuing intent " + MainService.ACTION_CONNECT);
            Helper.startSvcIntent(mContext, MainService.ACTION_CONNECT);
        }
    };

    private Handler mReconnectHandler;

    private SettingsManager mSettings;
    private Context mContext;
    protected SmackAndroid mSmackAndroid;
    
    /**
     * Constructor for an XmppManager instance, connection is optional. It 
     * servers only a purpose when creating the instance with an already
     * connected connection, e.g. when registering new accounts with an server
     * and using this connection.
     * 
     * @param context - required
     * @param connection - optional
     */
    private XmppManager(Context context, XMPPConnection connection) {
        
        mSmackAndroid = SmackAndroid.init(context);
        
        mReconnectHandler = new Handler(MainService.getServiceLooper());
        
        mConnectionChangeListeners = new ArrayList<XmppConnectionChangeListener>();
        mSettings = SettingsManager.getSettingsManager(context);
        
        mContext = context;
        sReusedConnectionCount = 0;
        sNewConnectionCount = 0;
        
        ServiceDiscoveryManager.setIdentityName(Helper.APP_NAME);
        ServiceDiscoveryManager.setIdentityType("bot"); // http://xmpp.org/registrar/disco-categories.html
        
        // Smack Settings
        SmackConfiguration.setKeepAliveInterval(1000 * 60 * 12);  // 12 min
        SmackConfiguration.setPacketReplyTimeout(1000 * 40);      // 40 sec
        SmackConfiguration.setLocalSocks5ProxyEnabled(true);
        SmackConfiguration.setLocalSocks5ProxyPort(-7777);        // negative number means try next port if already in use
        
        Roster.setDefaultSubscriptionMode(Roster.SubscriptionMode.manual);
        // connection can be null, it is created on demand
        mConnection = connection;
    }
    
    /**
     * This getter creates the XmppManager and inits the XmppManager
     * with a new connection with the current preferences.
     * 
     * @param ctx
     * @return
     */
    public static XmppManager getInstance(Context ctx) {
        if (sXmppManager == null) {
            sXmppManager = new XmppManager(ctx, null);            
        }
        return sXmppManager;
    }
    
    private void start(int initialState) {
        switch (initialState) {
            case CONNECTED:
                initConnection();
                break;
            case WAITING_TO_CONNECT:
            case WAITING_FOR_NETWORK:
                updateStatus(initialState);
                break;
            default:
                throw new IllegalStateException("xmppMgr start() Invalid State: " + initialState);
        }
    }
    
    /**
     * calls cleanupConnection and 
     * sets _status to DISCONNECTED
     */
    private void stop() {
        updateStatus(DISCONNECTING);
        cleanupConnection();
        updateStatus(DISCONNECTED);
        mConnection = null;
    }
    
    /**
     * Removes all references to the old connection.
     * 
     * Spawns a new disconnect runnable if the connection
     * is still connected and removes packetListeners and 
     * Callbacks for the reconnectHandler.
     * 
     * synchronized because cleanupConnection() ->
     * maybeStartReconnect() -> connectionClosedOnError()
     * is called from a different thread
     */
    private synchronized void cleanupConnection() {
        mReconnectHandler.removeCallbacks(mReconnectRunnable);

        if (mConnection != null) {
            if (mPacketListener != null) {
                mConnection.removePacketListener(mPacketListener);
            }
            if (mQueryPacketListener != null) {
                mConnection.removePacketListener(mQueryPacketListener);
            }
            if (mConnectionListener != null) {
                mConnection.removeConnectionListener(mConnectionListener);
            }
            
            
            if (mConnection.isConnected()) {
                // Try to disconnect
                Thread t = new Thread(new Runnable() {
                    public void run() {
                        try {
                            mConnection.disconnect();
                        } catch (Exception e) {}
                    }
                }, "xmpp-disconnector");
                // we don't want this thread to hold up process shutdown so mark as daemon.
                t.setDaemon(true);
                t.start();

                try {
                    t.join(DISCON_TIMEOUT);
                } catch (InterruptedException e) {
                    mConnection = null;
                    mPingManager = null;
                }
            }
        }
        mPacketListener = null; 
        mQueryPacketListener = null;
        mConnectionListener = null;
    }
    
    /** 
     * This method *requests* a state change - what state things actually
     * wind up in is impossible to know (eg, a request to connect may wind up
     * with a state of CONNECTED, DISCONNECTED or WAITING_TO_CONNECT...
     */
    public void xmppRequestStateChange(int newState) {
        int currentState = getConnectionStatus();
        Log.i(TAG,"xmppRequestStateChange " + statusAsString(currentState) + " => " + statusAsString(newState));
        switch (newState) {
        case XmppManager.CONNECTED:
            if (!isXmppConnected()) {
                cleanupConnection();
                start(XmppManager.CONNECTED);
            }
            break;
        case XmppManager.DISCONNECTED:
            stop();
            break;
        case XmppManager.WAITING_TO_CONNECT:
            cleanupConnection();
            start(XmppManager.WAITING_TO_CONNECT);
            break;
        case XmppManager.WAITING_FOR_NETWORK:
            cleanupConnection();
            start(XmppManager.WAITING_FOR_NETWORK);
            break;
        default:
            Log.w(TAG,"xmppRequestStateChange() invalid state to switch to: " + statusAsString(newState));
        }
        // Now we have requested a new state, our state receiver will see when
        // the state actually changes and update everything accordingly.
    }

    /**
     * Updates the status about the service state (and the statusbar)
     * by sending an ACTION_XMPP_CONNECTION_CHANGED intent with the new
     * and old state.
     * needs to be static, because its called by MainService even when
     * xmppMgr is not created yet
     * 
     * @param ctx
     * @param old_state
     * @param new_state
     */
    public static void broadcastStatus(Context ctx, int old_state, int new_state) {  
        Intent intent = new Intent(MainService.ACTION_XMPP_CONNECTION_CHANGED);                      
        intent.putExtra("old_state", old_state);
        intent.putExtra("new_state", new_state);
        if (new_state == CONNECTED && sXmppManager != null && sXmppManager.mConnection != null) {
            intent.putExtra("TLS", sXmppManager.mConnection.isUsingTLS());
            intent.putExtra("Compression", sXmppManager.mConnection.isUsingCompression());
        }
        ctx.sendBroadcast(intent);
    }
    
    /**
     * updates the connection status
     * and calls broadCastStatus()
     * 
     * @param status
     */
    private void updateStatus(int status) {
        if (status != mStatus) {
            // ensure _status is set before broadcast, just in-case
            // a receiver happens to wind up querying the state on
            // delivery.
            int old = mStatus;
            mStatus = status;     
            Log.i(TAG,"broadcasting state transition from " + statusAsString(old) + " to " + statusAsString(status) + " via Intent " + MainService.ACTION_XMPP_CONNECTION_CHANGED);
            broadcastStatus(mContext, old, status);
        }
    }

    private void maybeStartReconnect() {
            int timeout;
            updateStatus(WAITING_TO_CONNECT);
            cleanupConnection();
            mCurrentRetryCount += 1;
            if (mCurrentRetryCount < 5) {
                // a simple linear-backoff strategy.
                //timeout = 5000 * mCurrentRetryCount;
            	timeout = 5000;
            } else {
            	timeout = 5000;
                // every 5 min
                //timeout = 1000 * 60 * 5;
            }
            Log.i(TAG,"maybeStartReconnect scheduling retry in " + timeout + "ms. Retry #" + mCurrentRetryCount);
            mReconnectHandler.postDelayed(mReconnectRunnable, timeout);
    }
    

    /**
     * Initializes the XMPP connection
     * 
     * 1. Creates a new XMPPConnection object if necessary
     * 2. Connects the XMPPConnection
     * 3. Authenticates the user with the server
     * 
     * Calls maybeStartReconnect() if something went wrong
     * 
     */
    private void initConnection() {
        XMPPConnection connection;

        // assert we are only ever called from one thread
        assert (!Thread.currentThread().getName().equals(MainService.SERVICE_THREAD_NAME));

        // everything is ready for a connection attempt
        updateStatus(CONNECTING);

        // create a new connection if the connection is obsolete or if the
        // old connection is still active
        if (SettingsManager.connectionSettingsObsolete 
                || mConnection == null 
                || mConnection.isConnected() ) {
            
            try {
                connection = createNewConnection(mSettings);
            } catch (Exception e) {
                // connection failure
                Log.e(TAG,"Exception creating new XMPP Connection", e);
                maybeStartReconnect();
                return;
            }
            SettingsManager.connectionSettingsObsolete = false;
            if (!connectAndAuth(connection)) {
                // connection failure
                return;
            }                  
            sNewConnectionCount++;
        } else {
            // reuse the old connection settings
            connection = mConnection;
            // we reuse the xmpp connection so only connect() is needed
            if (!connectAndAuth(connection)) {
                // connection failure
                return;
            }
            sReusedConnectionCount++;
        }
        // this code is only executed if we have an connection established
        onConnectionEstablished(connection);
    }
    
    private void onConnectionEstablished(XMPPConnection connection) {
        mConnection = connection;               
        mConnectionListener = new ConnectionListener() {
            @Override
            public void connectionClosed() {
                // connection was closed by the foreign host
                // or we have closed the connection
                Log.i(TAG,"ConnectionListener: connectionClosed() called - connection was shutdown by foreign host or by us");
                xmppRequestStateChange(getConnectionStatus());
            }

            @Override
            public void connectionClosedOnError(Exception e) {
                // this happens mainly because of on IOException
                // eg. connection timeouts because of lost connectivity
                Log.w("xmpp disconnected due to error: ", e);
                if (e.getMessage().startsWith("Attr.value missing")) {
                    Log.w(TAG,(android.util.Log.getStackTraceString(e)));
                }
                // We update the state to disconnected (mainly to cleanup listeners etc)
                // then schedule an automatic reconnect.
                maybeStartReconnect();
            }

            @Override
            public void reconnectingIn(int arg0) {
                throw new IllegalStateException("Reconnection Manager is running");
            }

            @Override
            public void reconnectionFailed(Exception arg0) {
                throw new IllegalStateException("Reconnection Manager is running");
            }

            @Override
            public void reconnectionSuccessful() {
                throw new IllegalStateException("Reconnection Manager is running");
            }
        };
        mConnection.addConnectionListener(mConnectionListener);            

        try {
            informListeners(mConnection);

            PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
            mPacketListener = new ChatPacketListener(mConnection, mContext);
            mConnection.addPacketListener(mPacketListener, filter);
            
            PacketFilter queryPacketfilter = new PacketTypeFilter(QueryPacket.class);
            mQueryPacketListener = new QueryPacketListener(mConnection, mContext);
            mConnection.addPacketListener(mQueryPacketListener, queryPacketfilter);
            
        } catch (Exception e) {
            // see issue 126 for an example where this happens because
            // the connection drops while we are in initConnection()
            Log.e(TAG,"xmppMgr exception caught", e);
            maybeStartReconnect();
            return;
        }

        Log.i(TAG,"connection established with parameters: con=" + mConnection.isConnected() + 
                " auth=" + mConnection.isAuthenticated() + 
                " enc=" + mConnection.isUsingTLS() + 
                " comp=" + mConnection.isUsingCompression());
        
      
        mCurrentRetryCount = 0;
        updateStatus(CONNECTED);
    }
    
    
    private void informListeners(XMPPConnection connection) {
        for (XmppConnectionChangeListener listener : mConnectionChangeListeners) {
            listener.newConnection(connection);
        }
    }
    
    /**
     * Tries to fully establish the given XMPPConnection
     * Calls maybeStartReconnect() or stop() in an error case
     * 
     * @param connection
     * @return true if we are connected and authenticated, false otherwise
     */
    private boolean connectAndAuth(XMPPConnection connection) {
        try {
            connection.connect();
        } catch (Exception e) {
            Log.w("XMPP connection failed", e);
            if (e instanceof XMPPException) {
                XMPPException xmppEx = (XMPPException) e;
                StreamError error = xmppEx.getStreamError();
                // Make sure the error is not null
                if (error != null) {
                    Log.w(TAG,"XMPP connection failed because of stream error: " + error.toString());
                }
            }
            maybeStartReconnect();
            mConnection = null;
            return false;
        }          
        
        // if we reuse the connection and the auth was done with the connect()
        if (connection.isAuthenticated()) {
            return true;
        }
        
        ServiceDiscoveryManager serviceDiscoMgr = ServiceDiscoveryManager.getInstanceFor(connection);
        mPingManager = PingManager.getInstaceFor(connection);
        mPingManager.registerPingFailedListener(new PingFailedListener() {
            
            @Override
            public void pingFailed() {
                // TODO remember that maybeStartReconnect is called from a 
                // different thread (the PingTask) here, it may causes 
                // synchronization problems
                Log.d(TAG,"PingManager reported failed ping, calling maybeStartReconnect()");
                maybeStartReconnect();
                
            }
        });
        
        XHTMLManager.setServiceEnabled(connection, false);   
        serviceDiscoMgr.addFeature("http://jabber.org/protocol/disco#info");
        serviceDiscoMgr.addFeature("bug-fix-dalspsapplication");
        
        
        try {
            connection.login(mSettings.getLogin(), mSettings.getPassword(), Helper.APP_NAME);
            //Presence presence = new Presence(Presence.Type.available);
            //connection.sendPacket(presence);
        } catch (Exception e) {
        	
            cleanupConnection();
            
            Log.e(TAG,"xmpp login failed: " + e.getMessage());
            // sadly, smack throws the same generic XMPPException for network
            // related messages (eg "no response from the server") as for
            // authoritative login errors (ie, bad password).  The only
            // differentiator is the message itself which starts with this
            // hard-coded string.
            if (e.getMessage() == null || e.getMessage().indexOf("SASL authentication") == -1) {
                // doesn't look like a bad username/password, so retry
                maybeStartReconnect();
            } else {
                
                stop();
            }
            return false;
        }
        return true;
    }    
    
    /**
     * Parses the current preferences and returns an new unconnected
     * XMPPConnection 
     * @return
     * @throws XMPPException 
     */
    private static XMPPConnection createNewConnection(SettingsManager settings) throws XMPPException {
        ConnectionConfiguration conf;
        
        
        // trim the serverHost here because of Issue 122
        conf = new ConnectionConfiguration(settings.serverHost.trim(), settings.serverPort, settings.serviceName);
       
        conf.setSocketFactory(new XmppSocketFactory());
        /*	
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            conf.setTruststoreType("AndroidCAStore");
            conf.setTruststorePassword(null);
            conf.setTruststorePath(null);
        } else {
            conf.setTruststoreType("BKS");
            String path = System.getProperty("javax.net.ssl.trustStore");
            if (path == null) {
                path = System.getProperty("java.home") + File.separator + "etc"
                    + File.separator + "security" + File.separator
                    + "cacerts.bks";
            }
            conf.setTruststorePath(path);
        }

        conf.setSecurityMode(ConnectionConfiguration.SecurityMode.enabled);
        // disable the built-in ReconnectionManager since we handle this
        conf.setReconnectionAllowed(false);
        */
        conf.setSendPresence(true);
        XMPPConnection connection = new XMPPConnection(conf);
        return connection;
    }

    /** returns the current connection state */
    public int getConnectionStatus() {
        return mStatus;
    }
    
    public boolean getTLSStatus() {
        return mConnection == null ? false : mConnection.isUsingTLS();
    }
    
    public boolean getCompressionStatus() {
        return mConnection == null ? false : mConnection.isUsingCompression();
    }
    
    /**
     * Sends a XMPP Message, but only if we are connected
     * This method is thread safe.
     * 
     * @param message
     * @param to - the receiving JID - if null the default notification address will be used
     * @return true, if we were connected and the message was handled over to the connection - otherwise false
     */
    public boolean send(XmppMsg message, String to) {
        
    	if (message.toString().startsWith("query") || message.toString().startsWith("stop")){
    		mSettings.setTo(null);	
    		
    		//stop sql service
    		final Intent startService = new Intent(QueryService.ACTION_QUERY_STOP);
			MainService.sendToServiceHandler(startService);
    		
    	}	
    	if (to == null) {
            Log.i(TAG,"Sending message \"" + message.toString() + "\"");
           
            if (mSettings.getTo() != null)
            	to = mSettings.getTo() ;
            else
            	to = mSettings.getNotifiedAddress();
            
        } else {
            Log.i(TAG,"Sending message \"" + message.toString() + "\" to " + to);
        }
        Message msg = null;
        

        // to is null, so send to the default, which is the notifiedAddress
        if (to == null || to.trim().length() == 0) {
        	if (mConnection != null && mConnection.isConnected()){
        		Collection<RosterEntry> list = mConnection.getRoster().getEntries();
        		for(RosterEntry re : list){        			
        			msg = new Message(re.getUser());
        			if (mConnection.isConnected())
        				msg.setBody(setNode(message.generateTxt()));
        				//determine the type of the message, groupchat or chat
        				msg.setType(Message.Type.chat);
        				msg.setFrom(mConnection.getUser());
        				mConnection.sendPacket(msg);	
        		}
        		return true;
        	}
        } else {
            msg = new Message(to);
            
            // determine the type of the message, groupchat or chat
            msg.setType(Message.Type.chat);
            
            if (isConnected()) {
            	msg.setBody(setNode(message.generateTxt()));
            	mConnection.sendPacket(msg);
                return true;
            }
        }

       
        return false;
     }
    
     String setNode(String msg){
    	 if (msg !=null && msg.startsWith("result")){
    		 Helper.updateSentData(msg);
    		 msg = msg+", node - "+ mConnection.getUser().substring(0, mConnection.getUser().indexOf("@"))+",sendtime-"+System.currentTimeMillis();
    	 }	 
    	 return msg;
     }
     public boolean sendQuery() {
        
        if (isConnected()) {
            return QueryPacketHelper.queryEntity(mSettings.getNotifiedAddress(), this.mConnection);
            
        }
        return false;
     }

    public boolean isConnected() {
        return isXmppConnected() && mStatus == CONNECTED;
    }
    
    public boolean isXmppConnected() {
        return mConnection != null && mConnection.isConnected();
    }
    
   
    public void registerConnectionChangeListener(XmppConnectionChangeListener listener) {
        mConnectionChangeListeners.add(listener);
    }
    
    public static int getNewConnectionCount() {
        return sNewConnectionCount;
    }
    
    public static int getReusedConnectionCount() {
        return sReusedConnectionCount;
    }

    public static String statusAsString(int state) {
        String res = "??";
        switch(state) {
        case DISCONNECTED:
            res = "Disconnected";
            break;
        case CONNECTING:
            res = "Connecting";
            break;
        case CONNECTED:
            res = "Connected";
            break;
        case DISCONNECTING:
            res = "Disconnecting";
            break;
        case WAITING_TO_CONNECT:
            res = "Waiting to connect";
            break;
        case WAITING_FOR_NETWORK:
            res = "Waiting for network";
            break;
        }
        return res;                        
    }
    
    public String statusString() {
        return statusAsString(mStatus);
    }    
    
    
    public PingManager getPingManger() {
        return mPingManager;
    }
    
}