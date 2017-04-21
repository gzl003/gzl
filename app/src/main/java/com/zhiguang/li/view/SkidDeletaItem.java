package com.zhiguang.li.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 *  * Created by 智光 on 2017/2/21 16:42
 *  策划删除的item
 * 此view 里面只能有两个子view 第一个是展示的view 第二个是侧滑内部的view
 */
public class SkidDeletaItem extends LinearLayout {

    private Scroller mScroller;// 滑动控制
    private float mLastMotionX;// 记住上次触摸屏的位置
    private int deletaX;
    private int back_width;//隐藏的宽度
    private float downX;
    public boolean isdelet = false;


    public SkidDeletaItem(Context context) {
        super(context);
        init(context);
    }

    public SkidDeletaItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SkidDeletaItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setIsdelet(boolean isdelet) {
        this.isdelet = isdelet;
        if (isdelet) {
            scrollTo(back_width, 0);
        } else {
            scrollTo(0, 0);
        }
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
    }

    /**
     * computeScroll在父控件执行drawChild时，会调用这个方法
     */
    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();//刷新界面
        }
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
            if (i == 1) {
                back_width = getChildAt(i).getMeasuredWidth();
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastMotionX = x;
                downX = x;

                break;
            case MotionEvent.ACTION_MOVE:
                deletaX = (int) (mLastMotionX - x);
                mLastMotionX = x;
                int scrollx = getScrollX() + deletaX;
                if (scrollx > 0 && scrollx < back_width) {
                    scrollBy(deletaX, 0);
                } else if (scrollx > back_width) {
                    scrollTo(back_width, 0);
                } else if (scrollx < 0) {
                    scrollTo(0, 0);
                }
                break;
            case MotionEvent.ACTION_UP:
                int scroll = getScrollX();
                if (scroll > back_width / 2) {
                    scrollTo(back_width, 0);
                    isdelet = true;

                } else {
                    scrollTo(0, 0);
                    isdelet = false;
                }
                if (Math.abs(x - downX) < 5) {
                    return false;
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                scrollTo(0, 0);
                break;
        }
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int margeLeft = 0;
        int size = getChildCount();
        for (int i = 0; i < size; i++) {
            View view = getChildAt(i);
            if (view.getVisibility() != View.GONE) {
                int childWidth = view.getMeasuredWidth();
                view.layout(margeLeft, 0, margeLeft + childWidth,
                        view.getMeasuredHeight());
                margeLeft += childWidth;
            }
        }
    }
}
