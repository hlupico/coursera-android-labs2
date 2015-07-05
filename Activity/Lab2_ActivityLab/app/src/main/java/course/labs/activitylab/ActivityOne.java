package course.labs.activitylab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


// ActivityOne controls the first screen that the user sees upon opening the application

public class ActivityOne extends Activity {

	// Use these as keys when saving state between reconfigurations
	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityOne";

	// TODO:
	// Create variables mCreate, mRestart, mStart and mResume
	// These variables will keep track of the calls made to
	// onCreate(), onRestart(), onStart() and onResume().

	private int mCreate;
	private int mRestart;
	private int mStart;
	private int mResume;

    // Defining these variables as private restricts other portions
    // of the program from changing their values
	// You will need to increment these variables' values when their
	// corresponding lifecycle methods get called.

	// TODO: Create variables for each of the TextViews
	// mTvCreate, mTvRestart, mTvStart, mTvResume will
	// display the current count of each counter variable

	TextView mTvCreate;
	TextView mTvRestart;
	TextView mTvStart;
	TextView mTvResume;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
		
		// TODO: Assign the appropriate TextViews to the TextView variables
		// Access the TextView by calling Activity's findViewById()
		// textView1 = (TextView) findViewById(R.id.textView1);
		
		mTvCreate = (TextView) findViewById(R.id.create);
		mTvRestart = (TextView) findViewById(R.id.restart);
		mTvStart = (TextView) findViewById(R.id.start);
		mTvResume = (TextView) findViewById(R.id.resume);
		
		Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo);

        launchActivityTwoButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO: Launch Activity Two

				// Create an intent to start ActivityTwo
				Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);

				// Launch the Activity using the intent
				startActivity(intent);
			}
		});

		// Has previous state been saved?
		if (savedInstanceState != null) {

			// TODO: Restore value of counters from saved state
			// Only need 4 lines of code, one for every count variable

            mCreate = savedInstanceState.getInt(CREATE_KEY);
			mRestart = savedInstanceState.getInt(RESTART_KEY);
			mStart = savedInstanceState.getInt(START_KEY);
			mResume = savedInstanceState.getInt(RESUME_KEY);

		}else{

			//Initialize members with default values for a new instance

            mCreate = 0;
			mRestart = 0;
			mStart = 0;
			mResume = 0;
		}

		Log.i(TAG, "Entered the onCreate() method");

		// TODO:
		// Update the appropriate count variable
		// Update the user interface via the displayCounts() method
		mCreate ++;
		displayCounts();

	}

	// Lifecycle callback overrides

	@Override
	public void onStart() {
		super.onStart();

		Log.i(TAG, "Entered the onStart() method");

		// TODO:
		// Update the appropriate count variable
		// Update the user interface
		mStart++;
		displayCounts();

	}

	@Override
	public void onResume() {
		super.onResume();

		Log.i(TAG, "Entered the onResume() method");

		// TODO:
		// Update the appropriate count variable
		// Update the user interface
		mResume++;
		displayCounts();

	}

	@Override
	public void onPause() {
		super.onPause();

		Log.i(TAG, "Entered the onPause() method");
	}

	@Override
	public void onStop() {
		super.onStop();

		Log.i(TAG, "Entered the onStop() method");
	}

	@Override
	public void onRestart() {
		super.onRestart();

		Log.i(TAG, "Entered the onRestart() method");

		// TODO:
		// Update the appropriate count variable
		// Update the user interface
		mRestart++;
		displayCounts();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();

		Log.i(TAG, "Entered the onDestroy() method");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {

		// TODO:
		// Save state information with a collection of key-value pairs
		// 4 lines of code, one for every count variable
		savedInstanceState.putInt(START_KEY, mStart);
		savedInstanceState.putInt(CREATE_KEY, mCreate);
		savedInstanceState.putInt(RESTART_KEY, mRestart);
		savedInstanceState.putInt(RESUME_KEY, mResume);
		
		// Always call the superclass so it can save the view hierarchy state
	    super.onSaveInstanceState(savedInstanceState);

	}

	// Update the displayed counters
	// This method expects that the counters and TextView variables
	// use the names specified above
	public void displayCounts() {
		// TODO - uncomment these lines
		mTvCreate.setText("onCreate() calls: " + mCreate);
		mTvStart.setText("onStart() calls: " + mStart);
		mTvResume.setText("onResume() calls: " + mResume);
		mTvRestart.setText("onRestart() calls: " + mRestart);
	
	}
}
