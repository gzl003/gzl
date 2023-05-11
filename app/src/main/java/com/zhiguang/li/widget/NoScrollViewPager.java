package com.zhiguang.li.widget;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;

/**
 * Created by 智光 on 2016/5/3.
 */
public class NoScrollViewPager extends ViewPager {
    private boolean isCanScroll = false;

    public NoScrollViewPager(Context context) {
        super(context);
    }

    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll) {
        this.isCanScroll = isCanScroll;
    }


    @Override
    public void scrollTo(int x, int y) {
        if (isCanScroll) {
            super.scrollTo(x, y);
        }
    }
}
