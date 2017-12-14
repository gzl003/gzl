package com.zhiguang.li.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 *  * Created by 智光 on 2017/12/14 15:21
 *  
 */
public class GrallyPageTransfrom implements ViewPager.PageTransformer {

    private static final float min_scale = 0.85f;

    @Override
    public void transformPage(final View page, float position) {
        float scaleFactor = Math.max(min_scale, 1 - Math.abs(position));
//        float rotate = 20 * Math.abs(position);
        if (position < -1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
        } else if (position < 0) {
            page.setScaleX(scaleFactor);//X缩放
            page.setScaleY(scaleFactor);//Y缩放
//            page.setRotationY(rotate);//Y 旋转
        } else if (position >= 0 && position < 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
//            page.setRotationY(-rotate);
        } else if (position >= 1) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
//            page.setRotationY(-rotate);
        }
    }

}
