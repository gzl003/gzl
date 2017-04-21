package com.example.gzl.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.gzl.R;

/**
 *  * Created by 智光 on 2017/3/30 11:30
 *  
 */
public class LoadingImage extends RelativeLayout {

    private ImageView imageView;

    public LoadingImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    public LoadingImage(Context context) {
        super(context);
        initView(context);
    }

    public LoadingImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.loading_image, null);
        imageView = (ImageView) view.findViewById(R.id.loading_img);

        Animation rotate = AnimationUtils.loadAnimation(context, R.anim.rotate);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        imageView.startAnimation(rotate);
        addView(view);
    }
}
