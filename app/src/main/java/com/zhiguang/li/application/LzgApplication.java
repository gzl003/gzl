package com.zhiguang.li.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.jiongbull.jlog.BuildConfig;
import com.jiongbull.jlog.JLog;
import com.tencent.smtt.sdk.QbSdk;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

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
                .addInterceptor(new LoggerInterceptor("LZG_TAG"))
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
        x5init();
    }

    private void x5init() {
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。

        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

//            @Override
//            public void onViewInitFinished(boolean arg0) {
//                // TODO Auto-generated method stub
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//                Log.d("app", " onViewInitFinished is " + arg0);
//            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }

            @Override
            public void onViewInitFinished() {
                Log.d("app", " onViewInitFinished is ");
            }
        };
        //x5内核初始化接口
//        QbSdk.initX5Environment(getApplicationContext(),  cb);
        QbSdk.preInit(getApplicationContext());
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
