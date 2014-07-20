package com.dalsps.util;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.dalsps.net.MainService;
import com.dalsps.net.xmpp.XmppMsg;


public class Helper {
    
	public final static String TAG = "Helper";
    public final static String APP_NAME = "DalspsApplication";
    public final static String LineSep = System.getProperty("line.separator");
        
    
    /**
     * Sends an String via an service intent
     * 
     * @param msg
     * @param to destination jid, can be null
     * @param ctx
     * @return
     */
    public static boolean send(String msg, String to, Context ctx) {
        return send(new XmppMsg(msg), to, ctx);
    }
    
    /**
     * Sends an String via an service intent
     * 
     * @param msg
     * @param to destination jid, can be null
     * @param ctx
     * @return
     */
    public static boolean sendQuery(String msg, String to, Context ctx) {
    	 Intent intent = new Intent(MainService.ACTION_SEND_QUERY);
         intent.setClass(ctx, MainService.class);
         return MainService.sendToServiceHandler(intent);
    }
    
    /**
     * Sends a XMPP Message via an service intent
     * 
     * @param msg
     * @param to destination jid, can be null
     * @param ctx
     * @return
     */
    public static boolean send(XmppMsg msg, String to, Context ctx) {
        Intent intent = new Intent(MainService.ACTION_SEND);
        intent.setClass(ctx, MainService.class);
        if (to != null) {
            intent.putExtra("to", to);
        }
        intent.putExtra("xmppMsg", msg);
        return MainService.sendToServiceHandler(intent);
    }
    
    
    
	/**
     * Starts the service with the given action
     * 
     * @param ctx
     * @param action
     */
    public static void startSvcIntent(final Context ctx, final String action) {
        final Intent i = newSvcIntent(ctx, action, null, null);
        ctx.startService(i);
    }
    
    /**
     * Composes a new intent for the MainService
     * 
     * @param ctx
     * @param action
     * @param message the String extra "message", can be null
     * @param to the String extra "to", can be null for default notification address
     * @return
     */
    public static Intent newSvcIntent(final Context ctx, final String action, final String message, final String to) {
        final Intent i = new Intent(action, null, ctx, MainService.class);
        if (message != null) {
            i.putExtra("message", message);
        }
        if (to != null) {
            i.putExtra("to", to);
        }
        return i;
    }
    
    /**
     * Starts the Service with an XMPP Message Received intent
     * 
     * @param ctx
     * @param message
     * @param from
     */
    public static void startSvcXMPPMsg(final Context ctx, final String message, final String from) {
        final Intent i = new Intent(MainService.ACTION_XMPP_MESSAGE_RECEIVED, null, ctx, MainService.class);
        i.putExtra("message", message);
        i.putExtra("from", from);
        MainService.sendToServiceHandler(i);
    }
    
  
    public static void updateSentData(final String message) {
        final Intent i = new Intent(MainService.ACTION_VIEWSTATUS_SENTDATA);
        i.putExtra("message", message);
        MainService.sendToServiceHandler(i);
    }
    
    public static void latencyLog(String data)
    {
    	data = data.substring(data.indexOf("time-")+5);
    	long started = Long.parseLong(data);
    	long current = System.currentTimeMillis();
    	Log.i("Latency", String.valueOf(current - started ));
    	
    }
}
