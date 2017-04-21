package com.zhiguang.li.activity;

import android.annotation.SuppressLint;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;

import com.zhiguang.li.R;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class WebViewRefshActivity extends AppCompatActivity /*implements SwipeRefreshLayout.OnRefreshListener*/ {

//    private String url = "https://182.92.134.2/mseasonform.do";
//    private String url = "https://www.baidu.com/";
//    private String url = "https://beta.huanxi.com/testh5/test/index.html";
    private String url = "https://www.sina.cn/testh5/test/index.html";
//    private String url = "https://beta.huanxi.com/myh5.html";

//    private ScrollWebView my_webview;
//    private SwipeRefreshLayout my_refreshview;
//    private WebSettings webSettings;
    private com.tencent.smtt.sdk.WebView tencent_webview;

//    private Handler handler = new Handler();
//    private Runnable runnable = new Runnable() {
//        @Override
//        public void run() {
//            handler.removeCallbacks(this);
//            my_refreshview.setRefreshing(false);
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_refsh);
        initvew();
    }

    private void initvew() {

        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        init();

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        // TODO Auto-generated method stub
        tencent_webview = (WebView) findViewById(R.id.tencent_webview);
        tencent_webview.loadUrl(url);
        WebSettings webSettings = tencent_webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        tencent_webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && tencent_webview.canGoBack()) {
            tencent_webview.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }










//        Intent intent= new Intent();
//        intent.setAction("android.intent.action.VIEW");
//        Uri content_url = Uri.parse(url);
//        intent.setData(content_url);
//        startActivity(intent);

//        my_webview = (ScrollWebView) findViewById(R.id.my_webview);
//        my_refreshview = (SwipeRefreshLayout) findViewById(R.id.my_refreshview);
//        my_refreshview.setOnRefreshListener(this);
//        my_webview.setSwipeRefreshLayout(my_refreshview);
//        my_webview.loadUrl(url);
//
//        webSettings = my_webview.getSettings();
//        webSettings.setJavaScriptEnabled(true);  //支持js
//        webSettings.setAllowFileAccess(true);// 设置允许访问文件数据(true);
//        webSettings.setDomStorageEnabled(true);
//        webSettings.setDatabaseEnabled(true);
//
//        webSettings.setUseWideViewPort(false);  //将图片调整到适合webview的大小
//        webSettings.setSupportZoom(true);  //支持缩放
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
//        webSettings.supportMultipleWindows();  //多窗口
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
//        webSettings.setAllowFileAccess(true);  //设置可以访问文件
//        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
//        webSettings.setBuiltInZoomControls(true); //设置支持缩放
//        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
//        my_webview.setWebChromeClient(new WebChromeClient());//webview只是一个承载体，各种内容的渲染需要使用webviewChromClient去实现，所以set一个默认的基类WebChromeClient就行，
//        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
//        my_webview.setWebViewClient(new WebViewClient(){
//
//            /**
//             * 重写此方法可以让webview处理https请求。
//             * @param view
//             * @param handler
//             * @param error
//             */
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                handler.proceed();
//            }
//
//            /**
//             * 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
//             * 这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的。
//             * @param view
//             * @param url
//             * @return
//             */
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                Log.e("shouldOverrideUrlLoading", "============" + url);
//                return true;
//            }
//
//            /**
//             * (更新历史记录)
//             * @param view
//             * @param url
//             * @param isReload
//             */
//            @Override
//            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
//                super.doUpdateVisitedHistory(view, url, isReload);
//            }
//
//            /**
//             * 应用程序重新请求网页数据
//             * @param view
//             * @param dontResend
//             * @param resend
//             */
//            @Override
//            public void onFormResubmission(WebView view, Message dontResend, Message resend) {
//                super.onFormResubmission(view, dontResend, resend);
//                Log.e("webview", "onFormResubmission" );
//            }
//
//            /**
//             * 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
//             * @param view
//             * @param url
//             */
//            @Override
//            public void onLoadResource(WebView view, String url) {
//                Log.e("webview", "onLoadResource" + url);
//                super.onLoadResource(view, url);
//            }
//
//            /**
//             * 这个事件就是开始载入页面调用的，通常我们可以在这设定一个loading的页面，告诉用户程序在等待网络响应。
//             * @param view
//             * @param url
//             * @param favicon
//             */
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                Log.e("webview", "onPageStarted");
////                if (webView.canGoBack()) {
////                    back_btn.setVisibility(View.VISIBLE);
////                } else {
////                    back_btn.setVisibility(View.GONE);
////                }
//                super.onPageStarted(view, url, favicon);
//            }
//
//            /**
//             * 在页面加载结束时调用。同样道理，我们知道一个页面载入完成，于是我们可以关闭loading 条，切换程序动作。
//             * @param view
//             * @param url
//             */
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                Log.e("webview", "onPageFinished");
//                super.onPageFinished(view, url);
//            }
//
//            /**
//             *  (报告错误信息)
//             * @param view
//             * @param request
//             * @param error
//             */
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//            }
//
//            /**
//             * （获取返回信息授权请求）
//             * @param view
//             * @param handler
//             * @param host
//             * @param realm
//             */
//            @Override
//            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
//                super.onReceivedHttpAuthRequest(view, handler, host, realm);
//            }
//
//            /**
//             * (WebView发生改变时调用)
//             * @param view
//             * @param oldScale
//             * @param newScale
//             */
//            @Override
//            public void onScaleChanged(WebView view, float oldScale, float newScale) {
//                Log.e("webview", "onScaleChanged");
//                super.onScaleChanged(view, oldScale, newScale);
//            }
//
//            /**
//             * （Key事件未被加载时调用）
//             * @param view
//             * @param event
//             */
//            @Override
//            public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
//                super.onUnhandledKeyEvent(view, event);
//                Log.e("webview", "onUnhandledKeyEvent");
//            }
//
//            /**
//             * 重写此方法才能够处理在浏览器中的按键事件。
//             * @param view
//             * @param event
//             * @return
//             */
//            @Override
//            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
//                Log.e("webview", "shouldOverrideKeyEvent");
//                return super.shouldOverrideKeyEvent(view, event);
//            }
//
//        });


//    @Override
//    public void onRefresh() {
//        Toast.makeText(this, "刷新", Toast.LENGTH_SHORT).show();
//        handler.postDelayed(runnable, 1500);
//    }
}
