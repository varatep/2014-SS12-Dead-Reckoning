package Handlers;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;

import java.util.Locale;



/**
 * Created by Austin on 2/7/14.
 *
 *
 */
public class LocationHandler {
	private String longitude;
	private String latitude;
	private LocationListener locationlisten;
	// Define a listener that responds to location updates
	private LocationListener locationListener = new LocationListener() {
	    public void onLocationChanged(Location location) {
	      // Called when a new location is found by the network location provider.
	      //makeUseOfNewLocation(location);
	    //locationlisten.
	    longitude = "" + location.getLongitude();
	    latitude = "" + location.getLatitude();
	    }

	    public void onStatusChanged(String provider, int status, Bundle extras) {}

	    public void onProviderEnabled(String provider) {}

	    public void onProviderDisabled(String provider) {}
	  };
}