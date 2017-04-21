package com.example.gzl.myapplication.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;


public class NetWorkChecker {

    public static AlertDialog checkNetWorkPlay(Context mContext, final NetWorkCheckListener l) {
        if (!NetWorkState.isNetworkAvailable(mContext)) {
//            Toast.makeText(mContext, R.string.network_invaild, Toast.LENGTH_SHORT).show();
            l.onFailed(false);
            return null;
        } else if (NetWorkState.is3G(mContext)) {

            return new AlertDialog.Builder(mContext)
                    .setTitle("网络提示")
                    .setMessage("你正在使用运营商网络")
                    .setPositiveButton("停止使用", new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            l.onFailed(true);
                        }
                    })
                    .setNegativeButton("继续使用", new OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            l.onNetWorkOk(true);
                        }
                    })
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            l.onFailed(true);
                        }
                    })
                    .show();

        } else {
            l.onNetWorkOk(false);
            return null;
        }
    }

    public static void checkNetWorkDownload(Context mContext, final NetWorkCheckListener l) {
        if (!NetWorkState.isNetworkAvailable(mContext)) {
//            Toast.makeText(mContext, R.string.network_invaild, Toast.LENGTH_SHORT).show();
            return;
        } else if (NetWorkState.is3G(mContext)) {

            new AlertDialog.Builder(mContext).setTitle("网络提示").setMessage("你正在使用运营商网络")
                    .setPositiveButton("停止使用", null)
                    .setNegativeButton("继续使用", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            l.onNetWorkOk(true);
                        }
                    }).show();

        } else {
            l.onNetWorkOk(false);
        }
    }

    public static void checkNetWorkPlayer(Context mContext, final NetWorkCheckListener l) {
        if (!NetWorkState.isNetworkAvailable(mContext)) {
//            Toast.makeText(mContext, R.string.network_invaild, Toast.LENGTH_SHORT).show();
            l.onFailed(false);
        } else if (NetWorkState.is3G(mContext)) {
            l.onNetWorkOk(true);
        } else {
            l.onNetWorkOk(false);
        }
    }

    public interface NetWorkCheckListener {
        // void onNetWorkUnavailable();
        // void onNetWorkIs3G();
        void onNetWorkOk(boolean is3G);

        // void onCancel();
        void onFailed(boolean is3G);
    }
}
