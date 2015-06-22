# coursera-android-labs2
Cover the Following Topics: (1) AsyncTask &amp; Threading; (2) Notifications, Broadcast Receivers &amp; Alarms; (3) Graphics, Touch &amp; Multimedia; (4) Sensors, Locations, Maps &amp; Data Management

#Graphics-Animation Lab
###Objective:
To gain a better understanding of Graphics, Animation, Touch and Multimedia. 

###Application Sumary:
The user will be presented with an empty screen. When the screen is touched a bubble will appear at the touch location. The bubble will begin rotating and will move across the screen. Touching other areas of the screen will cause new bubbles to appear. If an existing bubble is "popped" the bubble will dissappear and a popping sound will be played. If a user chooses to "fling" an existing bubble, the bubble will change its direction and velocity based on the onFling() event. 

###Program Outline:
####Main.xml
- RelativeLayout
- All other views are added to mFrame (RelativeLayout) dynamically

####BubbleActivity:
#####onCreate()
    -Create RelativeLayout View
    -Define and assign stored bubble bitmap to mBitMap variable
#####onResume()
    -Create an instance of AudioManager, mAudioManager
    -Get StreamVolume from mAudioManager
    -Create an instance of SoundPool, mSoundPool
    -When mSoundPool is successfully loaded setup GestureDetector
    -Load sound into mSoundPool
#####onWindowsFocusChanged()
    -Get height and width dimensions of current display
#####onTouchEvent()
    -Override onTouchEvent, delegating touch controll to setupGestureDetector()
#####setupGestureDestector()
    -Create an instance of GestureDetector
    -Override onFling() and onSingleTapConfirmed() 
    -Make calls to intersects(), deflect(), start() and stop() helper methods to handle bussines logic
#####onPause()
    -Unload and release mSoundPool and mAudioManager
####BubbleView
    -BubbleView manages the size, speed, direction and rotation of each BubbleView created through the helper methods: setRotation(), setSpeedandDirection & createScaledBitMap()
#####intersects()
    -Determines if a touch event intersects with an existin bubbleView on the display
#####deflect()
    -If a bubbleView recieveced is "flinged" then the speed and direction that the bubble is traveling in is changed.
#####start()
    -Create a Worker Thread that verifies if the bubble is still on the display and redraws the image if it is still visible.
    -If the bubble is not in view, call the stop()
#####stop()
    -Removes the bubbleView from the parent view if it is no longer on the display or if it has been popped
    -"Pop" sound is played
#####Override onDraw()
    -Save, rotate and redraw the canvas
    -Draw bitmap at the new location
#####moveWhileonScreen() & isOutOfView()
    -moveWhileOnScreen() changes the bubbles position 
    -Calls isOutOfView() to calculate if bubbleView is still on the display




#Location Lab

###Objective: 
Improve knowledge of how to listen and measure location information.

###Program Outline: 
#####MockLocationProvider:
    
    -Used to set the NETWORK_PROVIDER and location information selected by the user in the Options Menu

#####PlaceDownloaderTask extends AsyncTask:
    - doInBackground - "Downloads" the dummy data (Can be modified to download data from geonames URL)
    - onPostExecute - returns result to PlaceViewActivity
    
#####PlaceRecord:
    - Contains information for locations
    
#####PlaceViewActivity extends ListActivity implements LocationListener:
    - Set adapter, footerview
    - Implement logic for footerview.setOnClickListener()
    - LocationManager registered/unregistered
    - start MockLocation Provider
    - Override onLocationChanged() to handle location logic
    - addNewPlace() is used as callback to PlaceDownloaderTask
    
#####PlaceViewAdapter extends BaseAdapter:
    - Handle data from placeRecord to be displayed in ListView
