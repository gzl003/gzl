package com.zhiguang.li.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zhiguang.li.R;
import com.zhiguang.li.adapter.ThreadListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *  * Created by 智光 on 2019/3/25 12:04
 *  
 */
public class ThreadCycleActivity extends BaseActivity {

    private List<String> strings = new ArrayList<>();
    private RecyclerView thread_list;
    private Handler handler = new Handler();
    private Runnable dateRunable = new Runnable() {
        @Override
        public void run() {
            if (strings != null) {
                strings.clear();
                String num = String.valueOf(((Math.random() * 9 + 1) * 10000000));
                for (int i = 0; i < num.length(); i++) {
                    strings.add(String.valueOf(num.charAt(i)));
                }
                thread_list.getAdapter().notifyDataSetChanged();
                handler.postDelayed(dateRunable, 1500);
            }
        }
    };
    private MyThread myThread = new MyThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tread_cycle_activity);
        thread_list = findViewById(R.id.thread_list);
        thread_list.setLayoutManager(new LinearLayoutManager(this));
        thread_list.setAdapter(new ThreadListAdapter(this, strings));


        String num = String.valueOf(((Math.random() * 9 + 1) * 10000));
        for (int i = 0; i < num.length(); i++) {
            strings.add(String.valueOf(num.charAt(i)));
        }
        handler.postDelayed(dateRunable, 1500);

        myThread.start();
    }


    class MyThread extends Thread {

        @Override
        public void run() {
            super.run();
            for (; ; ) {
                Log.e("MyThread", "地SEI个数字 :" + strings.size());
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(dateRunable);
        dateRunable = null;
        myThread.interrupt();
        myThread = null;
        handler = null;
    }
}
