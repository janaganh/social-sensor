package com.dalsps;

import java.util.ArrayList;
import java.util.List;

import com.dalsps.net.MainService;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ListFragment;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class ResultFragment extends ListFragment {

	private TimelineReceiver mTimelineReceiver;
	private IntentFilter mIntentFilter;
	private ArrayList<String> messages = new ArrayList();
	private Handler mHandler = new Handler();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		// Initialize the broadcast receiver and intent filter
		// for receiving status update notifications.
		mTimelineReceiver = new TimelineReceiver();
		mIntentFilter = new IntentFilter(MainService.ACTION_VIEWSTATUS_RESULT);
		
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		messages.clear();
		// Create an adapter mapping the cursor data to the layout
		setListAdapter();
	}

	public void clear(){
		messages.clear();
		// Create an adapter mapping the cursor data to the layout
		setListAdapter();
	}
	@Override
	public void onResume() {
		super.onResume();

		// Reset the cursor loader to get a new cursor.

		/*
		 * Request the activity register our broadcast receiver.
		 * 
		 * Restrict it to "trusted" senders whose application holds our custom
		 * permission. Currently, this is just the Yamba application itself.
		 */
		getActivity().registerReceiver(mTimelineReceiver, mIntentFilter);
	}

	@Override
	public void onPause() {
		super.onPause();

		// Request the activity unregister our broadcast receiver.
		getActivity().unregisterReceiver(mTimelineReceiver);
	}

	// Options menu/action bar handling methods.

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);

	}

	private class TimelineReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			
			String s = intent.getStringExtra("result");
			if (s != null) {
				if (messages.size() > 15)
					messages.clear();
				messages.add(s);
				mHandler.post(new Runnable() {
	                public void run() {
	                    setListAdapter();
	                }
	            });
			}

		}

	}

	private void setListAdapter() {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),R.layout.multi_line_list_item, messages);
		this.setListAdapter(adapter);
	}

}
