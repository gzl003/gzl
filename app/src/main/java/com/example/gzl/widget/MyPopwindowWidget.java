package com.example.gzl.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/**
 *  * Created by 智光 on 2016/11/30 11:35
 *  
 */

public class MyPopwindowWidget extends PopupWindow {
    private Context context;

    public MyPopwindowWidget(Context context) {
        super(context);
    }

    public MyPopwindowWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyPopwindowWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyPopwindowWidget(View contentView, int width, int height) {
        super(contentView, width, height);
    }

    public MyPopwindowWidget(int width, int height) {
        super(width, height);
    }

    public MyPopwindowWidget(View contentView) {
        super(contentView);
    }
}
