package com.zhiguang.li.utils;

import android.content.Context;
import android.util.Log;
import android.view.OrientationEventListener;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 屏幕方向变化的管理类，只检测是否有四个角度的改变
 *
 * @author zhoujun
 */
public class OrientationManager extends OrientationEventListener {
    public static final int ORIENTATION_0 = 0;
    public static final int ORIENTATION_90 = 90;
    public static final int ORIENTATION_180 = 180;
    public static final int ORIENTATION_270 = 270;

    private static final String TAG = "OrientationManager";
    public static final int SENSOR_UNKNOWN = -1;
    public static final int SENSOR_SMALL = SENSOR_UNKNOWN + 1;
    public static final int SENSOR_FULL = SENSOR_SMALL + 1;

    @IntDef({SENSOR_UNKNOWN, SENSOR_SMALL, SENSOR_FULL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SensorOrientationType {
    }

    public interface OrientationListener {
        void onOrientationChange(@OrientationManager.SensorOrientationType int orientation);
    }

    private IOrientationListener mListener;

    /**
     * 当前旋转的角度
     */
    private int mCurrOrientation;

    public interface IOrientationListener {
        void onOrientationChanged(int orientation);
    }

    public OrientationManager(Context context) {
        super(context);
    }

    @Override
    public void onOrientationChanged(int orientation) {
        if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
            //手机平放
            return; // 手机平放时，检测不到有效的角度
        }
        // 只检测是否有四个角度的改变
        if (orientation > 350 || orientation < 10) { // 0度
            //手机顶部向上
            orientation = 0;
        } else if (orientation > 80 && orientation < 100) { // 90度
            //手机右边向上
            orientation = 90;
        } else if (orientation > 170 && orientation < 190) { // 180度
            //手机底边向上
            orientation = 180;
        } else if (orientation > 260 && orientation < 280) { // 270度
            //手机左边向上
            orientation = 270;
        } else {
            return;
        }
        if (mCurrOrientation == orientation) {
            return;
        }

        mCurrOrientation = orientation;

        if (mListener != null) {
            mListener.onOrientationChanged(orientation);
        }
        Log.i(TAG, "onOrientationChanged:" + orientation);
    }

    /**
     * 设置方向变化的监听器
     *
     * @param listener
     */
    public void setOrientationListener(IOrientationListener listener) {
        mListener = listener;
    }
}
