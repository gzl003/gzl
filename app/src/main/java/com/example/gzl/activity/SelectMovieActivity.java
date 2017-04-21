package com.example.gzl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.gzl.R;
import com.example.gzl.widget.SelectEpisodWidget;

/**
 * Created by 智光 on 2016/4/12.
 * 电影选集
 */
public class SelectMovieActivity extends Activity implements View.OnClickListener, SelectEpisodWidget.CloseListener {

    private Button showview;
    private FrameLayout my_fram;
    private SelectEpisodWidget selectEpisodWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selemovie_layout);
        showview = (Button) findViewById(R.id.showview);
        showview.setText("添加");
        my_fram = (FrameLayout) findViewById(R.id.my_fram);
        selectEpisodWidget = new SelectEpisodWidget(SelectMovieActivity.this);
        selectEpisodWidget.setCloseListener(this);
        showview.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (selectEpisodWidget.isShown()) {
            if (selectEpisodWidget.mPopupWindow.isShowing()) {
                selectEpisodWidget.mPopupWindow.dismiss();
            }
            my_fram.removeAllViews();
            showview.setText("添加");
        } else {
            showview.setText("移除");
            my_fram.addView(selectEpisodWidget);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (selectEpisodWidget.mPopupWindow != null && selectEpisodWidget.mPopupWindow.isShowing() /*&& ev.getAction() == MotionEvent.ACTION_UP*/) {
            selectEpisodWidget.mPopupWindow.dismiss();
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (selectEpisodWidget.mPopupWindow != null && selectEpisodWidget.mPopupWindow.isShowing() /*&& event.getAction() == MotionEvent.ACTION_UP */) {
            selectEpisodWidget.mPopupWindow.dismiss();
            return true;
        }
        return super.onTouchEvent(event);
    }

    public String say() {


        return "";
    }


    @Override
    public void close() {
        my_fram.removeAllViews();
        showview.setText("添加");
    }
}
