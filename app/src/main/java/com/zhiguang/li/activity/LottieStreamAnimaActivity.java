package com.zhiguang.li.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;

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
 * @author: 智光
 * @time: 2023/10/26 16:44
 * @desc: 清晰度切换动画
 */
public class LottieStreamAnimaActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_steam_anima);
        lottieAnimationView = findViewById(R.id.red_package_anim);
        lottieAnimationView.setRenderMode(RenderMode.AUTOMATIC);
        initAmin();
    }

    private void initAmin() {
        lottieAnimationView.addLottieOnCompositionLoadedListener(new LottieOnCompositionLoadedListener() {
            @SuppressLint("RestrictedApi")
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
        lottieAnimationView.addValueCallback(new KeyPath("biaoti#text_1"), LottieProperty.TEXT, new SimpleLottieValueCallback<CharSequence>() {
            @Override
            public CharSequence getValue(LottieFrameInfo<CharSequence> frameInfo) {
                JLog.d("frameInfo.getStartValue: " + frameInfo.getStartValue());
                JLog.d("frameInfo.getInterpolatedKeyframeProgress: " + frameInfo.getInterpolatedKeyframeProgress());
                JLog.d("frameInfo.getEndValue: " + frameInfo.getEndValue());

                return frameInfo.getInterpolatedKeyframeProgress() + "";
            }
        });


        lottieAnimationView.addValueCallback(new KeyPath("biaoti#text_1"), LottieProperty.TEXT, new SimpleLottieValueCallback<CharSequence>() {
            @Override
            public CharSequence getValue(LottieFrameInfo<CharSequence> frameInfo) {
                JLog.d("frameInfo.getStartValue: " + frameInfo.getStartValue());
                JLog.d("frameInfo.getInterpolatedKeyframeProgress: " + frameInfo.getInterpolatedKeyframeProgress());
                JLog.d("frameInfo.getEndValue: " + frameInfo.getEndValue());
                return "恭喜您获得红包";
            }
        });

    }
}
