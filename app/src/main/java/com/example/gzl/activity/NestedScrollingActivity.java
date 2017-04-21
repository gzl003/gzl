package com.example.gzl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.gzl.R;
import com.example.gzl.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 嵌套滚动
 * <p/>
 * Android在support.v4包中为大家提供了两个非常神奇的类：
 * NestedScrollingParent
 * NestedScrollingChild
 */
public class NestedScrollingActivity extends Activity {
    private ListView my_bto_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scrolling);
        my_bto_list = (ListView) findViewById(R.id.my_bto_list);
        initData();
    }

    private void initData() {
        List<String> list = new ArrayList();
        for (int i = 0; i < 50; i++) {
            list.add("NestedScroll" + i);
        }
        MyAdapter myAdapter = new MyAdapter(this);
        myAdapter.setDataList(list);
        my_bto_list.setAdapter(myAdapter);
    }
}
