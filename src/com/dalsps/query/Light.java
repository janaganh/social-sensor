package com.dalsps.query;

import com.dalsps.CachedObj;

public class Light  extends CachedObj {
	
	public double get(){
		return this.data.values[0];
	}
}
