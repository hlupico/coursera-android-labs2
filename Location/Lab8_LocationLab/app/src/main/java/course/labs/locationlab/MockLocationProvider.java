 package course.labs.locationlab;

 // Mocks a LocationProvider
// Adapted from code found at: 
// http://mobiarch.wordpress.com/2012/07/17/testing-with-mock-location-data-in-android/

//The MockLocationProvider sets updates for the LastKnownLocation based
//on the user's selection in the Options Menu

 import android.content.Context;
 import android.location.Location;
 import android.location.LocationManager;
 import android.os.SystemClock;
 import android.util.Log;

 public class MockLocationProvider {

	private String mProviderName;
	private LocationManager mLocationManager;
	private static float sMockAccuracy = 5;

	public MockLocationProvider(String name, Context ctx) {
		this.mProviderName = name;
        Log.v("MockLocationProvider", name);
		mLocationManager = (LocationManager) ctx
				.getSystemService(Context.LOCATION_SERVICE);
		mLocationManager.addTestProvider(mProviderName, false, false, false,
				false, true, true, true, 0, 5);
		mLocationManager.setTestProviderEnabled(mProviderName, true);
	}

	public void pushLocation(double lat, double lon) {
		Location mockLocation = new Location(mProviderName);
		mockLocation.setLatitude(lat);
		mockLocation.setLongitude(lon);
		mockLocation.setAltitude(0);
		mockLocation.setTime(System.currentTimeMillis());
		mockLocation
				.setElapsedRealtimeNanos(SystemClock.elapsedRealtimeNanos());
		mockLocation.setAccuracy(sMockAccuracy);
		mLocationManager.setTestProviderLocation(mProviderName, mockLocation);
	}

	public void shutdown() {
		mLocationManager.removeTestProvider(mProviderName);
	}
}
