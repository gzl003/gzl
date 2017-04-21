package com.example.gzl.network;

import android.content.Context;
import android.widget.Toast;

import com.example.gzl.utils.NetWorkState;

import java.util.Observable;
import java.util.Observer;

/**
 * 网络切换，辅助类
 * <p/>
 * Created by daniel on 9/9/15.
 */
public class PlayerNetworkHelper implements Observer {
    private boolean isFront = false;
    private Context mActivity;

    public PlayerNetworkHelper(Context activity) {
        mActivity = activity;
    }

    public void onResume() {
        isFront = true;

        if (NetWorkState.isWifi(mActivity.getApplicationContext())) {
            // WIFI

        } else if (NetWorkState.is3G(mActivity.getApplicationContext())) {
            // 3G

        } else {
            // 无网

        }
    }

    public void onPause() {
        isFront = false;
    }

    public void onCreate() {
        NetworkObservable.getInstance(mActivity).addObserver(this);
    }

    public void onDestroy() {
        NetworkObservable.getInstance(mActivity).deleteObserver(this);
    }

    /**
     * 网络变化监听
     */
    @Override
    public void update(Observable observable, Object data) {
//        UU.j(new Object[]{"player.update", data});
        if (data != null && data instanceof NetworkObservable.NetConnectStatus) {
            NetworkObservable.NetConnectStatus status = (NetworkObservable.NetConnectStatus) data;
            if (isFront) {
                doFront(status);
            } else {
                doBack(status);
            }
        }
    }

    private void doBack(NetworkObservable.NetConnectStatus status) {
        if (status == NetworkObservable.NetConnectStatus.CONNECTED_3G) {

        }
    }

    private void doFront(NetworkObservable.NetConnectStatus status) {
        switch (status) {
            case CONNECTED_3G: {

            }
            break;
            case CONNECTED_WIFI:
//

                break;
            case DISCONNECTED:
                Toast.makeText(mActivity.getApplicationContext(), "断网啦",
                        Toast.LENGTH_SHORT).show();
                break;
        }
    }
}