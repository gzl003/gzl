package com.zhiguang.li.activity;

import android.app.Activity;
import android.os.Bundle;
import androidx.core.widget.NestedScrollView;
import android.util.Log;

import com.zhiguang.li.R;

public class ScrollingActivity extends Activity implements NestedScrollView.OnScrollChangeListener {
    public static final String TAG = "nestedScrollView";
    NestedScrollView nestedScrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedview);
        nestedScrollView.setOnScrollChangeListener(this);



    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        Log.e(TAG, "onScrollChange ++++++ scrollX + " + scrollX + "      scrollY + " + scrollY + "     oldScrollX + " + oldScrollX + "    oldScrollY + " + oldScrollY);
    }


}
