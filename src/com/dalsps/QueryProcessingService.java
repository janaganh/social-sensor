package com.dalsps;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.os.PowerManager;
import android.util.Log;

import com.dalsps.net.MainService;
import com.dalsps.net.SettingsManager;
import com.dalsps.net.xmpp.XmppMsg;
import com.dalsps.query.SelectLexer;
import com.dalsps.query.SelectNode;
import com.dalsps.query.SelectParser;
import com.dalsps.query.SelectWalker;
import com.dalsps.util.Helper;



public class QueryProcessingService extends IntentService {
	
	private static PowerManager sPm;
	private static PowerManager.WakeLock sWl;
	
	public static final String ACTION_PROCESS_QUERY_START = "com.dalsps.action.PROCESS_QUERY_START";
	public static final String ACTION_PROCESS_QUERY_STOP = "com.dalsps.action.PROCESS_QUERY_STOP";
	
	private static final String TAG = "QueryProcessingService";
	private String msg = null;
	
	public QueryProcessingService() {
		super("QueryService");	
	}

    @Override
	public void onCreate() 
	{
    	msg ="oncreate-time-"+System.currentTimeMillis();
    	super.onCreate();
  		sPm = (PowerManager) getSystemService(Context.POWER_SERVICE);
  		sWl = sPm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, Helper.APP_NAME+"Query Processing WakeLock");
	}
    
	@Override
	protected void onHandleIntent(Intent intent) {
		
		Log.v(TAG, "onHandleIntent() invoked");
		String action = intent.getAction();
		if (ACTION_PROCESS_QUERY_START.equals(action)){
			msg+=",handleintent-starttime-"+System.currentTimeMillis();
			sWl.acquire();
			String query = intent.getStringExtra("query");
			processQueryStart(query.toLowerCase());
			sWl.release();
			msg+=",handleintent-endtime-"+System.currentTimeMillis();
		}
		else if (ACTION_PROCESS_QUERY_STOP.equals(action)){
			sWl.acquire();
			stopSelf();
			sWl.release();
		}
		
		// Fetch timeline data
	}
	
		
	 private void processQueryStart(String query)
	 {
	   try {
		     
		     msg+=",process-starttime-"+System.currentTimeMillis();
		   
		   	 SelectLexer lexer = new SelectLexer(new ANTLRStringStream(query));
			 SelectParser parser = new SelectParser(new CommonTokenStream(lexer));
			 Cache c = DalspsApplication.getCache();
			 CommonTree tree = (CommonTree)parser.parse().getTree();  
			 SelectWalker walker = new SelectWalker(new CommonTreeNodeStream(tree), c);
			 List<Object> result = walker.query();
			 ArrayList<String> intents = walker.getIntentList(); 
			 ArrayList<String> attribues = walker.getAttributeList();
			 int count = 0;
			 if (result != null)
			 { 
				 for(Object row : result) 
				 {
					   count++;	  
					   if (intents.size()>0){
							Intent i = new Intent(MainService.ACTION_FIRE_INTENT);
							i.putStringArrayListExtra("intent_list", intents);
							startService(i);
							Intent startService = new Intent(QueryService.ACTION_QUERY_STOP);
							startService(startService);
							break;
						}
					   else {
						    if (count > 1) break;
						    CachedObj r = ((CachedObj)row);
						    String msg1 = "sensor type - "+r.getType()+"  ";
						    for (String a:  attribues){					  				
						    	msg1 += r.getFormattedValue(a);
						    }
						    Helper.send("result:"+msg1, null, this);
						    
						    
					   }
				       //Log.v(TAG,"Query Processed Value = "+String.valueOf(((CachedObj)row).data.values[0]));
						
			      }
			 }
		 }catch(Exception ex){
			 Log.e(TAG+" Process", "Query Processing Error",ex);
			 Helper.send("exception:"+" Query Processing Error : "+ex.getMessage(), null, this);
			 Intent startService = new Intent(QueryService.ACTION_QUERY_STOP);
			 startService(startService);
			 stopSelf();
		 }
	   msg +=",process-endtime-"+System.currentTimeMillis();
	 }
	 
	 @Override
		public void onDestroy() {	
			
		   msg+=",ondestroy-time-"+System.currentTimeMillis();
		   Log.i(TAG, msg);
		   
			//Log.v(TAG, "on destroy invoked");			
			this.stopSelf();
			// TODO Auto-generated method stub
			super.onDestroy();
			
		}

	

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		msg+=",onstart-time-"+System.currentTimeMillis();
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}



	
	
	
}
