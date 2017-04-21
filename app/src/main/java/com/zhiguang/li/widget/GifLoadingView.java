package com.zhiguang.li.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.ant.liao.GifView;
import com.zhiguang.li.R;

/**
 *  * Created by 智光 on 2016/12/20 16:27
 *  
 */

public class GifLoadingView extends RelativeLayout {
    private Context mContext;
    private GifView gifView;

    public GifLoadingView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public GifLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public GifLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View view = View.inflate(mContext, R.layout.gif_layout, null);
        gifView = (GifView) view.findViewById(R.id.img_gif);
        gifView.setGifImage(R.drawable.huanxi_full_loading);
        gifView.showAnimation();
        addView(view);
    }
}
