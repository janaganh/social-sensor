package com.dalsps.query;

import com.dalsps.CachedObj;

public class Acclerometer extends CachedObj  {
	public double getX(){
		return this.data.values[0];
	}
	public double getY(){
		return this.data.values[1];
	}
	public double getZ(){
		return this.data.values[0];
	}
}
