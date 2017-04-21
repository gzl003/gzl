package com.example.gzl.myapplication.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

public class SyncHorizontalScrollView extends HorizontalScrollView {

    private View view;

    private int windowWitdh = 0;
    private Activity mContext;

    public void setSomeParam(View view, Activity context) {
        this.mContext = context;
        this.view = view;
        DisplayMetrics dm = new DisplayMetrics();
        this.mContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
        windowWitdh = dm.widthPixels;
    }

    public SyncHorizontalScrollView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public SyncHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }


    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

    }
}
