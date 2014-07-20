package com.dalsps.query;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.dalsps.Cache;
import com.dalsps.CachedObj;

public class SelectNode implements Node {

	  final ArrayList<String> attributes;
	  final ArrayList<String> intents;
	  final  Cache data;
	  final Node expression;

	  public SelectNode(ArrayList<String> a,ArrayList<String> b, Cache d, Node e) {
	    attributes = a;
	    data = d;
	    expression = e;
	    intents = b;
	  }

	  @Override
	  public Object eval(B ignored) {
	    List<CachedObj> result = new ArrayList<CachedObj>();
	    int co = 0;
	    while (co < data.getSize() ) {
	      //Log.v("SELECT-NODE", expression.eval((B)data.get(co)).toString());
	      if((Boolean)expression.eval((B)data.get(co))) {
	        // 'b' passed, check which attributes to include
	       result.add(data.get(co));
	      }
	      co++;
	    }
	    return result;
	  }
	  
	  public ArrayList<String> getAttributes(){
			return attributes;
		}
	  public ArrayList<String> getIntents(){
			return intents;
		}
	}

	
	
	
