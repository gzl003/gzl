package com.zhiguang.li.service;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *  * Created by 智光 on 2018/6/28 16:37
 *  Socket 使用   即套接字，是一个对 TCP / IP协议进行封装的编程调用接口（API）
 * 1， 即通过Socket，我们才能在Andorid平台上通过 TCP/IP协议进行开发
 * 2， Socket不是一种协议，而是一个编程调用接口（API），属于传输层（主要解决数据如何在网络中传输）
 */
public class LzgSocket extends Socket {
    private final String TAG = "LzgSocket";
    private String host;
    private int port;

    public LzgSocket(String host, int port) throws IOException {
        super(host, port);
        this.host = host;
        this.port = port;
    }


    @Override
    public void connect(SocketAddress endpoint) throws IOException {
        super.connect(endpoint);
        Log.e(TAG, "---> socket  " + endpoint.toString());
    }

    @Override
    public void connect(SocketAddress endpoint, int timeout) throws IOException {
        super.connect(endpoint, timeout);
        Log.e(TAG, "---> socket  " + endpoint.toString());
    }



    @Override
    public synchronized void close() throws IOException {
        Log.e(TAG, "---> socket  close");
    }
}
