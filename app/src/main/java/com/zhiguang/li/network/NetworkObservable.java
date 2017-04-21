package com.zhiguang.li.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.text.TextUtils;
import android.util.Log;

import com.zhiguang.li.utils.NetWorkState;

/**
 * 监听网络变化的Observable
 */
public class NetworkObservable extends java.util.Observable {
    private NetConnectStatus mState = NetConnectStatus.INITED;
    private volatile static NetworkObservable sInstance;
    private Context mContext;
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.i("NetworkObservable", "action: " + action);
            if (!TextUtils.isEmpty(action)
                    && action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                NetConnectStatus state = getNetConnectStatus();
                if (state != mState) {
                    mState = state;
                    setChanged();
                    notifyObservers(state);
                }
            }
        }
    };

    private NetworkObservable(Context context) {
        mContext = context;
        registerReceiver();
    }

    public static NetworkObservable getInstance(Context context) {
        if (sInstance == null) {

                if (sInstance == null) {
                    sInstance = new NetworkObservable(context);
                }

        }
        return sInstance;
    }

    public void onDestory() {
        deleteObservers();
        unregisterReceiver();
        sInstance = null;
    }

    private void registerReceiver() {
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mContext.registerReceiver(mReceiver, mFilter);
    }

    private void unregisterReceiver() {
        mContext.unregisterReceiver(mReceiver);
    }

    private NetConnectStatus getNetConnectStatus() {
        if (!NetWorkState.isNetworkAvailable(mContext)) {
            return NetConnectStatus.DISCONNECTED;
        } else if (NetWorkState.is3G(mContext)) {
            return NetConnectStatus.CONNECTED_3G;
        } else {
            return NetConnectStatus.CONNECTED_WIFI;
        }
    }

    public enum NetConnectStatus {
        INITED, DISCONNECTED, CONNECTED_WIFI, CONNECTED_3G
    }
}
