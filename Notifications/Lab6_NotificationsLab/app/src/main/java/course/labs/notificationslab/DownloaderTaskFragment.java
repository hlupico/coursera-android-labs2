package course.labs.notificationslab;

import android.app.Activity;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DownloaderTaskFragment extends Fragment {

	private DownloadFinishedListener mCallback;
	private Context mContext;
	private final int MY_NOTIFICATION_ID = 11151990;
    static final String TAG_FRIEND_RES_IDS = "friends";
    private Integer mBroadcastReceiverCode;
    private PendingIntent mContentIntent;
    private boolean downloadCompleted = false;

    private final CharSequence tickerText = "This is a Really, Really, Super Long Notification Message!";

    private BroadcastReceiver mRefreshReceiver;


	@SuppressWarnings("unused")
	private static final String TAG = "Lab-Notifications";

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Preserve across reconfigurations
        setRetainInstance(true);

        // Create new DownloaderTask that "downloads" data
        DownLoaderTask downLoaderTask = new DownLoaderTask();

        // Retrieve arguments from DownloaderTaskFragment
        // Prepare them for use with DownloaderTask.
        Bundle args = getArguments();
        //.getIntegerArrayList returns the value associated with the given key
        ArrayList arrayList = args.getIntegerArrayList(TAG_FRIEND_RES_IDS);

        //Start the DownloaderTask
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


	//Implement an AsyncTask subclass called DownLoaderTask.
	//This class must use the downloadTweets method
	//Ultimately, it must also pass newly available data back to
	//the hosting Activity using the DownloadFinishedListener interface.

    public class DownLoaderTask extends AsyncTask<ArrayList<Integer>, Void, String[]> {

        protected String[] doInBackground(ArrayList<Integer>... params) {
            //Create a String[] for feed response, should be length of params passed
            //Helper method downloadTweets will return a String Array
            ArrayList<Integer> resourceIds = params[0];
            String[] feeds = downloadTweets(resourceIds);
            Log.v("doInBackground", "Feeds In doInBackground");

            return feeds;

        }

        //Pass data back to hosting Activity
        protected void onPostExecute(String[] feeds) {

            if(feeds != null) {
                mCallback.notifyDataRefreshed(feeds);
            }
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

            downloadCompleted = true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        notify(downloadCompleted);
        return feeds;

    }


    // Uncomment this helper method.
    // If necessary, notifies the user that the tweet downloads are
    // complete. Sends an ordered broadcast back to the BroadcastReceiver in
    // MainActivity to determine whether the notification is necessary.


    private void notify(final boolean success){

        Log.v(TAG, "Entered notify() - success: " + success);

        //restartMainActivityIntent will be wrapped with PendingIntent to change target action
        final Intent restartMainActivityIntent = new Intent(mContext, MainActivity.class);
        restartMainActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);


        //.sendOrderedBroadcast allows you to receive data back from the broadcast
        //The broadcast is created within the .sendOrderedBroadcast()
        //sendOrderedBroadcast(Intent intent, String receiverPermission, BroadcastReceiver resultReceiver, Handler scheduler, int initialCode, String initialData, Bundle initialExtras)
        mContext.sendOrderedBroadcast(new Intent(MainActivity.DATA_REFRESHED_ACTION), null,
                //Create new BroadcastReceiver to receive a result (the state of MainActivity)
                new BroadcastReceiver() {

                    final String failMsg = mContext
                            .getString(R.string.download_failed_string);
                    final String successMsg = mContext
                            .getString(R.string.download_succes_string);
                    final String notificationSentMsg = mContext
                            .getString(R.string.notification_sent_string);

                    @Override
                    public void onReceive(Context context, Intent intent) {

                        // TODO: Check whether or not the MainActivity received the broadcast
                        // The resultcode, MainActivity.IS_ALIVE, indicates that MainActivity is
                        // active and in the foreground.
                        mBroadcastReceiverCode = getResultCode();
                        Log.v(TAG, "This is mBroadcastRecieverCode: " + mBroadcastReceiverCode.toString());
                        //Primative types cannot be null, use Integer instead of int to test if result has been received
                        //If no code is returned - display Notification Area Notification when data download is complete
                        if (mBroadcastReceiverCode != Activity.RESULT_FIRST_USER) {

                            // TODO: Create a PendingIntent using the
                            // restartMainActivityIntent and set its flags to FLAG_UPDATE_CURRENT

                            mContentIntent = PendingIntent.getActivity(mContext, 0,
                                    restartMainActivityIntent, PendingIntent.FLAG_UPDATE_CURRENT);


                            // Uses R.layout.custom_notification for the
                            // layout of the notification View. The xml
                            // file is in res/layout/custom_notification.xml

                            RemoteViews mContentView = new RemoteViews(
                                    mContext.getPackageName(),
                                    R.layout.custom_notification);

                            // TODO: Set the notification View's text to
                            // reflect whether the download completed
                            // successfully

                            mContentView.setTextViewText(R.id.text, successMsg);

                            // TODO: Use the Notification.Builder class to
                            // create the Notification. You will have to set
                            // several pieces of information. You can use
                            // android.R.drawable.stat_sys_warning
                            // for the small icon. You should also
                            // setAutoCancel(true).

                            Notification.Builder notificationBuilder = new Notification.Builder(mContext)
                                    .setTicker(tickerText)
                                    .setSmallIcon(android.R.drawable.stat_sys_warning)
                                    .setAutoCancel(true)
                                    .setContentIntent(mContentIntent)
                                    .setContent(mContentView);

                            //Build The notificationBuilder
                            //notificationBuilder.build();

                            // TODO: Send the notification

                            NotificationManager mNotificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                            mNotificationManager.notify(MY_NOTIFICATION_ID, notificationBuilder.build());

                            Toast.makeText(mContext, notificationSentMsg, Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(mContext,
                                    success ? successMsg : failMsg,
                                    Toast.LENGTH_LONG).show();
                        }
                    }

                }
                , null, 0, null, null);
    }


    // Uncomment this helper method
    // Saves the tweets to a file

    private void saveTweetsToFile(String[] result) {
        PrintWriter writer = null;
        try {
            FileOutputStream fos = mContext.openFileOutput(
                    MainActivity.TWEET_FILENAME, Context.MODE_PRIVATE);
            writer = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(fos)));

            for (String s : result) {
                writer.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }


	
}