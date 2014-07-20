package com.dalsps;



import java.util.ArrayList;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import com.dalsps.sensors.Sensors;
public class SensorService extends IntentService {
	
	private static final String TAG = "SensorService";
	private Sensors sensor = null;
	
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		
	}

	@Override
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.v(TAG, "on start"); 
		
		return super.onStartCommand(intent, flags, START_FLAG_RETRY );
	}

	public SensorService() {
		super("SensorService");	
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.v(TAG, "onHandleIntent() invoked");
		String action  = intent.getStringExtra("action");
		if (action.equals("start") ){
			try {
				ArrayList<String> sensorList = intent.getStringArrayListExtra("sensorList");
				sensor = new Sensors(sensorList);
				sensor.start();
			}catch(Exception ex){
				//broadcast error
				sensor = null;
			}
		}
		else if (action.equals("stop")){
			if (sensor != null)
				sensor.stop();
			sensor = null;
		}
		
		Log.v(TAG, "Completed");
	}

	@Override
	public void onDestroy() {	
		
		Log.v(TAG, "on destroy invoked");
		if (sensor != null)
			sensor.stop();
		sensor = null;
		
		this.stopSelf();
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}
	
	

}
