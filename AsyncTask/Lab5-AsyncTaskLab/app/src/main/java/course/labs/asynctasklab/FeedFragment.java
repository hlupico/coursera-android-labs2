package course.labs.asynctasklab;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FeedFragment extends Fragment {

	private TextView mTextView;
    private static final String TAG = "FeedFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.feed, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mTextView = (TextView) getView().findViewById(R.id.feed_view);
        Log.v(TAG, getArguments().getString(MainActivity.TAG_TWEET_DATA));
		mTextView.setText(getArguments().getString(MainActivity.TAG_TWEET_DATA));
	}


}