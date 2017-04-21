package com.example.gzl.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by zhaohua on 2016/3/23.
 */
public class ScreenUtils {
    /**
     * lzg
     * 获取屏幕的宽
     *
     * @param context
     * @return
     */
    public static float getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        return wm.getDefaultDisplay().getWidth();
    }

    public static float getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        return wm.getDefaultDisplay().getHeight();
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
