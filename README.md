# coursera-android-labs2
Cover the Following Topics: (1) AsyncTask &amp; Threading; (2) Notifications, Broadcast Receivers &amp; Alarms; (3) Graphics, Touch &amp; Multimedia; (4) Sensors, Locations, Maps &amp; Data Management

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
