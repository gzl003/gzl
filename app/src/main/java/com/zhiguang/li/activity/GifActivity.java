package com.zhiguang.li.activity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.zhiguang.li.R;

public class GifActivity extends AppCompatActivity {

    private ImageView longmao;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        longmao = (ImageView) findViewById(R.id.longmao);
        startImageAnimation();

    }

    /**
     * 开始图片动画
     */
    private void startImageAnimation() {
        AnimationDrawable animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.anmain_longmao);
        longmao.setImageDrawable(animationDrawable);
        animationDrawable.start();
    }
}
