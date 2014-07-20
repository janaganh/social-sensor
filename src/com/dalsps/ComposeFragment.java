package com.dalsps;

import java.util.ArrayList;
import java.util.List;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import winterwell.jtwitter.TwitterException;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.dalsps.query.SelectLexer;
import com.dalsps.query.SelectParser;
import com.dalsps.query.SelectWalker;
import com.dalsps.util.Helper;


public class ComposeFragment extends Fragment  {
	private static final String TAG = "ComposeFragment";
	private boolean toggle = false;
	private EditText mEditMsg;
	private Toast mToast;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Retain this instance of the fragment across Activity restarts
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View top = inflater.inflate(R.layout.compose_fragment, container, false);
		
        // Get references to our View objects
        mEditMsg = (EditText) top.findViewById(R.id.edit_msg);
        //Button buttonUpdate = (Button) top.findViewById(R.id.button_update);
        
        //sample query
        String query = "select orientation.x from sensors  where orientation.x > 10 ;";
        mEditMsg.setText(query);
        
        // Initialize the Toast notification
        mToast = Toast.makeText(getActivity().getApplicationContext(), null, Toast.LENGTH_LONG);

		return top;
	}

	

	
	
	public void test(){
		try {
		
			String src = "select accelerometer,orientation from sensors  where accelerometer > 1 AND orientation > 80 ;";			
			// The intent we want to use to start the UpdaterService
			Intent startService = new Intent(getActivity().getApplicationContext(), SensorService.class);
			
			SelectLexer lexer = new SelectLexer(new ANTLRStringStream(src));
			SelectParser parser = new SelectParser(new CommonTokenStream(lexer));
			Cache c = DalspsApplication.getCache();
			CommonTree tree = (CommonTree)parser.parse().getTree();  
			SelectWalker walker = null;
			
			if (!this.toggle){
				walker = new SelectWalker(new CommonTreeNodeStream(tree), c);
				walker.query();
				ArrayList<String> sensorList = null;//walker.sensorList;
				startService.putExtra("action", "start");
				startService.putStringArrayListExtra("sensorList", sensorList);
				getActivity().startService(startService);
				this.toggle = true;
			}	
			else{
				startService.putExtra("action", "stop");
				getActivity().stopService(startService);
				this.toggle = false;
				walker = new SelectWalker(new CommonTreeNodeStream(tree), c);
				List<Object> result = walker.query();
				for(Object row : result) {
					Log.v(TAG,"sucess");  
					Log.v(TAG,String.valueOf(((CachedObj)row).data.values[0]));
				  }
				
			}	

		}
		catch(Exception ex){
			Log.e("SELECT", "WTF",ex);
		}
							
	}

	 public void test3(){
		try {
		
			if (!toggle){
				String src = "select accelerometer,orientation from sensors  where accelerometer > 1 AND orientation > 80 ;";			
				// The intent we want to use to start the UpdaterService
				Intent startService = new Intent(QueryService.ACTION_QUERY_START);
				startService.putExtra("query",src);
				getActivity().startService(startService);
				toggle = true;
			}
			else{
				toggle = false;
				Intent startService = new Intent(QueryService.ACTION_QUERY_STOP);
				getActivity().startService(startService);
				
			}
			
		}
		catch(Exception ex){
			Log.e("SELECT", "WTF",ex);
		}
							
	}
}

