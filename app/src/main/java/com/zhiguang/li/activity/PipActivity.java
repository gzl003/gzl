package com.zhiguang.li.activity;

import android.app.PictureInPictureParams;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Rational;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.zhiguang.li.R;

/**
 * @author :智光
 * @date :2021/8/12 10:56
 * @desc :
 */
public class PipActivity extends BaseActivity {
    PictureInPictureParams.Builder pipBuild = new PictureInPictureParams.Builder();
    Rational rational = new Rational(16, 9);
    private ConstraintLayout layout;
    private TextView textView;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;

//    private ConstraintSet constraintSet = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pip_layout);
        textView = findViewById(R.id.bottom_text);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);


        layout = findViewById(R.id.top_img);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    enterPictureInPictureMode(pipBuild.build());

                }
            }
        });
//        constraintSet.clone(layout);
//        setRequestedOrientation(Configuration.ORIENTATION_LANDSCAPE);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);

        if (isInPictureInPictureMode) {
            textView.setVisibility(View.GONE);
            textView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getBaseContext(), PipActivity.class));
                }
            }, 3000);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            //竖屏
            toSmall();
        }
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //横屏
            toFull();
        }

    }

    private void toSmall() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
//        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) layout.getLayoutParams();
//        params.width = (int) getResources().getDisplayMetrics().xdpi;
//        params.height = params.width * 9 / 16;
//        layout.setLayoutParams(params);

        Toast.makeText(this, "toSmall", Toast.LENGTH_SHORT).show();
        constraintSet.connect(textView8.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 16);
        constraintSet.connect(textView8.getId(), ConstraintSet.BOTTOM, textView9.getId(), ConstraintSet.TOP, 16);
        constraintSet.connect(textView9.getId(), ConstraintSet.TOP, textView8.getId(), ConstraintSet.BOTTOM, 16);
        constraintSet.connect(textView9.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 16);
        constraintSet.clear(textView10.getId(), ConstraintSet.TOP);
        constraintSet.connect(textView10.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 16);
        constraintSet.applyTo(layout);
    }

    private void toFull() {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(layout);
//        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) layout.getLayoutParams();
//        params.width = (int) getResources().getDisplayMetrics().xdpi;
//        params.height = (int) getResources().getDisplayMetrics().ydpi;
//        layout.setLayoutParams(params);

        Toast.makeText(this, "toFull", Toast.LENGTH_SHORT).show();
        constraintSet.connect(textView8.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 16);
        constraintSet.connect(textView8.getId(), ConstraintSet.BOTTOM, textView9.getId(), ConstraintSet.TOP, 16);
        constraintSet.connect(textView9.getId(), ConstraintSet.TOP, textView8.getId(), ConstraintSet.BOTTOM, 16);
        constraintSet.connect(textView9.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 16);
        constraintSet.connect(textView10.getId(), ConstraintSet.TOP, textView9.getId(), ConstraintSet.BOTTOM, 16);
        constraintSet.connect(textView10.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 16);
        int[] child = {textView8.getId(), textView9.getId(), textView10.getId()};
        float[] weights = {1, 1, 1};
        constraintSet.createVerticalChain(ConstraintSet.PARENT_ID, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, child, weights, ConstraintSet.VERTICAL);
        constraintSet.applyTo(layout);
    }
}
