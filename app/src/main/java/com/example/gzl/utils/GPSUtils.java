package com.example.gzl.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class GPSUtils {
    public LocationManager locationManager;
    public double mLongitude;
    public double mLatitude;
    public Location mLocation;
    private static volatile GPSUtils instance;
    private static final double EARTH_RADIUS = 6378137.0;
    private IGPSListener mListener;

    public static GPSUtils getInstance() {
        if (instance == null) {
            synchronized (GPSUtils.class) {
                if (instance == null) {
                    instance = new GPSUtils();
                }
            }
        }
        return instance;
    }

    public void onCreate(Context context) {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        if (isOpenNetworkProvider(context)) {
            registNetworkProvider();
        } else if (isOpenGpsProvider(context)) {
            registGpsProvider();
        }
    }

    public void onResume(Context context) {
        if (isOpenNetworkProvider(context)) {
            registNetworkProvider();
        } else if (isOpenGpsProvider(context)) {
            registGpsProvider();
        }
    }

    public interface IGPSListener {
        void onLocationChanged();
    }

    public void setOnGpsListener(IGPSListener listener) {
        mListener = listener;
    }

    private LocationListener locationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        @Override
        public void onProviderEnabled(String provider) {
        }

        @Override
        public void onProviderDisabled(String provider) {
        }

        @Override
        public void onLocationChanged(Location location) {
            mLocation = location;
            mLatitude = location.getLatitude();
            mLongitude = location.getLongitude();
            if (mListener != null) {
                mListener.onLocationChanged();
            }

        }
    };

    public double distanceBetween(double lat, double lon) {
        float[] results = new float[1];
        Location.distanceBetween(mLatitude, mLongitude, lat, lon, results);
        return results[0];
    }

    public double getGpsDistance(double lng_a, double lng_b) {
        if (mLongitude == 0 || mLatitude == 0) {
            return 0;
        }
        double radLat1 = (mLongitude * Math.PI / 180.0);
        double radLat2 = (mLatitude * Math.PI / 180.0);
        double a = radLat1 - radLat2;
        double b = (lng_a - lng_b) * Math.PI / 180.0;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    private boolean isOpenLocProvider(Context context) {
        return isOpenGpsProvider(context) || isOpenNetworkProvider(context);
    }

    private boolean isOpenGpsProvider(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (lm == null) {
            return false;
        }
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    private boolean isOpenNetworkProvider(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (lm == null) {
            return false;
        }
        return lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private void openGps(Context context) {
        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }

    private void openGps(Context context, int requestCode) {
        Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    private void registNetworkProvider() {
        try {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10 * 1000, 10, locationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registGpsProvider() {
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10 * 1000, 10, locationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onDestory() {
        try {
            locationManager.removeUpdates(locationListener);
            mListener = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
