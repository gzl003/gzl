package com.zhiguang.li.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zhiguang.li.R;
import com.zhiguang.li.service.LzgSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LzgSocketActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = "LzgSocketActivity";

    private LzgSocket socket;// Socket变量
    private ExecutorService mThreadPool;// 线程池 为了方便展示,此处直接采用线程池进行线程管理,而没有一个个开线程
    /**
     * 接收服务器消息 变量
     */
    private InputStream is;// 输入流对象
    private InputStreamReader isr;// 输入流读取器对象
    private BufferedReader br;
    private String response;// 接收服务器发送过来的消息
    /**
     * 发送消息到服务器 变量
     */
    private OutputStream outputStream;// 输出流对象

    private Button btnConnect, btnDisconnect, btnSend;// 连接 断开连接 发送数据到服务器 的按钮变量
    private TextView Receive, receive_message;// 显示接收服务器消息 按钮
    private EditText mEdit;// 输入需要发送的消息 输入框
    /**
     * 主线程Handler 用于将从服务器获取的消息显示出来
     */
    private Handler mMainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    receive_message.setText(response);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lzg_socket);
        btnConnect = (Button) findViewById(R.id.connect);
        btnDisconnect = (Button) findViewById(R.id.disconnect);
        btnSend = (Button) findViewById(R.id.send);
        mEdit = (EditText) findViewById(R.id.edit);
        receive_message = (TextView) findViewById(R.id.receive_message);
        Receive = (Button) findViewById(R.id.Receive);
        btnConnect.setOnClickListener(this);
        btnDisconnect.setOnClickListener(this);
        btnSend.setOnClickListener(this);
        Receive.setOnClickListener(this);
        // 初始化线程池
        mThreadPool = Executors.newCachedThreadPool();

    }

    private void initSocket() {
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 创建Socket对象 & 指定服务端的IP 及 端口号
                    socket = new LzgSocket("https://www.baidu.com/", 8989);
                    // 判断客户端和服务器是否连接成功
                    if (socket.isConnected()) {
                        Log.e(TAG, "---> socket 连接成功");
                    } else {
                        Log.e(TAG, "---> socket 连接失败");
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void receiveMessage() {
        // 利用线程池直接开启一个线程 & 执行该线程
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // 步骤1：创建输入流对象InputStream
                    is = socket.getInputStream();
                    // 步骤2：创建输入流读取器对象 并传入输入流对象
                    // 该对象作用：获取服务器返回的数据
                    isr = new InputStreamReader(is);
                    br = new BufferedReader(isr);

                    // 步骤3：通过输入流读取器对象 接收服务器发送过来的数据
                    response = br.readLine();

                    // 步骤4:通知主线程,将接收的消息显示到界面
                    Message msg = Message.obtain();
                    msg.what = 0;
                    mMainHandler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void sendMessage() {
        // 利用线程池直接开启一个线程 & 执行该线程
        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    // 步骤1：从Socket 获得输出流对象OutputStream
                    // 该对象作用：发送数据
                    outputStream = socket.getOutputStream();

                    // 步骤2：写入需要发送的数据到输出流对象中
                    outputStream.write((mEdit.getText().toString() + "\n").getBytes("utf-8"));
                    // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞

                    // 步骤3：发送数据到服务端
                    outputStream.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void disConnect() {
        try {
            // 断开 客户端发送到服务器 的连接，即关闭输出流对象OutputStream
            outputStream.close();

            // 断开 服务器发送到客户端 的连接，即关闭输入流读取器对象BufferedReader
            br.close();

            // 最终关闭整个Socket连接
            socket.close();

            // 判断客户端和服务器是否已经断开连接
            if (socket.isConnected()) {
                Log.e(TAG, "---> socket 连接成功");
            } else {
                Log.e(TAG, "---> socket 连接失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.connect:
                initSocket();
                break;
            case R.id.disconnect:
                disConnect();
                break;
            case R.id.send:
                sendMessage();
                break;
            case R.id.Receive:
                receiveMessage();
                break;


        }
    }
}
