package com.example.gzl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.gzl.R;

/**
 * Created by 智光 on 2016/6/14
 */
public class FollowAnmia extends Activity implements View.OnTouchListener {
    private Button button;
    ViewGroup _root;
    private int _xDelta;
    private int _yDelta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.followanmi_layout);
        button = (Button) findViewById(R.id.id_text);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                150, 50);

        button.setLayoutParams(layoutParams);
        button.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) button
                        .getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) button
                        .getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                button.setLayoutParams(layoutParams);
                break;
        }
//        _root.invalidate();
        return true;
    }
}
