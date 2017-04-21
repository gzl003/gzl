package com.example.gzl.myapplication.application;

import android.app.Application;
import android.content.Context;

import com.jiongbull.jlog.BuildConfig;
import com.jiongbull.jlog.JLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by 智光 on 2016/7/7
 */
public class LzgApplication extends Application {
    private static LzgApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        JLog.init(this).setDebug(BuildConfig.DEBUG);
        JLog.getSettings().setDebug(true);

        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static LzgApplication getInstance() {
        return mInstance;
    }

    public static Context getAppContext() {
        if (mInstance != null) {
            return mInstance.getApplicationContext();
        }
        return null;
    }
}
