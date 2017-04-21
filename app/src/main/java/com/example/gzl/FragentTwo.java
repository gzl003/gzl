package com.example.gzl;


import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * mainwebview
 * Created by zhaohua on 2016/3/17.
 */
public class FragentTwo extends Fragment {
    private WebView webView;
    private WebSettings webSettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_twolayout, container, false);
        webView = (WebView) view.findViewById(R.id.mainwebview);
//        view.setAnimation();
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);  //支持js
        webSettings.setUseWideViewPort(false);  //将图片调整到适合webview的大小
        webSettings.setSupportZoom(true);  //支持缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN); //支持内容重新布局
        webSettings.supportMultipleWindows();  //多窗口
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        webSettings.setAllowFileAccess(true);  //设置可以访问文件
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setBuiltInZoomControls(true); //设置支持缩放
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
//        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        loadWeb();
        webView.loadUrl("http://www.lmf9.com/");

        return view;
    }

    private void loadWeb() {
        webView.setWebViewClient(new WebViewClient() {
            /**
             * 在点击请求的是链接是才会调用，重写此方法返回true表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边。
             * 这个函数我们可以做很多操作，比如我们读取到某些特殊的URL，于是就可以不打开地址，取消这个操作，进行预先定义的其他操作，这对一个程序是非常必要的。
             * @param view
             * @param url
             * @return
             */
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                Log.e("shouldOverrideUrlLoading", "============" + url);
                return true;
            }

            /**
             * (更新历史记录)
             * @param view
             * @param url
             * @param isReload
             */
            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
            }

            /**
             * 应用程序重新请求网页数据
             * @param view
             * @param dontResend
             * @param resend
             */
            @Override
            public void onFormResubmission(WebView view, Message dontResend, Message resend) {
                super.onFormResubmission(view, dontResend, resend);
            }

            /**
             * 在加载页面资源时会调用，每一个资源（比如图片）的加载都会调用一次。
             * @param view
             * @param url
             */
            @Override
            public void onLoadResource(WebView view, String url) {
                Log.e("webview", "onLoadResource");
                super.onLoadResource(view, url);
            }

            /**
             * 这个事件就是开始载入页面调用的，通常我们可以在这设定一个loading的页面，告诉用户程序在等待网络响应。
             * @param view
             * @param url
             * @param favicon
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Log.e("webview", "onPageStarted");
//                if (webView.canGoBack()) {
//                    back_btn.setVisibility(View.VISIBLE);
//                } else {
//                    back_btn.setVisibility(View.GONE);
//                }
                super.onPageStarted(view, url, favicon);
            }

            /**
             * 在页面加载结束时调用。同样道理，我们知道一个页面载入完成，于是我们可以关闭loading 条，切换程序动作。
             * @param view
             * @param url
             */
            @Override
            public void onPageFinished(WebView view, String url) {
                Log.e("webview", "onPageFinished");
                super.onPageFinished(view, url);
            }

            /**
             *  (报告错误信息)
             * @param view
             * @param request
             * @param error
             */
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            /**
             * （获取返回信息授权请求）
             * @param view
             * @param handler
             * @param host
             * @param realm
             */
            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                super.onReceivedHttpAuthRequest(view, handler, host, realm);
            }

            /**
             * 重写此方法可以让webview处理https请求。
             * @param view
             * @param handler
             * @param error
             */
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
            }

            /**
             * (WebView发生改变时调用)
             * @param view
             * @param oldScale
             * @param newScale
             */
            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                Log.e("webview", "onScaleChanged");
                super.onScaleChanged(view, oldScale, newScale);
            }

            /**
             * （Key事件未被加载时调用）
             * @param view
             * @param event
             */
            @Override
            public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
                super.onUnhandledKeyEvent(view, event);
            }

            /**
             * 重写此方法才能够处理在浏览器中的按键事件。
             * @param view
             * @param event
             * @return
             */
            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                Log.e("webview", "shouldOverrideKeyEvent");
                return super.shouldOverrideKeyEvent(view, event);
            }

        });
    }
}
