package com.dalsps;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import com.dalsps.query.SelectLexer;
import com.dalsps.query.SelectNode;
import com.dalsps.query.SelectParser;
import com.dalsps.query.SelectWalker;
import com.dalsps.util.Helper;


import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;



public class QueryService extends IntentService {
	
	public static final String ACTION_QUERY_START = "com.dalsps.action.QUERY_START";
	public static final String ACTION_QUERY_STOP = "com.dalsps.action.QUERY_STOP";
	
	private static AlarmManager am = null;
	private static ArrayList<PendingIntent> pis = new ArrayList<PendingIntent>();
	private   AlarmManager getAlarm(){
		if (am == null)
			am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		return am;
	}
	
	private static final String TAG = "QueryService";

	public QueryService() {
		super("QueryService");
		
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		
		Log.v(TAG, "onHandleIntent() invoked");
		String action = intent.getAction();
		if (ACTION_QUERY_START.equals(action)){
			String query = intent.getStringExtra("query");
			processQueryStart(query.toLowerCase());
		}
		else if (ACTION_QUERY_STOP.equals(action)){
			stopQueryService();
			
		}
		
		// Fetch timeline data
	}
	
		
	 	private void processQueryStart(String query)
	 	{
		 
	 		Log.v(TAG, "Start On Processing");
	 		try 
	 		{
				// The intent we want to use to start the UpdaterService			
				SelectLexer lexer = new SelectLexer(new ANTLRStringStream(query));
				SelectParser parser = new SelectParser(new CommonTokenStream(lexer));
				Cache c = DalspsApplication.getCache();
				CommonTree tree = (CommonTree)parser.parse().getTree();  
				SelectWalker walker = new SelectWalker(new CommonTreeNodeStream(tree), c);
				walker.query();
				ArrayList<String> sensorList= walker.getAttributeList();
				Intent startService = new Intent(this, SensorService.class);
				startService.putExtra("action", "start");
				startService.putStringArrayListExtra("sensorList", sensorList);
				startService(startService);							
				
				//start processing
		    	Intent intent =  new Intent(QueryProcessingService.ACTION_PROCESS_QUERY_START);
				intent.putExtra("query", query);
				// Create the PendingIntent "authorization"
				PendingIntent pi = PendingIntent.getService(this, 1, intent ,PendingIntent.FLAG_UPDATE_CURRENT);
				pis.add(pi);				
				//getAlarm().setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1, 1500, pi);
				getAlarm().setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 1, 1250, pi);
			}
			catch(Exception ex){
				
				Log.e(TAG+" Process start", "Error",ex);
				Helper.send("exception:"+" Query Error : "+ex.getMessage(), null, this);
				stopQueryService();
				//to do inform exception
			}
	 }
	 	
	  	

	 private void stopQueryService(){
		 
		 Intent startService = new Intent(this, SensorService.class);
		 startService.putExtra("action", "stop");
		 stopService(startService);
		 Intent intent = new Intent(QueryProcessingService.ACTION_PROCESS_QUERY_START);
		 PendingIntent pi = PendingIntent.getService(this, 1, intent ,PendingIntent.FLAG_UPDATE_CURRENT);
		 getAlarm().cancel(pi);
		 for (PendingIntent i : pis) {
			 getAlarm().cancel(i);
		}
		 stopSelf();
	 }
	 
	 @Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG+" Process start", "OnDestroy");
		super.onDestroy();
	}
}
