package com.dalsps.net.xmpp.ext;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Packet;

import android.content.Context;
import android.util.Log;

import com.dalsps.net.MainService;
import com.dalsps.net.SettingsManager;



public class QueryPacketListener implements PacketListener {
        
		
		private static final String TAG = "Query Packet Listener";
		private SettingsManager mSettings;
        private Context mCtx;
        private XMPPConnection mConnection;
        
        public QueryPacketListener(XMPPConnection connection, Context ctx) {
            this.mConnection = connection;
            this.mCtx = ctx;
            this.mSettings = SettingsManager.getSettingsManager(ctx);
        }
        
        public void processPacket(Packet packet) {
            QueryPacket message = (QueryPacket) packet;
            String from = message.getFrom();
            Log.i(TAG, "XMPP packet received - but from address \"" + from.toLowerCase() +" "+message.toString());
            if (mSettings.getNotifiedAddress().equalsIgnoreCase(from) && !message.getFrom().equals(mConnection.getUser())) {
                Log.i(TAG, "XMPP packet received - sending Intent: " + MainService.ACTION_XMPP_MESSAGE_RECEIVED);                
                //Helper.startSvcXMPPMsg(mCtx, message.getBody(), from);
            } else {
                if (!mSettings.getNotifiedAddress().equalsIgnoreCase(from)) {
                    Log.i(TAG, "XMPP packet received - but from address \"" + from.toLowerCase() + "\" does not match notification address \"" 
                            +mSettings.getNotifiedAddress());
                } else if (message.getFrom().equals(mConnection.getUser())) {
                    Log.i(TAG, "XMPP packet received - but from the same user as the XMPP connection");
                }            }
        }
}
