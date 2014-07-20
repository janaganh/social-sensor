package com.dalsps;

import winterwell.jtwitter.Twitter;
import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.Log;

public class DalspsApplication extends Application implements OnSharedPreferenceChangeListener {
	private static final String TAG = "YambaApplication";
	
	public static final String ACTION_NEW_STATUS
		= "com.marakana.android.yamba.ACTION_NEW_STATUS";
	public static final String EXTRA_NEW_STATUS_COUNT
		= "com.marakana.android.yamba.EXTRA_NEW_STATUS_COUNT";
	public static final String RECEIVE_NEW_STATUS
		= "com.marakana.android.yamba.permission.RECEIVE_NEW_STATUS";
	public static final int NEW_STATUS_NOTIFICATION = 1;
	public static final int SHOW_NEW_STATUS_PENDING_INTENT = 1;

	private static DalspsApplication instance;
	
	private SharedPreferences prefs;
	private Twitter twitter;
	private static final Cache cache;
	
	static {
		cache = new Cache(2);
	}
	
	public static Cache getCache(){
		return cache;
	}
	
	public static DalspsApplication getInstance() {
		return instance;
	}

	public synchronized Twitter getTwitter() {
		if (twitter == null) {
			// Retrieve latest preference values for the connection
			String user = prefs.getString("PREF_USER", null);
			String password = prefs.getString("PREF_PASSWORD", null);
			String url = prefs.getString("PREF_SITE_URL", "http://yamba.marakana.com/api");

			// Initialize the Twitter object
			twitter = new Twitter(user, password);
			twitter.setAPIRootUrl(url);
		}
		return twitter;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		instance = this;
		
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	public synchronized void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		Log.v(TAG, "Preference changed: " + key);
		twitter = null;
	}
}
