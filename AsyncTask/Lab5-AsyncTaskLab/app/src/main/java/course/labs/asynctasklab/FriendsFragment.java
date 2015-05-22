package course.labs.asynctasklab;

import android.app.Activity;
import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FriendsFragment extends ListFragment {

	// HostingActivity
	private SelectionListener mCallback;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ArrayAdapter - Constructor  = ArrayAdapter(Context context, int resource, T[] objects)
        setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, MainActivity.FRIENDS_NAMES));
        //simple_list_item_1 is a built-in XML layout document that is part of the Android OS


	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		// Make sure that the hosting Activity has implemented
		// the callback interface. 
		try {
            //TODO: HANNA - What is going on here? Is SelectionListener passing MainActivity as mCallback?
			mCallback = (SelectionListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement SelectionListener");
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		// Enable user interaction only if data is available
        //Default mIsInteractionEnabled is declared in MainActivity
		setAllowUserClicks(mCallback.canAllowUserClicks());
		
	}

	// Enable/disable user interaction 
	void setAllowUserClicks(boolean allowUserInteraction) {
		getListView().setEnabled(allowUserInteraction);
		if (allowUserInteraction) {
			getListView().setBackgroundColor(Color.WHITE);
		} else {
			getListView().setBackgroundColor(Color.DKGRAY);
		}
	}

    //This method will be called when an item in the list is selected.
	@Override
	public void onListItemClick(ListView l, View view, int position, long id) {
		// Inform hosting Activity of user's selection
		if (null != mCallback) {
            //onItemSelected is a method of SelectionListener
            //Passes position of view selected from FriendsFeed
			mCallback.onItemSelected(position);
		}
	}

}
