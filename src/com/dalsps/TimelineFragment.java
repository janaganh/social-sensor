package com.dalsps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class TimelineFragment extends ListFragment
				implements SimpleCursorAdapter.ViewBinder,
				LoaderManager.LoaderCallbacks<Cursor> {

	private static final String[] FROM = {
		StatusProvider.KEY_USER,
		StatusProvider.KEY_CREATED_AT,
		StatusProvider.KEY_MESSAGE
	};
	
	private static final int[] TO = {
		R.id.data_user,
		R.id.data_date,
		R.id.data_msg
	};
	
	private SimpleCursorAdapter mAdapter;
	
	private TimelineReceiver mTimelineReceiver;
	private IntentFilter mIntentFilter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
		// Initialize the broadcast receiver and intent filter
		// for receiving status update notifications.
		mTimelineReceiver = new TimelineReceiver();
		mIntentFilter = new IntentFilter(DalspsApplication.ACTION_NEW_STATUS);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// Create an adapter mapping the cursor data to the layout
		mAdapter = new SimpleCursorAdapter(getActivity(),
				R.layout.timeline_row, null, FROM, TO, 0);
		mAdapter.setViewBinder(this);
		
		setListAdapter(mAdapter);
		
		// Initialize the cursor with the data
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public void onResume() {
		super.onResume();
		
		// Reset the cursor loader to get a new cursor.
		getLoaderManager().restartLoader(0, null, this);
		
		/*
		 * Request the activity register our broadcast receiver.
		 * 
		 * Restrict it to "trusted" senders whose application holds our custom
		 * permission. Currently, this is just the Yamba application itself.
		 */
		getActivity().registerReceiver(mTimelineReceiver, mIntentFilter,
				DalspsApplication.RECEIVE_NEW_STATUS, null);
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

	// ViewBinder method implementation
	
	@Override
	public boolean setViewValue(View v, Cursor cursor, int columnIndex) {
		int id = v.getId();
		switch (id) {
		case R.id.data_date:
			// Present the date in a more human-friendly fashion
			TextView tv = (TextView) v;
			long timestamp = cursor.getLong(columnIndex);
			CharSequence relTime = DateUtils.getRelativeTimeSpanString(timestamp);
			tv.setText(relTime);
			return true;
		default:
			return false;
		}
	}
	
	// LoaderCallbacks method implementations

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// We need to return a new loader.
		return new CursorLoader(getActivity().getApplicationContext(),
				StatusProvider.CONTENT_URI, null, null, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		// We've got a new cursor. Install it in the adapter.
		mAdapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// Release our reference to the cursor, without closing it.
		mAdapter.swapCursor(null);
	}
	
	// Method and broadcast receiver class for handling new status notifications.
	
	public void updateDisplay() {
		getLoaderManager().restartLoader(0, null, this);
	}
	
	private class TimelineReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// When we receive a new status notification,
			// get an updated Cursor for the adapter.
			updateDisplay();
		}
		
	}
	
}
