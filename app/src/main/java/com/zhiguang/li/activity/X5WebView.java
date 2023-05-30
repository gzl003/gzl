package com.zhiguang.li.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.Button;

import com.tencent.smtt.export.external.interfaces.HttpAuthHandler;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.zhiguang.li.R;


/**
 * Created by 智光 on 2018/5/31 17:27
 *
 */
public class X5WebView extends BaseActivity {
    WebView webView;
    Button shuaxin;

    // ////////////////////////////////////////////////////////////////////////////////////////////////
    // Activity OnCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_x5layout);
        webView = (WebView) findViewById(R.id.x5wenview);
        webView.loadUrl( "javascript:window.location.reload(true)");

        new WebViewFactory().init(webView,"javascript:window.location.reload(true)",true,this);



        shuaxin = findViewById(R.id.shuaxin);

        getWindow().setFormat(PixelFormat.TRANSLUCENT);

        webView.getView().setOverScrollMode(View.OVER_SCROLL_ALWAYS);

        setWebView();
        shuaxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                webView.reload();
            }
        });
        webView.loadUrl("https://m.huanxi.com");
    }

    private void setWebView() {
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onLoadResource(WebView webView, String s) {
                super.onLoadResource(webView, s);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                return super.shouldOverrideUrlLoading(webView, s);
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
            }

            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView webView, String s) {
                return super.shouldInterceptRequest(webView, s);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
                return super.shouldInterceptRequest(webView, webResourceRequest);
            }

            @Override
            public void doUpdateVisitedHistory(WebView webView, String s, boolean b) {
                super.doUpdateVisitedHistory(webView, s, b);
            }

            @Override
            public void onFormResubmission(WebView webView, Message message, Message message1) {
                super.onFormResubmission(webView, message, message1);
            }

            @Override
            public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String s, String s1) {
                super.onReceivedHttpAuthRequest(webView, httpAuthHandler, s, s1);
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }

            @Override
            public void onScaleChanged(WebView webView, float v, float v1) {
                super.onScaleChanged(webView, v, v1);
            }

            @Override
            public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
                super.onUnhandledKeyEvent(webView, keyEvent);
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
                return super.shouldOverrideKeyEvent(webView, keyEvent);
            }

            @Override
            public void onTooManyRedirects(WebView webView, Message message, Message message1) {
                super.onTooManyRedirects(webView, message, message1);
            }

            @Override
            public void onReceivedLoginRequest(WebView webView, String s, String s1, String s2) {
                super.onReceivedLoginRequest(webView, s, s1, s2);
            }

            @Override
            public void onDetectedBlankScreen(String s, int i) {
                super.onDetectedBlankScreen(s, i);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        try {
            super.onConfigurationChanged(newConfig);
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public class WebViewFactory {

        public void init(com.tencent.smtt.sdk.WebView webView, String url, boolean flag, Context context) {

            com.tencent.smtt.sdk.WebSettings settings = webView.getSettings();
            settings.setBuiltInZoomControls(true);
            settings.setLayoutAlgorithm(com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
            settings.setBlockNetworkImage(false);
            settings.setSupportZoom(true);
            settings.setSupportMultipleWindows(true);
            settings.setAppCacheEnabled(true);
            //居中
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            settings.setSavePassword(false);
            settings.setSaveFormData(false);
            settings.setJavaScriptEnabled(true);
            settings.setAllowFileAccess(true);
            settings.setGeolocationEnabled(true);
            settings.setAppCacheMaxSize(Long.MAX_VALUE);
            settings.setDomStorageEnabled(true);
            settings.setPluginState(com.tencent.smtt.sdk.WebSettings.PluginState.ON_DEMAND);
            settings.setCacheMode(com.tencent.smtt.sdk.WebSettings.LOAD_NO_CACHE);
            String dir = context.getDir("database", Context.MODE_PRIVATE).getPath();
            //设置定位的数据库路径
            settings.setGeolocationDatabasePath(dir);
            settings.setJavaScriptCanOpenWindowsAutomatically(true);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            webView.requestFocus();
            webView.loadUrl(url);
        }
    }


}
