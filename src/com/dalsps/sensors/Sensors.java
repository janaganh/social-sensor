package com.dalsps.sensors;

import java.util.ArrayList;

import com.dalsps.CachedObj;
import com.dalsps.DalspsApplication;

import android.app.Application;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class Sensors implements SensorEventListener {
    
	private final SensorManager mSensorManager;
    private final ArrayList<Sensor> sensors;
    private ArrayList<String> availableSensorsList;
    
    public Sensors (ArrayList<String> sensorList) throws Exception {
    	initSensorList();
    	
    	if (!checkSensorAvailability(sensorList)){
    		throw new Exception("Unavailable sensor");
    	}
    	
    	DalspsApplication app = DalspsApplication.getInstance(); 
    	sensors = new ArrayList<Sensor>();
    	mSensorManager = (SensorManager)app.getSystemService(app.SENSOR_SERVICE);
        for (String sensor : sensorList) {
			if (sensor.startsWith("orientation")){
				sensors.add(mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION));
				
			}else
			if(sensor.startsWith("accelerometer")){
				sensors.add(mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
			}
		}
    	 
    }

    private void initSensorList(){
    	availableSensorsList = new ArrayList<String>();
    	availableSensorsList.add("orientation");
    	availableSensorsList.add("accelerometer");
    	
    }
    
    private boolean checkSensorAvailability(ArrayList<String> sensorList){
    	
    	for (String sensor : sensorList) {
			boolean ok = false;
    		for (String s: availableSensorsList){				
				if (sensor.startsWith(s)){
					ok  = true;
					break;
				}
			}
    		if(ok == false)
    			return false;
			
		}
    	return true;
    }
    
   public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public void onSensorChanged(SensorEvent event) {
    	//Log.v("SENSOR",String.valueOf(event.values[0]));
    	CachedObj co = new  CachedObj();
    	co.data = event;
    	DalspsApplication.getCache().put(co);
    }
    
    
    public void start()
    {
    	
    	for (Sensor sensor : sensors) {
    		mSensorManager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
    		
		
    	
    }
    
    public void stop(){
    	mSensorManager.unregisterListener(this);
    }
    
}