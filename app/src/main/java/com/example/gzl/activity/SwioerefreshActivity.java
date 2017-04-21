package com.example.gzl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.gzl.R;
import com.example.gzl.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class SwioerefreshActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {

    private SwipeRefreshLayout refreshLayout;
    private ListView listView;
    private MyAdapter myAdapter;
    private List<String> list;
    private Handler handler;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            myAdapter.setDataList(list);
            refreshLayout.setRefreshing(false);
            Log.e("swioe", "刷新结束");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swioerefresh);
        initView();
        initData();
    }

    private void initData() {
        list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add("text" + i);
        }
        myAdapter.setDataList(list);
        handler = new Handler();
    }

    private void initView() {
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);

        refreshLayout.setColorSchemeResources(R.color.red,
                R.color.button_yellow,
                R.color.green,
                R.color.xiankan_blue);
        refreshLayout.setSize(20);
//        refreshLayout.setProgressBackgroundColor(R.color.gray6);
//        refreshLayout.setPadding(20, 20, 20, 20);
//        refreshLayout.setProgressViewOffset(true, 100, 200);
        refreshLayout.setDistanceToTriggerSync(50);
//        refreshLayout.setProgressViewEndTarget(true, 100);//刷新图标距离顶部的位置
        refreshLayout.setOnRefreshListener(this);

        listView = (ListView) findViewById(R.id.my_listview);

        myAdapter = new MyAdapter(this);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onRefresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < 20; i++) {
                    list.add("text" + i);
                }
                refreshLayout.setRefreshing(true);

                handler.post(runnable);
            }
        }).start();
        Log.e("onRefresh", "耍撒户撒户撒胡");
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(SwioerefreshActivity.this, list.get(position), Toast.LENGTH_SHORT).show();
    }
}
