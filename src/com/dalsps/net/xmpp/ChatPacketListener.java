package com.dalsps.net.xmpp;

import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.dalsps.net.MainService;
import com.dalsps.net.SettingsManager;
import com.dalsps.util.Helper;


public class ChatPacketListener implements PacketListener {
        
		
		private static final String TAG = "ChatPacketListener";
		private SettingsManager mSettings;
        private Context mCtx;
        private XMPPConnection mConnection;
        
        public ChatPacketListener(XMPPConnection connection, Context ctx) {
            this.mConnection = connection;
            this.mCtx = ctx;
            this.mSettings = SettingsManager.getSettingsManager(ctx);
        }
        
        public void processPacket(Packet packet) {
            Message message = (Message) packet;
            String from = message.getFrom();
            //Log.i(TAG,from);
            if (message.getBody() != null){
            	Log.i(TAG,message.getBody()+",recvtime-"+System.currentTimeMillis());
            	Helper.startSvcXMPPMsg(mCtx, message.getBody(), from);
            }
            /*if ( from != null && from.toLowerCase().startsWith(mSettings.getNotifiedAddress().toLowerCase()) && message.getBody() != null) {
                //Log.i(TAG, "XMPP packet received - sending Intent: " + MainService.ACTION_XMPP_MESSAGE_RECEIVED+" "+message.getBody());
            	Log.i(TAG,from);
            	Log.i(TAG,message.getBody()+",recvtime-"+System.currentTimeMillis());
               	Helper.startSvcXMPPMsg(mCtx, message.getBody(), from);                	                
            } else {
            	Log.i(TAG,from);
                if (!mSettings.getNotifiedAddress().equalsIgnoreCase(from)) {
                    Log.i(TAG, "XMPP packet received - but from address \"" + from.toLowerCase() + "\" does not match notification address \"" 
                            +mSettings.getNotifiedAddress());
                } else if (message.getFrom().equals(mConnection.getUser())) {
                    Log.i(TAG, "XMPP packet received - but from the same user as the XMPP connection");
                } else if (message.getBody() == null) {
                    Log.i(TAG, "XMPP Packet received - but without body (body == null)");
                }
            }*/
        }
}
