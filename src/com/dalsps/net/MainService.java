package com.dalsps.net;


import java.util.ArrayList;
import java.util.Hashtable;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.ping.PingManager;

import com.dalsps.MainActivity;
import com.dalsps.QueryService;
import com.dalsps.net.xmpp.XmppMsg;
import com.dalsps.util.Helper;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Binder;
import android.os.Build;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;


public class MainService  extends Service {
    
	public final static int ID = 1;
    
    public final static String TAG = "MainService";
    public final static String SERVICE_THREAD_NAME = "dalsps_mainservice";  
    
    public final static String ACTION_CONNECT = "com.dalsps.action.CONNECT";
	public final static String ACTION_DISCONNECT = "com.dalsps.action.DISCONNECT";
    public static final String ACTION_SEND = "com.dalsps.action.SEND";
    public static final String ACTION_SEND_QUERY = "com.dalsps.action.SEND_QUERY";
    
    public final static String ACTION_NETWORK_CHANGED = "com.dalsps.action.NETWORK_CHANGED";
    public final static String ACTION_BROADCAST_STATUS = "com.dalsps.action.BROADCAST_STATUS";
    
	// A list of intent actions that the XmppManager broadcasts.
    public static final String ACTION_XMPP_MESSAGE_RECEIVED = "com.dalsps.action.XMPP.MESSAGE_RECEIVED";
    public static final String ACTION_XMPP_PRESENCE_CHANGED = "com.dalsps.action.XMPP.PRESENCE_CHANGED";
    public static final String ACTION_XMPP_CONNECTION_CHANGED = "com.dalsps.action.XMPP.CONNECTION_CHANGED";
   
   
    //activity update
    public static final String ACTION_VIEWSTATUS_MESSAGE = "com.dalsps.viewstatus.MESSAGE";
    public static final String ACTION_VIEWSTATUS_RESULT = "com.dalsps.viewstatus.RESULT";
    public static final String ACTION_VIEWSTATUS_SENTDATA = "com.dalsps.viewstatus.SENTDATA";
    public static final String ACTION_VIEWSTATUS_OPEN_RESULT_VIEW = "com.dalsps.viewstatus.OPENRESULT";
    public static final String ACTION_VIEWSTATUS_OPEN_QUERY_VIEW = "com.dalsps.viewstatus.OPENQUERY";
    
    
    public static final String ACTION_WIDGET_ACTION ="com.dalsps.action.widget.ACTION";
    //fire intents
    public static final String ACTION_FIRE_INTENT = "com.dalsps.action.FIRE_INTENT";
    // A bit of a hack to allow global receivers to know whether or not
    // the service is running, and therefore whether to tell the service
    // about some events
    public static boolean IsRunning = false;

    private static boolean sListenersActive = false;
    
    private static SettingsManager sSettingsMgr;
    private static XmppManager sXmppMgr;
    private static BroadcastReceiver sXmppConChangedReceiver;
    private static PowerManager sPm;
    private static PowerManager.WakeLock sWl;
    private static PendingIntent sContentIntent = null;
    
    // This is the object that receives interactions from clients. See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();
    
    private long mHandlerThreadId;

    private static Context sUiContext;
    
    // some stuff for the async service implementation - borrowed heavily from
    // the standard IntentService, but that class doesn't offer fine enough
    // control for "foreground" services.
    private static volatile Looper sServiceLooper;
    private static volatile ServiceHandler sServiceHandler;

	private static Hashtable intentMapping = new Hashtable();
	
	static {
		intentMapping.put("display", "com.dalsps.action.Display"); 
	}
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            onHandleIntent((Intent) msg.obj, msg.arg1);
        }
    }

 // TODO move the following method into the subclass above ?
    /**
     * The IntentService(-like) implementation manages taking the intents passed
     * to startService and delivering them to this function which runs in its
     * own thread (so can block Pretty-much everything using the _xmppMgr is
     * here...
     * 
     * ACTION_XMPP_CONNECTION_CHANGED is handled implicitly, by every call of
     * this method.
     * 
     * @param intent
     * @param id
     */
    protected void onHandleIntent(final Intent intent, int id) {
        
    	// ensure XMPP manager is setup (but not yet connected)
        if (sXmppMgr == null)
            setupXmppManager();

        // Set Disconnected state by force to manage pending tasks
        // This is not actively used any more
        if (intent.getBooleanExtra("force", false) && intent.getBooleanExtra("disconnect", false)) {
            // request to disconnect.
            sXmppMgr.xmppRequestStateChange(XmppManager.DISCONNECTED);
        }

        if (Thread.currentThread().getId() != mHandlerThreadId) {
            throw new IllegalThreadStateException();
        }

        String action = intent.getAction();
        int initialState = getConnectionStatus();
        Log.i(TAG,"handling action '" + action + "' while in state " + XmppManager.statusAsString(initialState));

        // Start with handling the actions the could result in a change
        // of the connection status
        if (action.equals(ACTION_CONNECT)) {
            if (intent.getBooleanExtra("disconnect", false)) {
                // Request to disconnect. We will stop the service if
                // we are in "DISCONNECTED" state at the end of the method
                sXmppMgr.xmppRequestStateChange(XmppManager.DISCONNECTED);
            } else {
            	sWl.acquire();
            	// A simple 'connect' request.
                sXmppMgr.xmppRequestStateChange(XmppManager.CONNECTED);
                sWl.release();
            }
        } else if (action.equals(ACTION_DISCONNECT)) {
            sXmppMgr.xmppRequestStateChange(XmppManager.DISCONNECTED);
        } else if (action.equals(ACTION_NETWORK_CHANGED)) {
            boolean available = intent.getBooleanExtra("available", true);
            boolean failover = intent.getBooleanExtra("failover", false);
            Log.i(TAG,"network_changed with available=" + available + ", failover=" + failover + " and when in state: " + XmppManager.statusAsString(initialState));
            // We are in a waiting state and have a network - try to connect.
            if (available && (initialState == XmppManager.WAITING_TO_CONNECT || initialState == XmppManager.WAITING_FOR_NETWORK)) {
                sXmppMgr.xmppRequestStateChange(XmppManager.CONNECTED);
            } else if (!available && !failover && initialState == XmppManager.CONNECTED) {
                // We are connected but the network has gone down - disconnect
                // and go into WAITING state so we auto-connect when we get a future
                // notification that a network is available.
                sXmppMgr.xmppRequestStateChange(XmppManager.WAITING_FOR_NETWORK);
            }
        }

        
        if (action.equals(ACTION_SEND)) {
        	sWl.acquire();
        	XmppMsg xmppMsg = (XmppMsg) intent.getParcelableExtra("xmppMsg");
            if (xmppMsg == null) {
                xmppMsg = new XmppMsg(intent.getStringExtra("message")+",node = "+sSettingsMgr.getLogin());
            }
            
            sXmppMgr.send(xmppMsg, intent.getStringExtra("to"));
            sWl.release();
        } 
        else if(action.equals(ACTION_SEND_QUERY)){
        	sXmppMgr.sendQuery();
        }
        else if (action.equals(ACTION_XMPP_MESSAGE_RECEIVED)) {
            sWl.acquire();
            String message = intent.getStringExtra("message");
            
            if (message != null) {
                handleCommandFromXMPP(message, intent.getStringExtra("from"));
            }
            sWl.release();
        } 
        else if(action.equals(ACTION_VIEWSTATUS_SENTDATA)){
        	sWl.acquire();
            String message = intent.getStringExtra("message");
            
            if (message != null) {
            	Intent intent3 = new Intent(ACTION_VIEWSTATUS_RESULT);
   			 	intent3.putExtra("result", (message+" - sent data"));
   			 	sendBroadcast(intent3);
            }
            sWl.release();
        }
        else if(action.equals(ACTION_FIRE_INTENT)){
        	ArrayList<String> intentsList = intent.getStringArrayListExtra("intent_list");
        	for (String string : intentsList) {
        		//Object act = intentMapping.get(string);
        		//if (act !=null){
        			//Intent i = new Intent(act.toString());
        			//startService(i);
        			Intent i2 = new Intent(MainService.ACTION_VIEWSTATUS_MESSAGE);
        			i2.putExtra("message", "Following action was called '"+string);
					sendBroadcast(i2);
        		//}
			}        	
        }
        // ACTION_XMPP_CONNECTION_CHANGED is handled implicitly by every call
         else if (!action.equals(ACTION_XMPP_CONNECTION_CHANGED)
                && !action.equals(ACTION_CONNECT)
                && !action.equals(ACTION_DISCONNECT)
                && !action.equals(ACTION_NETWORK_CHANGED)) {
            Log.w(TAG,"Unexpected intent: " + action);
        }
        Log.i(TAG,"handled action '" + action + "' - state now: " + sXmppMgr.statusString());

        // stop the service if we are disconnected (but stopping the service
        // doesn't mean the process is terminated - onStart can still happen.)
        if (getConnectionStatus() == XmppManager.DISCONNECTED) {
        	sServiceHandler.removeMessages(id);
        	if (stopSelfResult(id)) {
                Log.i(TAG,"service is stopping because we are disconnected and no pending intents exist");
            } else {
                
            	Log.i(TAG,"we are disconnected, but more pending intents to be delivered - service will not stop");
            }
        }
    }

    public int getConnectionStatus() {
        return sXmppMgr == null ? XmppManager.DISCONNECTED : sXmppMgr.getConnectionStatus();
    }
    
  
    /**
     * Class for clients to access. Because we know this service always runs in
     * the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public MainService getService() {
            return MainService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
	
	 public static boolean sendToServiceHandler(int i, Intent intent) {
	        if (sServiceHandler != null) {
	            Message msg = sServiceHandler.obtainMessage();
	            msg.arg1 = i;
	            msg.obj = intent;
	            sServiceHandler.sendMessage(msg);
	            return true;
	        } else {
	            Log.i(TAG,"sendToServiceHandler() called with " 
	                    + intent.getAction() 
	                    + " when service handler is null");
	            return false;
	        }
	    }
	 
	  public static boolean sendToServiceHandler(Intent intent) {
	        return sendToServiceHandler(0, intent);
	  }
	  
	  @Override
	  public void onCreate() 
	  {
        super.onCreate();
        sPm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        sWl = sPm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, Helper.APP_NAME + " WakeLock");

        sSettingsMgr = SettingsManager.getSettingsManager(this);

        // Start a new thread for the service
        HandlerThread thread = new HandlerThread(SERVICE_THREAD_NAME);
        thread.start();
        mHandlerThreadId = thread.getId();
        sServiceLooper = thread.getLooper();
        sServiceHandler = new ServiceHandler(sServiceLooper);
        
        sUiContext = this;

        sContentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

        Log.i(TAG,"onCreate(): service thread created - IsRunning is set to true");
        IsRunning = true;

        // it seems that with gingerbread android doesn't issue null intents any
        // more when restarting a service but only calls the service's onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            int currentStatus = (sXmppMgr == null) ? XmppManager.DISCONNECTED : sXmppMgr.getConnectionStatus();
            if (currentStatus != XmppManager.DISCONNECTING) {
                Log.i(TAG,"onCreate(): issuing connect intent because we are on gingerbread (or higher).  and currentStatus is " + currentStatus);
                startService(new Intent(MainService.ACTION_CONNECT));
               
            }
        }
        
	}
	  @Override
    public int onStartCommand(Intent intent, int flags, int startId) 
	  {
        if (intent == null) {
            // The application has been killed by Android and
            // we try to restart the connection
            // this null intent behavior is only for SDK < 9
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
                startService(new Intent(MainService.ACTION_CONNECT));
            } else {
                Log.w(TAG,"onStartCommand() null intent with Gingerbread or higher");
            }
            return START_STICKY;
        }
        Log.i(TAG,"onStartCommand(): Intent " + intent.getAction());
        // A special case for the 'broadcast status' intent - we avoid setting
        // up the _xmppMgr etc
        if (intent.getAction().equals(ACTION_BROADCAST_STATUS)) {
            // A request to broadcast our current status even if _xmpp is null.
            int state = getConnectionStatus();
            XmppManager.broadcastStatus(this, state, state);
            // A real action request
        } else {
            // redirect the intent to the service handler thread
            sendToServiceHandler(startId, intent);
        }
        return START_STICKY;
    }

	    /**
	     * Does an initial one-time setup on the MainService by - Creating a
	     * XmppManager Instance - Registering the commands - Registering a Listener
	     * for ACTION_XMPP_CONNECTION_CHANGED which is issued by XmppManager for
	     * every state change of the XMPP connection
	     */
	    private void setupXmppManager() {
	        sXmppConChangedReceiver = new BroadcastReceiver() {
	            public void onReceive(Context context, Intent intent) {
	                intent.setClass(MainService.this, MainService.class);
	                startService(intent);
	            }
	        };
	        IntentFilter intentFilter = new IntentFilter(ACTION_XMPP_CONNECTION_CHANGED);
	        registerReceiver(sXmppConChangedReceiver, intentFilter);
	        sXmppMgr = XmppManager.getInstance(this);
	    }

	    @Override
	    public void onDestroy() {
	        Log.i(TAG,"MainService onDestroy(): IsRunning is set to false");
	        	        
	        IsRunning = false;
	        // If the _xmppManager is non-null, then our service was "started" (as
	        // opposed to simply "created" - so tell the user it has stopped.
	        if (sXmppMgr != null) {
	            // do some cleanup
	            unregisterReceiver(sXmppConChangedReceiver);
	            sXmppConChangedReceiver = null;

	            sXmppMgr.xmppRequestStateChange(XmppManager.DISCONNECTED);
	            //sXmppMgr.mSmackAndroid.exit();
	            sXmppMgr = null;
	        }
	        teardownListenersForConnection();
	        // All data must be cleaned, because onDestroy can be call without releasing the current object
	        // It's due to BIND_AUTO_CREATE used for Service Binder
	        // http://developer.android.com/reference/android/content/Context.html#stopService(android.content.Intent)
	       
	        sServiceLooper.quit();

	        super.onDestroy();
	        Log.i(TAG,"MainService onDestroy(): service destroyed");
	    }

	    private void teardownListenersForConnection() {
	        Log.i(TAG,"teardownListenersForConnection()");
	        stopForeground(true);
	        
	    }
		public static Looper getServiceLooper() {
			// TODO Auto-generated method stub
			return sServiceLooper;
		}

		 public PingManager getPingManager() {
		        return sXmppMgr == null ? null : sXmppMgr.getPingManger();
		   }

		
		private void handleCommandFromXMPP(String message, String from)
		{
			
			Log.i(TAG,"Command received "+message);
			String command = null;
			String data = null;
			
			if (message != null && message.indexOf(":")> -1){
				command = message.substring(0,message.indexOf(":"));
				data = message.substring(message.indexOf(":")+1,message.length());
			}
			if (command == null) {
				Helper.send("exception: invalid command", null, this);
			}
			else{
				 if (command.equals("query")){
					 					
					Intent startService = new Intent(QueryService.ACTION_QUERY_START);
					startService.putExtra("query",data);
					sSettingsMgr.setTo(from);
					startService(startService);
					Intent intent = new Intent(MainService.ACTION_VIEWSTATUS_OPEN_RESULT_VIEW);
					sendBroadcast(intent);
				}
				else if(command.equals("stop")){					
					sSettingsMgr.setTo(null);
					Intent startService = new Intent(QueryService.ACTION_QUERY_STOP);
					startService(startService);
					Intent intent = new Intent(MainService.ACTION_VIEWSTATUS_OPEN_QUERY_VIEW);
					sendBroadcast(intent);
				}
				else if (command.equals("display") || command.equals("exception")){
					Intent intent = new Intent(MainService.ACTION_VIEWSTATUS_MESSAGE);
					intent.putExtra("message", data);
					sendBroadcast(intent);
				}
				else if (command.equals("result")){
					//Helper.latencyLog(data);
					Intent intent = new Intent(MainService.ACTION_VIEWSTATUS_RESULT);
					intent.putExtra("result", data);
					sendBroadcast(intent);
					
				}
				else{
					Helper.send("exception: invalid command", null, this);
				}
			
		 }	
				
		}
}
