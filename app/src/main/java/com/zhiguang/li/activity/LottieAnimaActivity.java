package com.zhiguang.li.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.RenderMode;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.model.layer.Layer;
import com.airbnb.lottie.value.LottieFrameInfo;
import com.airbnb.lottie.value.SimpleLottieValueCallback;
import com.jiongbull.jlog.JLog;
import com.zhiguang.li.R;

import java.util.List;

/**
 * 动画
 */
public class LottieAnimaActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_anima);
        lottieAnimationView = findViewById(R.id.red_package_anim);
        lottieAnimationView.setRenderMode(RenderMode.AUTOMATIC);
        animSet();
        findViewById(R.id.replace_pic_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                lottieAnimationView.updateBitmap("image_0", bitmap);
                lottieAnimationView.cancelAnimation();
                lottieAnimationView.resumeAnimation();
            }
        });
        findViewById(R.id.parabolic_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lottieAnimationView.pauseAnimation();
                lottieAnimationView.clearAnimation();
                lottieAnimationView.setAnimation("parabolic_amin.json");
                lottieAnimationView.playAnimation();
            }
        });
        findViewById(R.id.replay_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TextDelegate textDelegate = new TextDelegate(lottieAnimationView);
//                textDelegate.setCacheText(false);
//                textDelegate.setText("欢喜", "首映");
                lottieAnimationView.cancelAnimation();
                lottieAnimationView.clearAnimation();
                lottieAnimationView.setAnimation("red_package.json");
                lottieAnimationView.playAnimation();
            }
        });

    }

    @SuppressLint("RestrictedApi")
    private void animSet() {
        lottieAnimationView.addLottieOnCompositionLoadedListener(new LottieOnCompositionLoadedListener() {
            @Override
            public void onCompositionLoaded(LottieComposition composition) {
                List<Layer> layers = composition.getLayers();
                for (int i = 0; i < layers.size(); i++) {
                    Layer layer = layers.get(i);
                    JLog.d("layer.getName: " + layer.getName());
                    JLog.d("layer.getRefId: " + layer.getRefId());
                    JLog.d("layer.getLayerType: " + layer.getLayerType().name());
                    JLog.d("layer.getId: " + layer.getId());
                    if (layer.getLayerType() == Layer.LayerType.PRE_COMP) {

                    }

                }

            }
        });
        lottieAnimationView.addValueCallback(new KeyPath("欢喜.txt_1"), LottieProperty.TEXT, new SimpleLottieValueCallback<CharSequence>() {
            @Override
            public CharSequence getValue(LottieFrameInfo<CharSequence> frameInfo) {
                JLog.d("frameInfo.getStartValue: " + frameInfo.getStartValue());
                JLog.d("frameInfo.getInterpolatedKeyframeProgress: " + frameInfo.getInterpolatedKeyframeProgress());
                JLog.d("frameInfo.getEndValue: " + frameInfo.getEndValue());

                return frameInfo.getInterpolatedKeyframeProgress() + "";
            }
        });

        lottieAnimationView.addValueCallback(new KeyPath( "金额.text_1"), LottieProperty.TEXT, new SimpleLottieValueCallback<CharSequence>() {
            @Override
            public CharSequence getValue(LottieFrameInfo<CharSequence> frameInfo) {
                JLog.d("frameInfo.getStartValue: " + frameInfo.getStartValue());
                JLog.d("frameInfo.getInterpolatedKeyframeProgress: " + frameInfo.getInterpolatedKeyframeProgress());
                JLog.d("frameInfo.getEndValue: " + frameInfo.getEndValue());
                return "原价30元现20元";
            }
        });

        lottieAnimationView.addValueCallback(new KeyPath("说明.text_2"), LottieProperty.TEXT, new SimpleLottieValueCallback<CharSequence>() {
            @Override
            public CharSequence getValue(LottieFrameInfo<CharSequence> frameInfo) {
                JLog.d("frameInfo.getStartValue: " + frameInfo.getStartValue());
                JLog.d("frameInfo.getInterpolatedKeyframeProgress: " + frameInfo.getInterpolatedKeyframeProgress());
                JLog.d("frameInfo.getEndValue: " + frameInfo.getEndValue());
                return  "恭喜您获得红包";
            }
        });

        lottieAnimationView.addValueCallback(new KeyPath("标题.text_3"), LottieProperty.TEXT, new SimpleLottieValueCallback<CharSequence>() {
            @Override
            public CharSequence getValue(LottieFrameInfo<CharSequence> frameInfo) {
                JLog.d("frameInfo.getStartValue: " + frameInfo.getStartValue());
                JLog.d("frameInfo.getInterpolatedKeyframeProgress: " + frameInfo.getInterpolatedKeyframeProgress());
                JLog.d("frameInfo.getEndValue: " + frameInfo.getEndValue());
                return "购买连续包";
            }
        });
        //3D旋转 红包抛物线动效
        lottieAnimationView.addValueCallback(new KeyPath("小红包.png"), LottieProperty.TRANSFORM_POSITION, new SimpleLottieValueCallback<PointF>() {
            @Override
            public PointF getValue(LottieFrameInfo<PointF> frameInfo) {
                JLog.d("TRANSFORM_ROTATION.getStartValue: " + frameInfo.getStartValue().x);
                JLog.d("TRANSFORM_ROTATION.getInterpolatedKeyframeProgress: " + frameInfo.getInterpolatedKeyframeProgress());
                JLog.d("TRANSFORM_ROTATION.getEndValue: " + frameInfo.getEndValue().x);

                return frameInfo.getStartValue();
            }
        });
    }
}