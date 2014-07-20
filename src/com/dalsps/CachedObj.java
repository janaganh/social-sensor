package com.dalsps;
import java.math.BigDecimal;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

import com.dalsps.query.B;

public class CachedObj extends B {
	public SensorEvent data;
	public int sent;
	
	private String cutValue(double val){
		String s = String.valueOf(val);
		int i = s.indexOf(".");
		if ( (i + 2 < ( s.length() -1))  )
			 return s.substring(0,i+3);
		else
			return s;	
	}
	
	public Object getAttribute(String sensor){
		if (isSameType(sensor)){
			return getVal(sensor);
		}
		return null;
	}
	
	private double getVal(String sensor){
		if (sensor.endsWith(".x")){
			return new  Double(cutValue(data.values[0]));
		}
		else if (sensor.endsWith(".y")){
			return new  Double(cutValue(data.values[1]));
		}
		else if (sensor.endsWith(".z")){
			return new  Double(cutValue(data.values[2]));
		}
		else{
			return new  Double(cutValue(data.values[0]));
		}
	}
	
	private boolean isSameType(String sensor){
		if (sensor.startsWith("orientation")){
			return data.sensor.getType() == Sensor.TYPE_ORIENTATION; 
		}
		else if (sensor.startsWith("accelerometer")){
			return data.sensor.getType() == Sensor.TYPE_ACCELEROMETER;
		}
		
		return false;
	}
	
	public String getType(){
		String type = "unknown";
		if (data.sensor.getType() == Sensor.TYPE_ORIENTATION)
			type = "orientation";
		else if (data.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
			type = "accelerometer";
		return type;
	}
	
	 public String getFormattedValue(String sensor){
		 StringBuilder s = new StringBuilder();
		 s.append(",[");
		 s.append(splitNum(getVal(sensor)));
		 s.append("]");
		 return s.toString();
	 }
	 
	 private String splitNum(double n){
		 String s= String.valueOf(n);
		 s = s.substring(0,s.indexOf("."))+" . "+s.substring(s.indexOf(".")+1,s.length());
		 return s;
	 }
}
