package com.dalsps;



import com.dalsps.net.MainService;
import com.dalsps.net.MainService.LocalBinder;
import com.dalsps.util.Helper;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {
	private static final String TAG = "MainActivity";
	
	private static final int UNKNOWN_FRAGMENT_VISIBLE = -1;
	private static final int COMPOSE_FRAGMENT_VISIBLE = 1;
	private static final int TIMELINE_FRAGMENT_VISIBLE = 2;
	private int mFragmentVisible;
	private static final String FRAGMENT_VISIBLE = "FRAGMENT_VISIBLE";
	
	private ComposeFragment mComposeFragment;
	private ResultFragment mTimelineFragment;
	private FragmentManager mFragmentManager;
	
	private MenuItem mComposeMenuItem;
	private MenuItem mTimelineMenuItem;
	
	
	private TextView mActivityTitle;
	
	private MainService mMainService;
	private Toast mToast;
	private DataUpdateReceiver dataUpdateReceiver;

	private ServiceConnection mMainServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            Log.d(TAG,"MainActivity: MainService connected");
            LocalBinder binder = (LocalBinder) service;
            MainService mainService = binder.getService();
            mMainService = mainService;         
            
        }

        public void onServiceDisconnected(ComponentName className) {
            Log.d(TAG,"mainActivity: MainService disconnected");
            mMainService = null;
            
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate() invoked");
        setContentView(R.layout.main);
        
        mActivityTitle = (TextView) findViewById(R.id.text_main_activity_title);
        mFragmentManager = getSupportFragmentManager();
        
        // Set up the activity's fragments
        if (savedInstanceState == null) {
        	// Initial activity creation. Create fragments.
        	mComposeFragment = new ComposeFragment();
        	mTimelineFragment = new ResultFragment();
        	
        	// Add the fragments to the layout, and hide the ComposeFragment.
        	mFragmentManager.beginTransaction()
        	.add(R.id.fragment_container, mComposeFragment, "compose")
        	.add(R.id.fragment_container, mTimelineFragment, "timeline")
        	.hide(mTimelineFragment)
        	.commit();
        	mFragmentVisible = COMPOSE_FRAGMENT_VISIBLE;
        	
        	mActivityTitle.setText(R.string.activity_main_compose_title);
        }
        else {
        	// Activity bounced. Find the re-created fragments.
        	mComposeFragment = (ComposeFragment) mFragmentManager.findFragmentByTag("compose");
        	mTimelineFragment = (ResultFragment) mFragmentManager.findFragmentByTag("timeline");
        	
        	// Restore fragment visibility flag.
        	mFragmentVisible = savedInstanceState.getInt(FRAGMENT_VISIBLE, UNKNOWN_FRAGMENT_VISIBLE);
        	if (mFragmentVisible == UNKNOWN_FRAGMENT_VISIBLE) {
        		// If for any reason we can't determine which fragment was visible,
        		// default to the TimelineFragment.
        		showComposeFragment();
        		
        		mActivityTitle.setText(R.string.activity_main_compose_title);
        	}
        }
        // Initialize the Toast notification
        mToast = Toast.makeText(getApplicationContext(), null, Toast.LENGTH_LONG);
        
        
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		
		/*  
		 * In our current implementation, the only time we should receive this is when the
		 * the user acknowledges a Yamba new status notification when the activity is
		 * already on screen. In case the ComposeFragment is currently displayed, let's
		 * make the TimelineFragment visible.
		 */
		showTimelineFragment();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		// Save fragment visibility state so that the action bar/options menu state
		// can be restored.
		outState.putInt(FRAGMENT_VISIBLE, mFragmentVisible);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.options_main, menu);
		inflater.inflate(R.menu.options_main_fragment_controls, menu);
		
		// Find references to the menu items to show the ComposeFragment and TimelineFragment
		// so that we can toggle their visibility.
		mComposeMenuItem = menu.findItem(R.id.menu_compose);
		mTimelineMenuItem = menu.findItem(R.id.menu_timeline);
		
		return true;
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		// Set visibility of the item depending on which fragment is visible.
		switch (mFragmentVisible) {
		case TIMELINE_FRAGMENT_VISIBLE:
			mComposeMenuItem.setVisible(true);
			mTimelineMenuItem.setVisible(true);
			
			break;
		default:
			// Assume TimelineFragment visible.
			mTimelineMenuItem.setVisible(true);
			mComposeMenuItem.setVisible(true);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		EditText mEditMsg  = (EditText)mComposeFragment.getView().findViewById(R.id.edit_msg);
		String msg ="",query = "";
		int id = item.getItemId();
		Intent intent;
		switch (id) {
		case R.id.menu_preferences:
			// Display the PrefsActivity
			intent = new Intent(this, PrefsActivity.class);
			startActivity(intent);
			Log.v(TAG, "Settings menu item selected");
			return true;
		case R.id.menu_compose:
			// Show the ComposeFragment
			mTimelineFragment.clear();
			showComposeFragment();
			Helper.send("stop:",null, this);
			return true;
		case R.id.menu_timeline:
			// Show the TimelineFragment
			mTimelineFragment.clear();
			showTimelineFragment();
			msg = mEditMsg.getText().toString();		
			Helper.send("query:"+msg ,null, this);
			return true;
		case R.id.option_lt:
			showComposeFragment();	
			query = "select orientation.x from sensors where orientation.x < 100;";
			mEditMsg.setText(query);
			return true;
		case R.id.option_gt:
			showComposeFragment();	
			query = "select orientation.x from sensors where orientation.x > 70;";
			mEditMsg.setText(query);
			return true;
		case R.id.option_eq:
			showComposeFragment();	
			query = "select orientation.x from  sensors where orientation.x = 50;";
			mEditMsg.setText(query);
			return true;
		case R.id.option_neq:
			showComposeFragment();	
			query = "select orientation.x from sensors where orientation.x != 100;";
			mEditMsg.setText(query);
			return true;
		case R.id.option_and:
			showComposeFragment();	
			query = "select orientation.x,orientation.y from sensors where orientation.x >= 70 and orientation.y <= 50;";
			mEditMsg.setText(query);
			return true;
		case R.id.option_or:
			showComposeFragment();	
			query = "select orientation.x,orientation.y from sensors where orientation.x <= 50 or orientation.x >= 70;";
			mEditMsg.setText(query);
			return true;	
		case R.id.option_gteq:
			showComposeFragment();	
			query = "select orientation.x from sensors where orientation.x >= 50;";
			mEditMsg.setText(query);
			return true;	
		case R.id.option_lteq:
			showComposeFragment();	
			query = "select orientation.x from sensors where orientation.x <= 100;";
			mEditMsg.setText(query);
			return true;
		case R.id.option_attrs:
			showComposeFragment();	
			query = "select orientation.x,orientation.y,orientation.z from sensors where orientation.x > 50;";
			mEditMsg.setText(query);
			return true;	
		
		case R.id.option_action:
			showComposeFragment();	
			query = "select orientation.x from sensors where orientation.x > 50 action_intents display;";
			mEditMsg.setText(query);
			return true;
		case R.id.option_action_multiple:
			showComposeFragment();	
			query = "select orientation.x from sensors where orientation.x > 50 action_intents display,print,test;";
			mEditMsg.setText(query);
			return true;	
		case R.id.option_invalid:
			showComposeFragment();	
			query = "select orientation.x from sensors  orientation.x > ";
			mEditMsg.setText(query);
			return true;	
				
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void showComposeFragment() {
		mFragmentManager.beginTransaction()
			.hide(mTimelineFragment)
			.show(mComposeFragment)
			.commit();
    	mFragmentVisible = COMPOSE_FRAGMENT_VISIBLE;
    	
    	// Update activity title
    	mActivityTitle.setText(R.string.activity_main_compose_title);
    	
    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
    		// Force refresh of action bar
    		//invalidateOptionsMenu();
    	}
	}
	
	public void showTimelineFragment() {
		mFragmentManager.beginTransaction()
			.hide(mComposeFragment)
			.show(mTimelineFragment)
			.commit();
    	mFragmentVisible = TIMELINE_FRAGMENT_VISIBLE;
    	
    	// Update activity title
    	mActivityTitle.setText(R.string.activity_main_timeline_title);
    	
    	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
    		// Force refresh of action bar
    		//invalidateOptionsMenu();
    	}
	}
	
	@Override
    public void onStart() {
        super.onStart();
   
        Log.d(TAG,"MainActivity: onSart()");
        //uncomment
     
        IntentFilter intentFilter = new IntentFilter(MainService.ACTION_XMPP_CONNECTION_CHANGED);
        Intent intent = new Intent(MainService.ACTION_CONNECT);
        bindService(intent, mMainServiceConnection, Context.BIND_AUTO_CREATE);
     
        
    }

	 
	  @Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (dataUpdateReceiver == null) dataUpdateReceiver = new DataUpdateReceiver();
		IntentFilter intentFilter = new IntentFilter(MainService.ACTION_VIEWSTATUS_MESSAGE);
		registerReceiver(dataUpdateReceiver, intentFilter);
		IntentFilter intentFilter2 = new IntentFilter(MainService.ACTION_VIEWSTATUS_OPEN_RESULT_VIEW);
		registerReceiver(dataUpdateReceiver, intentFilter2);
		IntentFilter intentFilter3 = new IntentFilter(MainService.ACTION_VIEWSTATUS_OPEN_QUERY_VIEW);
		registerReceiver(dataUpdateReceiver, intentFilter3);
		
		
	}

	  
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (dataUpdateReceiver != null) unregisterReceiver(dataUpdateReceiver);
	}

	@Override
	    public void onStop() {
	        super.onStop();

	        Log.d(TAG,"MainActivity: onStop()");
	        unbindService(mMainServiceConnection);
	        
	   }
	 
	  private class DataUpdateReceiver extends BroadcastReceiver {
		    @Override
		    public void onReceive(Context context, Intent intent) {
		       
		    	if (intent.getAction().equals(MainService.ACTION_VIEWSTATUS_MESSAGE)) {
		        	String message = intent.getStringExtra("message");
		        	mToast.setText(message);
					mToast.show();
		    	}
		    	else if (intent.getAction().equals(MainService.ACTION_VIEWSTATUS_OPEN_RESULT_VIEW)){
		    		mTimelineFragment.clear();
		    		showTimelineFragment();
		    	}
		    	else if (intent.getAction().equals(MainService.ACTION_VIEWSTATUS_OPEN_QUERY_VIEW)){
		    		showComposeFragment();		    		
		    	}
		    }
	  }	    
	  
}

