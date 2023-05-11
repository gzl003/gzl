package com.zhiguang.li.widget.tablayout;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import java.util.HashMap;

/**
 * @author :智光 on
 * @date :2020/11/10 11:55
 * @desc :
 */
public class MyPageTransformer implements ViewPager.PageTransformer {

    private static final String TAG = "MyPageTransformer";
    public static final float MAX_SCALE = 1.3f;

    private TabLayout mTabLayout;
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, Float> mLastMap = new HashMap<>();

    public MyPageTransformer(TabLayout mTabLayout) {
        this.mTabLayout = mTabLayout;
    }

    @Override
    public void transformPage(@NonNull View view, float v) {
        if (v > -1 && v < 1) {
            int currPosition = (int) view.getTag();
            final float currV = Math.abs(v);
            if (!mLastMap.containsKey(currPosition)) {
                mLastMap.put(currPosition, currV);
                return;
            }
            float lastV = mLastMap.get(currPosition);
            // 获取当前 TabView 的 TextView
            LinearLayout ll = (LinearLayout) mTabLayout.getChildAt(0);
            TabLayout.TabView tb = (TabLayout.TabView) ll.getChildAt(currPosition);
            View textView = tb.getTextView();

            // 先判断是要变大还是变小
            // 如果 currV > lastV，则为变小；如果 currV < lastV，则为变大
            if (currV > lastV) {
                // 变小
                textView.setScaleX(MAX_SCALE - (MAX_SCALE - 1.0f) * currV);
                textView.setScaleY(MAX_SCALE - (MAX_SCALE - 1.0f) * currV);

            } else if (currV < lastV) {
                float enterPercent = 1 - currV; // 进入屏幕的百分比
                // 变大
                textView.setScaleX(1.0f + (MAX_SCALE - 1.0f) * enterPercent);
                textView.setScaleY(1.0f + (MAX_SCALE - 1.0f) * enterPercent);
            }
            mLastMap.put(currPosition, currV);
        }
    }
}
