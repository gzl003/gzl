package com.zhiguang.li.activity;

import android.graphics.Matrix;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.zhiguang.li.R;
import com.zhiguang.li.widget.ZoomImageView;

public class ImageSuoFang extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private SeekBar seekBar;
    private ImageView imageView;
    private ZoomImageView zoomImageView;
    private Matrix mScaleMatrix = new Matrix();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_suo_fang);
        seekBar = (SeekBar) findViewById(R.id.suofangseekBar);
        imageView = (ImageView) findViewById(R.id.suofangimg);
        zoomImageView = (ZoomImageView) findViewById(R.id.zoomImage);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar.setProgress(0);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        /**
         * 设置缩放比例
         * scaleFactor 比例
         * getWidth() / 2   getHeight() / 2 缩放的位置 中心缩放
         */
//        mScaleMatrix.postScale(progress, progress,imageView.getWidth()/2,imageView.getHeight()/2);
//
//        imageView.setImageMatrix(mScaleMatrix);
        float cacle = (float) progress / 50;
        zoomImageView.setScale(cacle);
        Log.e("onProgressChanged", "progress" + cacle);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
