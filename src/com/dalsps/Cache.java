package com.dalsps;
import java.util.ArrayList;


public class Cache  {

	private int firstInIndex = 0;
	private int lastIndex;
	private int cacheSize;
	private final ArrayList<CachedObj> cache; 
	
	public Cache(int size){
		this.cacheSize = size;
		lastIndex = size - 1;
		cache = new ArrayList<CachedObj>(size);
	}
	private void setFirtInIndex(){
		if (firstInIndex == lastIndex ){
			firstInIndex = 0; 
		}
		else{
			firstInIndex++;
		}
	}
	public void put(CachedObj obj){
		if (cache.size() < cacheSize){
			cache.add(obj);			
		}
		else{
			setFirtInIndex();
			cache.add(firstInIndex, obj);
		}
	}
	
	public CachedObj get(int index){
		return this.cache.get(index);
	}
	
	public int getCacheSize(){
		return cacheSize;
	}
	public int getSize(){
		return cache.size();
	}
	
	
	public int getFirstInIndex(){
		return firstInIndex;
	}
}


