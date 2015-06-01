package pack.pepulse.com;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by Pepe on 17/02/2015.
 */
public class CurrentLocationListener implements LocationListener {

    public static String s;
    MainActivity mainActivity;

    public MainActivity getMainActivity(){

        return mainActivity;
    }

    public void setMainActivity(MainActivity mainActivity){

        this.mainActivity = mainActivity;
    }

    @Override
    public void onLocationChanged(Location location){

        location.getLatitude();
        location.getLongitude();

        s = "Latitude = " + location.getLatitude() + " \n\tLongitude = " + location.getLongitude() +
                "\n\tGMaps Link = http://maps.google.es/?q=" + location.getLatitude() + "%20" + location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public static String getCoordinates(){

        return s;
    }
}
