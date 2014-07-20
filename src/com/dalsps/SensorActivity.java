package com.dalsps;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

public class SensorActivity extends Activity implements SensorEventListener {
	private static final String TAG = "SensorActivity";
	  private SensorManager mSensorManager;
	  private Sensor mProximity[];

	  @Override
	  public final void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
	    	
	    // Get an instance of the sensor service, and use that to get an instance of
	    // a particular sensor.
	    mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	    
	    mProximity = new Sensor[]{ /*mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),*/
	    		mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)};
	    							
	    }
	  

	  @Override
	  public final void onAccuracyChanged(Sensor sensor, int accuracy) {
	    // Do something here if sensor accuracy changes.
		 Log.v(TAG, "Accuracy invoked");
	  }

	  @Override
	  public final void onSensorChanged(SensorEvent event) {
	    float distance = event.values[0];
	    Log.v(TAG, "On SensorChanged Sensor:"+event.sensor.getName()+" value = "+distance);// Do something with this sensor data.
	    
	  }

	  @Override
	  protected void onResume() {
	    // Register a listener for the sensor.
	    super.onResume();
	    mSensorManager.registerListener(this, mProximity[0], SensorManager.SENSOR_DELAY_FASTEST);
	    //mSensorManager.registerListener(this, mProximity[1], SensorManager.SENSOR_DELAY_FASTEST);
	  }

	  @Override
	  protected void onPause() {
	    // Be sure to unregister the sensor when the activity pauses.
	    super.onPause();
	    mSensorManager.unregisterListener(this);
	  }
	}