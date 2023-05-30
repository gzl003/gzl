package com.zhiguang.li.application;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.jiongbull.jlog.BuildConfig;
import com.jiongbull.jlog.JLog;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.https.HttpsUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.util.HashMap;
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
        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                // 内核初始化完成，可能为系统内核，也可能为系统内核
                Log.d("app", " onViewInitFinished is ");
            }

            /**
             * 预初始化结束
             * 由于X5内核体积较大，需要依赖网络动态下发，所以当内核不存在的时候，默认会回调false，此时将会使用系统内核代替
             * @param isX5 是否使用X5内核
             */
            @Override
            public void onViewInitFinished(boolean isX5) {
                Log.d("app", " onViewInitFinished is ");
                // 在调用TBS初始化、创建WebView之前进行如下配置
                HashMap map = new HashMap();
                map.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
                map.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
                QbSdk.initTbsSettings(map);
            }
        });
        QbSdk.setDownloadWithoutWifi(true);
        QbSdk.initForinitAndNotLoadSo(getAppContext());

//这个需要起一个线程单独去初始化吗？
        QbSdk.initX5Environment(this, null);
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
