package com.zhiguang.li.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhiguang.li.R;
import com.zhiguang.li.adapter.ThreadListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 *  * Created by 智光 on 2019/3/25 12:04
 *  
 */
public class ThreadCycleActivity extends BaseActivity {

//    private List<String> strings = new ArrayList<>();
    private RecyclerView thread_list;
    public static ThreadLocal<List<String>> threadLocal = new ThreadLocal<List<String>>(){
        private List<String> strings;
        @Override
        protected List<String> initialValue() {
            strings = new ArrayList<>();
            String num = String.valueOf(((Math.random() * 9 + 1) * 10000000));
            for (int i = 0; i < num.length(); i++) {
                strings.add(String.valueOf(num.charAt(i)));
            }
            return strings;
        }

        @Override
        public void set(List<String> value) {
            threadLocal.set(value);
        }


    };
    /**
     * 当下载状态发送改变的时候回调
     */
    private ExecuteHandler handler = new ExecuteHandler();

    /**
     * 拿到主线程Looper
     */
    @SuppressLint("HandlerLeak")
    private class ExecuteHandler extends Handler {
        private ExecuteHandler() {
            super(Looper.getMainLooper());
        }

        @Override
        public void handleMessage(Message msg) {
            int type = (int) msg.obj;
            switch (type) {
                case 1:
                    break;
                case 2:
                    thread_list.getAdapter().notifyDataSetChanged();
                    break;
            }

        }

    }

    /**
     * 发送handler消息到主线程刷新UI
     */
    private void notifydateChanged(int type) {
        Message message = handler.obtainMessage();
        message.obj = type;
        handler.sendMessage(message);
    }

    private MyThread myThread = new MyThread();
    private DateThread dateThread = new DateThread();
    private OtherThread otherThread = new OtherThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tread_cycle_activity);
        thread_list = findViewById(R.id.thread_list);
        thread_list.setLayoutManager(new LinearLayoutManager(this));
        thread_list.setAdapter(new ThreadListAdapter(this, threadLocal.get()));


        dateThread.start();
        myThread.start();
        otherThread.start();
    }


    class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            for (; ; ) {
                Log.e("MyThread", "地SEI个数字 :" + threadLocal.get().size());
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (threadLocal.get()) {
                    List<String> list = threadLocal.get();
                    list.set(0, "我是测试数据");
                    threadLocal.set(list);
                }
                notifydateChanged(2);
            }
        }
    }
    class DateThread extends Thread {

        @Override
        public void run() {
            super.run();
            for (; ; ) {
                try {
                    sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e("MyThread", "地SEI个数字 :" + threadLocal.get().size());
                if (threadLocal.get() != null) {
                    synchronized (threadLocal.get()) {
                        threadLocal.set(null);
                        String num = String.valueOf(((Math.random() * 9 + 1) * 10000000));
                        for (int i = 0; i < num.length(); i++) {
                            threadLocal.get().add(String.valueOf(num.charAt(i)));
                        }
                    }
                    notifydateChanged(2);
                }
            }
        }
    }

    class OtherThread extends Thread {

        @Override
        public void run() {
            super.run();
            for (; ; ) {
                try {
                    sleep(1600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (threadLocal.get()){
                    threadLocal.get().set(0, "你是过来捣乱的吗");
                    threadLocal.get().set(1, "呵呵 去屎吧");
                }
                notifydateChanged(2);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myThread.interrupt();
        myThread = null;
        dateThread.interrupt();
        dateThread = null;
        otherThread.interrupt();
        otherThread = null;

        handler = null;
    }
}
