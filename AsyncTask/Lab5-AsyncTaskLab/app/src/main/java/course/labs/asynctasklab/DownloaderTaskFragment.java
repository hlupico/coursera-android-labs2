package course.labs.asynctasklab;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DownloaderTaskFragment extends Fragment {

	private DownloadFinishedListener mCallback;
	private Context mContext;
    static final String TAG_FRIEND_RES_IDS = "friends";
	
	@SuppressWarnings ("unused")
	private static final String TAG = "Lab-Threads";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Preserve across reconfigurations
		setRetainInstance(true);
		
		// TODO: Create new DownloaderTask that "downloads" data
        DownLoaderTask downLoaderTask = new DownLoaderTask();
        
		
		// TODO: Retrieve arguments from DownloaderTaskFragment
		// Prepare them for use with DownloaderTask. 
        Bundle args = getArguments();
        //.getIntegerArrayList returns the value associated with the given key
        ArrayList arrayList = args.getIntegerArrayList(TAG_FRIEND_RES_IDS);
        //Create integer array for size of arrayList passed

        //TODO: REMOVE THIS LINE OF CODE
        //Integer[] mResourceIds = new Integer[arrayList.size()];

        //Initialize mResourceIds with values from arrayList
        //arrayList must be cast to get Integer[], otherwise Object[] is returned
        //mResourceIds = (Integer[]) arrayList.toArray(mResourceIds);


		// TODO: Start the DownloaderTask 
		downLoaderTask.execute(arrayList);
        

	}

	// Assign current hosting Activity to mCallback
	// Store application context for use by downloadTweets()
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mContext = activity.getApplicationContext(); 

		// Make sure that the hosting activity has implemented
		// the correct callback interface.
		try {
			mCallback = (DownloadFinishedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement DownloadFinishedListener");
		}
	}

	// Null out mCallback
	@Override
	public void onDetach() {
		super.onDetach();
		mCallback = null;
	}


    // TODO: Implement an AsyncTask subclass called DownLoaderTask.
    // This class must use the downloadTweets method (currently commented
    // out). Ultimately, it must also pass newly available data back to
    // the hosting Activity using the DownloadFinishedListener interface.

//	public class DownloaderTask extends ... {
    

    //doInBackground
    public class DownLoaderTask extends AsyncTask<ArrayList<Integer>, Void, String[]> {

        protected String[] doInBackground(ArrayList<Integer>... params) {
            //Create a String[] for feed response, should be length of params passed
            //Helper method downloadTweets will return a String Array
            ArrayList<Integer> resourceIds = params[0];
            String[] feeds = downloadTweets(resourceIds);
            Log.v("doInBackground", feeds.toString());

            return feeds;

        }

        //Pass data back to hosting Activity
        protected void onPostExecute(String[] feeds) {

            mCallback.notifyDataRefreshed(feeds);
        }

    }

	// Helper method simulates downloading Twitter data from the network
    private String[] downloadTweets(ArrayList<Integer> resourceIDS) {

        final int simulatedDelay = 2000;
		String[] feeds = new String[resourceIDS.size()];

        try {
			for (int idx = 0; idx < resourceIDS.size(); idx++) {
				InputStream inputStream;
				BufferedReader in;
				try {
					// Pretend downloading takes a long time
					Thread.sleep(simulatedDelay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				inputStream = mContext.getResources().openRawResource(
						resourceIDS.get(idx));
				in = new BufferedReader(new InputStreamReader(inputStream));

				String readLine;
				StringBuffer buf = new StringBuffer();

				while ((readLine = in.readLine()) != null) {
					buf.append(readLine);
				}

				feeds[idx] = buf.toString();

				if (null != in) {
					in.close();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return feeds;
	}
}