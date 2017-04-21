package com.example.gzl.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.gzl.myapplication.widget.PathView;

/**
 * Created by 智光 on 2016/6/23
 */
public class PathLineActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pathline_layout);
        setContentView(new PathView(this));
    }
}
