package com.dalsps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.dalsps.net.MainService;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Register an alarm to trigger the UpdaterService on a repeating basis.
		Intent serviceIntent = new Intent(MainService.ACTION_CONNECT);
        context.startService(serviceIntent);
	}

}
