package com.dalsps.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;


public class NetworkConnectivityReceiver extends BroadcastReceiver {
		
    private static final String TAG = "Network Connectivity";

	@SuppressWarnings("deprecation")
    @Override
    public void onReceive(Context context, Intent intent) {
        
        
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
            Log.e(TAG, "NetworkConnectivityReceiver: Connectivity Manager is null!");
            return;
        }
        
        for (NetworkInfo network : cm.getAllNetworkInfo()) {
                Log.d(TAG, "NetworkConnectivityReceiver: "
                        + " available=" + (network.isAvailable()?1:0)
                        + ", connected=" + (network.isConnected()?1:0)
                        + ", connectedOrConnecting=" + (network.isConnectedOrConnecting()?1:0)
                        + ", failover=" + (network.isFailover()?1:0)
                        + ", roaming=" + (network.isRoaming()?1:0)
                        + ", networkName=" + network.getTypeName());
         } 
        
        
        NetworkInfo network = cm.getActiveNetworkInfo();
        if (network != null && MainService.IsRunning) {
            Log.d(TAG, "NetworkConnectivityReceiver: " + MainService.ACTION_NETWORK_CHANGED + " " + network.getTypeName());
            Intent svcintent = new Intent(MainService.ACTION_NETWORK_CHANGED);
            svcintent.putExtra("available", network.isConnected());
            svcintent.putExtra("failover", network.isFailover());
            context.startService(svcintent);
        }

        network = (NetworkInfo) intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
        if (network.getTypeName().equals("WIFI") && network.isConnected() ) {
            // Start
             Log.d(TAG, "NetworkConnectivityReceiver: startOnWifiConnected enabled, wifi connected, sending intent");
            
            context.startService(new Intent(MainService.ACTION_CONNECT));
        } else if (network.getTypeName().equals("WIFI") && !network.isConnected()) {            
            // Stop 
           Log.d(TAG, "NetworkConnectivityReceiver: stopOnWifiDisconnected enabled, wifi disconnected, sending intent");
            
            context.startService(new Intent(MainService.ACTION_DISCONNECT));
        }
    }
}
