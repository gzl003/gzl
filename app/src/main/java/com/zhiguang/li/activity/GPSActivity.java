package com.zhiguang.li.activity;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.zhiguang.li.R;
import com.zhiguang.li.utils.DateUtils;


public class GPSActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_view;
    private Button btnStart;
    private Button btnStop;
    private Location mLocation;
    private LocationManager mLocationManager;
    private Criteria criteria;
    private String provider;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_layout_activity);

        tv_view = (TextView) findViewById(R.id.text);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStop:
                mLocationManager.removeUpdates(locationListener);
                gpsIsOpen();

                break;
            case R.id.btnStart:
                if (!gpsIsOpen()) {
                    return;
                }
                mLocation = getLocation();
                if (mLocation != null) {
                    tv_view.setText("维度:" + location.getLatitude() + "\n经度:"
                            + location.getLongitude() + "\n时间:" + DateUtils.formatDate(location.getTime(), DateUtils.FORMAT_YMDHM));
                } else {
                    tv_view.setText("获取不到数据");
                }
                break;
        }
    }

    // 判断当前是否开启GPS
    private boolean gpsIsOpen() {
        boolean bRet = true;
        LocationManager alm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (!alm.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "未开启GPS", Toast.LENGTH_SHORT).show();
            bRet = false;
        } else {
            Toast.makeText(this, "GPS已开启", Toast.LENGTH_SHORT).show();
            bRet = true;
        }
        return bRet;
    }


    private Location getLocation() {
        //获取位置管理服务
        mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        //查找服务信息
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); //定位精度: 最高
        criteria.setAltitudeRequired(false); //海拔信息：不需要
        criteria.setBearingRequired(false); //方位信息: 不需要
        criteria.setCostAllowed(true);  //是否允许付费
        criteria.setPowerRequirement(Criteria.POWER_LOW); //耗电量: 低功耗
        provider = mLocationManager.getBestProvider(criteria, true); //获取GPS信息
        mLocationManager.requestLocationUpdates(provider, 2000, 5, locationListener);
        location = mLocationManager.getLastKnownLocation(provider);
        return location;
    }

    //    改方法是等待GPS位置改变后得到新的经纬度
    private final LocationListener locationListener = new LocationListener() {

        public void onLocationChanged(Location location) {

            if (location != null) {
                tv_view.setText("维度:" + location.getLatitude() + "\n经度:"
                        + location.getLongitude() + "\n时间:" + DateUtils.formatDate(location.getTime(), DateUtils.FORMAT_YMDHM));//location.getTime();//当前时间
                Log.e("onLocationChanged", "维度:" + location.getLatitude() + "\n经度:"
                        + location.getLongitude() + "\n时间:"
                        + DateUtils.formatDate(location.getTime(), DateUtils.FORMAT_YMDHM));
            } else {
                tv_view.setText("获取不到数据" /*+ Integer.toString(nCount)*/);
            }
        }

        /**
         * GPS禁用时触发
         */
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
            Log.e("onLocationChanged", "onProviderDisabled");
        }

        /**
         * GPS开启时触发
         */
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
            Location location = mLocationManager.getLastKnownLocation(provider);
            Log.e("onLocationChanged", "onProviderEnabled");
        }

        /**
         * GPS状态变化时触发
         */
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                //GPS状态为可见时
                case LocationProvider.AVAILABLE:
                    Log.e("onLocationChanged", "当前GPS状态为可见状态");
                    break;
                //GPS状态为服务区外时
                case LocationProvider.OUT_OF_SERVICE:
                    Log.e("onLocationChanged", "当前GPS状态为服务区外状态");
                    break;
                //GPS状态为暂停服务时
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.e("onLocationChanged", "当前GPS状态为暂停服务状态");
                    break;
            }

        }
    };

    protected void onDestroy() {
        if (mLocationManager != null && locationListener != null) {
            mLocationManager.removeUpdates(locationListener);
        }
        super.onDestroy();
    }

    @Override
    public void doResult(int action) {
        super.doResult(action);
        Toast.makeText(GPSActivity.this, "RIGHT", Toast.LENGTH_SHORT).show();
        switch (action) {
            case RIGHT:
                Toast.makeText(this, "RIGHT", Toast.LENGTH_SHORT).show();
                break;
            case LEFT:
                Toast.makeText(this, "LEFT", Toast.LENGTH_SHORT).show();
                break;
            case TOP:
                Toast.makeText(this, "TOP", Toast.LENGTH_SHORT).show();
                break;
            case BOTTOM:
                Toast.makeText(this, "BOTTOM", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
