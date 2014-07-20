package com.dalsps.net.xmpp.ext;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.PacketCollector;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.PacketIDFilter;
import org.jivesoftware.smack.packet.IQ;
import org.jivesoftware.smackx.ServiceDiscoveryManager;
import org.jivesoftware.smackx.packet.DiscoverInfo;

import android.util.Log;



public class QueryPacketHelper {
	
	public static final String TAG = "QueryPacketHelper";
    /**
     * Pings the given jid and returns the IQ response which is either of 
     * IQ.Type.ERROR or IQ.Type.RESULT. If we are not connected or if there was
     * no reply, null is returned.
     * 
     * You should use isPingSupported(jid) to determine if XMPP Ping is 
     * supported by the user.
     * 
     * @param jid
     * @param pingTimeout
     * @return
     */
    
    public static IQ query(String jid, long pingTimeout,XMPPConnection connection) {
    	
    	Log.i(TAG, "Request a query");
    	
    	// Make sure we actually connected to the server
        if (!connection.isAuthenticated())
            return null;
        
        QueryPacket packet = new QueryPacket(connection.getUser(), jid);
        
        PacketCollector collector =
                connection.createPacketCollector(new PacketIDFilter(packet.getPacketID()));
        
        connection.sendPacket(packet);
        
        IQ result = (IQ) collector.nextResult(pingTimeout);
        
        collector.cancel();
        
        Log.i(TAG,"Request a query result obj "+result != null ?result.toString():"null");
        
        return result;
    }
    
    /**
     * Pings the given jid and returns the IQ response with the default
     * packet reply timeout
     * 
     * @param jid
     * @return
     */
    public static IQ query(String jid,XMPPConnection connection) {
        return query(jid, SmackConfiguration.getPacketReplyTimeout(),connection);
    }
    
    /**
     * Pings the given Entity.
     * 
     * Note that XEP-199 shows that if we receive a error response
     * service-unavailable there is no way to determine if the response was send
     * by the entity (e.g. a user JID) or from a server in between. This is
     * intended behavior to avoid presence leaks.
     * 
     * Always use isPingSupported(jid) to determine if XMPP Ping is supported
     * by the entity.
     * 
     * @param jid
     * @return True if a pong was received, otherwise false
     */
    
    public static boolean queryEntity(String jid, long pingTimeout,XMPPConnection connection) {
    	
    	IQ result = query(jid, pingTimeout,connection);
        
        if (result == null 
                || result.getType() == IQ.Type.ERROR) {
            return false;
        } 
        return true;
    }
    
    public static boolean queryEntity(String jid,XMPPConnection connection) {
        return queryEntity(jid, SmackConfiguration.getPacketReplyTimeout(),connection);
    }
    
    
    
}
