package com.zhiguang.li.utils;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 *  * Created by 智光 on 2017/12/14 15:21
 *  
 */
public class GrallyPageTransfrom implements ViewPager.PageTransformer {

    private static final float MAX_SCALE = 1.0f;
    private static final float MIN_SCALE = 0.85f;

    @Override
    public void transformPage(final View page, float position) {
        if (position < -1) {
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
        } else if (position <= 1) {//a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0
            // [-1,1]
            if (position < 0) {
                float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                Log.e("transformPage  开始消失",  "\n transformPage>>>>>>>>>" + scaleFactor);
            } else {
                float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);
                page.setScaleX(scaleFactor);
                page.setScaleY(scaleFactor);
                Log.w("transformPage   开始出现   else {",  "\n transformPage>>>>>>>>>" + scaleFactor);
            }
        } else { // (1,+Infinity]
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);

        }
    }
}
