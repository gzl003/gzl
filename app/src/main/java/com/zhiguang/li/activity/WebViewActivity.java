package com.zhiguang.li.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.HttpAuthHandler;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import com.zhiguang.li.R;

import org.json.JSONException;
import org.json.JSONObject;

public class WebViewActivity extends Activity implements View.OnClickListener {
    private WebView webView;
    private WebSettings webSettings;
    private ProgressBar loding_progress;
    private Button back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        webView = findViewById(R.id.my_webview);
        back_btn = findViewById(R.id.back_btn);
        loding_progress = findViewById(R.id.loding_progress);
        back_btn.setOnClickListener(this);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);  //支持js
        webSettings.setSupportZoom(true);  //支持缩放
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL); //支持内容重新布局
        webSettings.setUseWideViewPort(true);//将图片调整到适合webview的大小
        webSettings.supportMultipleWindows();  //多窗口
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //关闭webview中缓存
        webSettings.setAllowFileAccess(true);  //设置可以访问文件
        webSettings.setNeedInitialFocus(true); //当webview调用requestFocus时为webview设置节点
        webSettings.setBuiltInZoomControls(true); //设置支持缩放
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        webSettings.setLoadsImagesAutomatically(true);  //支持自动加载图片
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setBlockNetworkImage(false);
        webView.addJavascriptInterface(new JavaScriptInterface(this), "HX");//js给Android传值 添加接收的接口

        loadWeb();
//        webView.loadUrl("http://www.lmf9.com/");
//        webView.loadUrl("https://qam.huanxi.com/h5/index/" + new Random());
        webView.loadUrl("https://m.huanxi.com/h5/ys-nb/#!/index?id=17");
//        webView.loadUrl("http://sina.com.cn");
//        webView.loadUrl("http://m.teteparts.com/discover.html");
    }

    /**
     * 接收js的方法
     */
    public class JavaScriptInterface {
        private Activity mActivity;

        public JavaScriptInterface(Activity activity) {
            mActivity = activity;
        }

        /**
         * js的方法
         *
         * @param str
         */
        @JavascriptInterface
        public void JSCallMovie1(String str) {
            try {
                JSONObject jsonObject = new JSONObject(str);
                String mid = jsonObject.optString("data-mid");
                String vtype = jsonObject.optString("data-mtype");
                Log.e("JavaScriptInterface", "JSCallMovie1======" + mid);
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            webView.post(new Runnable() {
//                @Override
//                public void run() {
//                    String dfdfd = "QWE";
//                    webView.loadUrl("javascript:shareCallback('" + dfdfd + "')");//Android 传值给js
//                }
//            });
        }

        @JavascriptInterface
        public void JSCallMovie2(String str) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(str);
                String mid = jsonObject.optString("data-mid");
                String vtype = jsonObject.optString("data-mtype");
                Log.e("JavaScriptInterface", "JSCallMovie2=====callShare======" + mid);
                Log.e("JavaScriptInterface", "JSCallMovie2======callShare====" + vtype);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void JSCallMovie3(String str) {
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(str);
                String mid = jsonObject.optString("data-mid");
                String vtype = jsonObject.optString("data-mtype");
                Log.e("JavaScriptInterface", "JSCallMovie3==callShare======" + mid);
                Log.e("JavaScriptInterface", "JSCallMovie3==callShare====" + vtype);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface
        public void JSCallBack() {
            Log.e("JavaScriptInterface", "JSCallHotComment");
            finish();
        }

        @JavascriptInterface
        public void JSCallMovieShare(String string) {
            Log.e("JavaScriptInterface", "JSCallMovieShare" + string);
        }

        @JavascriptInterface
        public void JSCallSpecialShare(String string) {
            Log.e("JavaScriptInterface", "JSCallSpecialShare" + string);
        }

        @JavascriptInterface
        public void JSCallComment(String string) {
            Log.e("JavaScriptInterface", "JSCallHotComment" + string);
        }

        @JavascriptInterface
        public void JSCallHotComment() {
            Log.e("JavaScriptInterface", "JSCallHotComment");
        }
    }

    private void loadWeb() {
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                if (fileChooserParams != null) {

                }
                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.e("webview", "onProgressChanged>>>>  " + newProgress);
            }
        });

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
//                view.loadUrl(url);
                Log.e("shouldOverrideUrlLoading", "============" + url);
                // 如下方案可在非微信内部WebView的H5页面中调出微信支付
//                if (url.startsWith("weixin://wap/pay?")) {
//                    Intent intent = new Intent();
//                    intent.setAction(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(url));
//                    startActivity(intent);
//
//                    return true;
//                }
                return super.shouldOverrideUrlLoading(view, url);
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
                Log.e("webview", "onLoadResource" + url);
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
                if (webView.canGoBack()) {
                    back_btn.setVisibility(View.VISIBLE);
                } else {
                    back_btn.setVisibility(View.GONE);
                }
                loding_progress.setVisibility(View.VISIBLE);
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
                loding_progress.setVisibility(View.GONE);
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

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_btn:
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    webView.destroy();
                }
                break;
        }
    }
}
