package com.dalsps.net;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

import com.dalsps.util.Helper;


import android.app.backup.BackupManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;


/**
 * 
 *  
 * In order to work flawlessly with the BackupAgent
 * ALL settings in SettingsManager have to be of the same type
 * as within the SharedPreferences back-end AND they need to have
 * the same name
 *
 */
public class SettingsManager {
    
	public static final String TAG = "SettingsManager";
	
	public static final String[] xmppConnectionSettings = { "serverHost", "serviceName", "serverPort", 
                                                            "login", "password"};
    
    // XMPP connection
    public String serverHost;
    public String serviceName;
    public int serverPort;
    
    private String login;
    public String getLogin() { return login; }
    public void setLogin(String value) { login = saveStringSetting("login", value); }
    
    private String password;
    public String getPassword() { return password; }
    public void setPassword(String value) { password = saveStringSetting("password", value); }
    private String notifiedAddress;
    public String getNotifiedAddress() { return notifiedAddress; }
    public void setNotifiedAddress(String s) { notifiedAddress = s; }
    public static boolean connectionSettingsObsolete;
    
    private static SettingsManager sSettingsManager = null;
    
    private SharedPreferences mSharedPreferences;
    private Context mContext;
    private String to;
    
    public String getTo(){
    	return to;
    }
    public void setTo(String to){
    	this.to = to;
    }
    private OnSharedPreferenceChangeListener mChangeListener = new OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Log.i(TAG, "Preferences updated: key=" + key);
            importPreferences();
            OnPreferencesUpdated(key);
        }
    };
    
    private SettingsManager(Context context) {
        mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(Helper.APP_NAME, 0);
        mSharedPreferences.registerOnSharedPreferenceChangeListener(mChangeListener);
        
        importPreferences();
    }
    
    public static SettingsManager getSettingsManager(Context context) {
        if (sSettingsManager == null) {
            sSettingsManager = new SettingsManager(context);           
        } 
        return sSettingsManager;        
    }
    
    public void Destroy() {
        mSharedPreferences.unregisterOnSharedPreferenceChangeListener(mChangeListener);
    }
    
    public SharedPreferences.Editor getEditor() {
        return mSharedPreferences.edit();
    }
    
    public String saveStringSetting(String key, String value) {
        getEditor().putString(key, value).commit();
        return value;
    }
    
    public boolean saveBooleanSetting(String key, boolean value) {
        getEditor().putBoolean(key, value).commit();
        return value;
    }
    
    public Map<String, ?> getAllSharedPreferences() {
        return mSharedPreferences.getAll();
    }
    
    public boolean SharedPreferencesContains(String key) {
        return mSharedPreferences.contains(key);
    }

    public void OnPreferencesUpdated(String key) {
    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            BackupManager bm = new BackupManager(mContext);
            bm.dataChanged();
        }
        for (String s : xmppConnectionSettings) {
            if (s.equals(key)) {
                connectionSettingsObsolete = true;
            }
        }
        
    }
    
    /** imports the preferences */
    private void importPreferences() {       
        serverHost = mSharedPreferences.getString("serverHost", "");
        serverPort = mSharedPreferences.getInt("serverPort", 0);
        serviceName = mSharedPreferences.getString("serviceName", "");
        notifiedAddress = mSharedPreferences.getString("notifiedAddress", "");
        login = mSharedPreferences.getString("login", "");
        password =  mSharedPreferences.getString("password", ""); 
       }
}
