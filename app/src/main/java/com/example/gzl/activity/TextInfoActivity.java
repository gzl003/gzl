package com.example.gzl.activity;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gzl.R;
import com.example.gzl.utils.GPSUtils;
import com.example.gzl.utils.SdCardUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by 智光 on 2016/4/20.
 */
public class TextInfoActivity extends BaseActivity {
//    final int RIGHT = 0;
//    final int LEFT = 1;
//    final int TOP = 2;
//    final int BOTTOM = 3;

    private String slicotext = "";
    //    private GestureDetector gestureDetector;//手势识别器
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private TextView text7;
    private TextView text8;
    private TextView text9;

    private float x1;
    private float y1;
    private float x2;
    private float y2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testinfo_activity_layout);
        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);
        text6 = (TextView) findViewById(R.id.text6);
        text7 = (TextView) findViewById(R.id.text7);
        text8 = (TextView) findViewById(R.id.text8);
        text9 = (TextView) findViewById(R.id.text9);

//        gestureDetector = new GestureDetector(this, onGestureListener);
        getphoneInfo();
        text2.setText("ip地址" + getPhoneIp());
        text3.setText("网络类型" + GetNetworkType());
        text4.setText("imei :  " + getIMIE());
//        getSim();
        isSDCard();
        text6.setText("本机的mac地址是：" + getMACAdress(this));
        getPermission();
        getLocation();
//        text7.setText(getTodayZero() + "");
        text9.setText(getTime(getTodayZero()));

    }

    /**
     * 获取 终端类型
     */
    public void getphoneInfo() {
        String phoneBornd = "安卓";
        phoneBornd = phoneBornd + android.os.Build.MODEL + ",版本" + android.os.Build.VERSION.RELEASE;
        text1.setText(phoneBornd);
    }

    /**
     * 网络类型
     */
    public String GetNetworkType() {
        String strNetworkType = "";

        NetworkInfo networkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = "WIFI";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();
                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = "3G";
                        } else {
                            strNetworkType = _strSubTypeName;
                        }

                        break;
                }
            }
        }
        return strNetworkType;
    }

    /**
     * 获取手机的IP
     *
     * @return
     */
    private String getPhoneIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        //if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet6Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 得到手机的IMEI
     *
     * @return
     */
    public String getIMIE() {
        String imei = "";
//        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        if (tm != null) {
//            imei = tm.getDeviceId();
//
//        }
        try {
            imei = ((TelephonyManager) this.getSystemService(TELEPHONY_SERVICE)).getDeviceId();
        } catch (Exception ex) {
            imei = "没有拿到";
        }
//        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        return imei;
    }

    /**
     * 判断是不是双双待
     *
     * @param context
     * @return
     */
//    public boolean isWCDMA(Context context) {
//        boolean isDouble = false;
//        Method method;
//        Object result_0;
//        Object result_1;
//        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        tm.getSimSerialNumber();
//        try {
//            //只要在反射getSimStateGemini 这个函数时报了错就是单卡手机（这是我自己的经验，不一定全正确）
//            method = TelephonyManager.class.getMethod("getSimStateGemini", new Class[]{int.class});
//            isDouble = true;
//            //获取SIM卡1
//            result_0 = method.invoke(tm, new Object[]{new Integer(0)});
//            //获取SIM卡1
//            result_1 = method.invoke(tm, new Object[]{new Integer(1)});
//        } catch (SecurityException e) {
//            isDouble = false;
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            isDouble = false;
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            isDouble = false;
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            isDouble = false;
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            isDouble = false;
//            e.printStackTrace();
//        } catch (Exception e) {
//            isDouble = false;
//            e.printStackTrace();
//        }
//        return isDouble;
//    }

    /**
     *
     */
//    public void getSim() {
//        String imis = null;
//        boolean platform = false;
//        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        //采用金立MTK平台的接口获得
//        try {
//            @SuppressWarnings("rawtypes")
//            Class clazz = Class.forName("android.telephony.TelephonyManager");
//            Method method = clazz.getMethod("getSubscriberIdGemini", int.class);
//            //获取到IMSI并将是否是支持平台的布尔值设置为true 即为支持平台
//            imis = (String) method.invoke(tm, 1);
//
//            platform = true;
//        } catch (Exception ex) {
//
//        }
//
//        //如果采用金立MTK平台的方法没有获取到IMSI
//        //采用金立承开高通平台的接口获取
//        try {
//            @SuppressWarnings("rawtypes")
//            //这一步利用反射方法获取得到金立，承高通获取IMSI的类和方法
//                    Class clazz = Class.forName("android.telephony.MSimTelephonyManager");
//            Method method = clazz.getMethod("getDefault", null);
//            Object object = method.invoke(clazz, null);
//            method = clazz.getMethod("getSubscriberId", int.class);
//            imis = (String) method.invoke(object, 1);
//            platform = true;
//        } catch (Exception ex) {
//
//        }
//
//    }

    /**
     * 判断SD卡是否存在
     */
    public void isSDCard() {
        String sdcard = null;
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            sdcard = "sd卡存在";
        } else {
            sdcard = "sd卡不存在";
        }
        Log.e("isSDCard", "isFirstSdcard" + SdCardUtils.isFirstSdcardMounted());
        Log.e("isSDCard", "isSecondSDcard" + SdCardUtils.isSecondSDcardMounted());
        Log.e("isSDCard", "内置" + SdCardUtils.getFirstExterPath());
        Log.e("isSDCard", "外置" + SdCardUtils.getSecondExterPath());
        text5.setText(sdcard + "内置" + SdCardUtils.getFirstExterPath() + "外置" + SdCardUtils.getSecondExterPath());
    }

    /**
     * 获取MAC地址
     * wifiMac
     * sim卡
     *
     * @param context
     */
    public String getMACAdress(Context context) {

        String mac = "";
        if (GetNetworkType().equals("WIFI")) {
            // 获取wifi管理器
            WifiManager wifiMng = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfor = wifiMng.getConnectionInfo();
            mac = wifiInfor.getMacAddress();
        } else {
            String str = "";
            try {
                Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
                InputStreamReader ir = new InputStreamReader(pp.getInputStream());
                LineNumberReader input = new LineNumberReader(ir);

                for (; null != str; ) {
                    str = input.readLine();
                    if (str != null) {
                        mac = str.trim();// 去空格
                        break;
                    }
                }
            } catch (IOException ex) {
                // 赋予默认值
                ex.printStackTrace();
            }
        }
        return mac;
    }

    /**
     * 得到应用的权限
     */
    public void getPermission() {
        boolean permission = false;
        String[] permissions = new String[0];
        String press = null;
        try {
            PackageManager pm = getPackageManager();
            permission = (PackageManager.PERMISSION_GRANTED == pm.checkPermission("android.permission.ACCESS_FINE_LOCATION", getPackageName()));

            PackageInfo pack = pm.getPackageInfo(getPackageName(), PackageManager.GET_PERMISSIONS);
            permissions = pack.requestedPermissions;
        } catch (Exception ex) {

        }
        if (permission) {
            Toast.makeText(this, "包含该权限", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "不包含该权限", Toast.LENGTH_SHORT).show();
        }
        if (permissions.length > 0) {
            for (int i = 0; i < permissions.length; i++) {
                if (i > 0) {
                    press = press + permissions[i] + "\n";
                } else {
                    press = permissions[i] + "\n";
                }
            }
            text7.setText(press);
        }

    }


    @Override
    public void doResult(int action) {
        super.doResult(action);
        switch (action) {
            case RIGHT:
                slicotext = slicotext + "\n" + "RIGHT";
                text7.setText(slicotext);
                break;
            case LEFT:
                slicotext = slicotext + "\n" + "LEFT";
                text7.setText(slicotext);
                break;
            case TOP:
                slicotext = slicotext + "\n" + "TOP";
                text7.setText(slicotext);
                break;
            case BOTTOM:
                slicotext = slicotext + "\n" + "BOTTOM";
                text7.setText(slicotext);
                break;

        }
    }

    /**
     * 获取经纬度
     */
    public void getLocation() {
        Location location = GPSUtils.getInstance().mLocation;
        if (location != null) {
            double latitude = location.getLatitude();//纬度
            double longitude = location.getLongitude();//经度
            text8.setText("经度：" + longitude + "\n" + "纬度：" + latitude);
        } else {
            text8.setText("经度：" + null + "\n" + "纬度：" + null);
        }
    }

    private String getRunningActivityName() {
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();
        return runningActivity;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 获取手机当前音量值
//        int i = getVolumeValue();
        switch (keyCode) {

            case KeyEvent.KEYCODE_VOLUME_DOWN:// 音量减小
                Toast.makeText(this, "当前音量值：减小 " + getVolumeValue(), Toast.LENGTH_SHORT).show();
                break;

            case KeyEvent.KEYCODE_VOLUME_UP: // 音量增大
                Toast.makeText(this, "当前音量值： 增加 " + getVolumeValue(), Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }

    public int getVolumeValue() {
        int volume = 0;
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //通话音量

//        int max = am.getStreamMaxVolume( AudioManager.STREAM_VOICE_CALL );
//
//        int current = am.getStreamVolume( AudioManager.STREAM_VOICE_CALL );


        //系统音量

        int max = am.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);

        int current = am.getStreamVolume(AudioManager.STREAM_SYSTEM);

        return current;
    }

    /**
     * 系统时间戳转换成日期
     *
     * @param timeStamp
     * @return
     */
    public static String formatData(long timeStamp) {
        if (timeStamp == 0) {
            return "";
        }
        String result = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        result = format.format(new Date(timeStamp));
        return result;
    }

    public static long getTodayZero() {
        Date date = new Date();
        long l = 24 * 60 * 60 * 1000; //每天的毫秒数
        //date.getTime()是现在的毫秒数，它 减去 当天零点到现在的毫秒数（ 现在的毫秒数%一天总的毫秒数，取余。），理论上等于零点的毫秒数，不过这个毫秒数是UTC+0时区的。
        //减8个小时的毫秒值是为了解决时区的问题。
        return (date.getTime() - (date.getTime() % l) - 8 * 60 * 60 * 1000);
    }

    public static String getTime(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = new Date(time);
        return format.format(d1);
    }

}
