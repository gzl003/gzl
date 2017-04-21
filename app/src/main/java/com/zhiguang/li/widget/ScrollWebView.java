package com.zhiguang.li.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 *  * Created by 智光 on 2017/4/14 11:42
 *  
 */
public class ScrollWebView extends WebView {

    private SwipeRefreshLayout swipeRefreshLayout;

    public ScrollWebView(Context context) {
        super(context);
    }

    public ScrollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(this.getScaleY() == 1.0){
            swipeRefreshLayout.setEnabled(true);
        }else{
            swipeRefreshLayout.setEnabled(false);
        }
    }

//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean in = super.onInterceptTouchEvent(ev);
//        if (in) {
//            getParent().requestDisallowInterceptTouchEvent(true);
//            this.requestDisallowInterceptTouchEvent(true);
//        }
//        return false;
//    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }


}
