package com.zhiguang.li.widget;

import android.content.Context;
import androidx.core.view.NestedScrollingParent;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

/**
 * 嵌套滚动
 * Created by 智光 on 2016/8/15.
 * 滑动的View必须使用RecycleView  listView 不能用
 * <p/>
 * 嵌套滑动具体点就是当子View有滑动的时候把滑动的情况告诉父View，
 * 让父View也可以做一些相应的滑动动作。父View做完动作之后又把滑动的情况告诉子View。
 * 可以看出子View是主动的一方，父View是被动的一方(父View也可能是父父View这里为了方便一点就直接说的是父View)。
 * 分两部分来介绍嵌套滑动。一个是简单的从源码的层次分析嵌套滑动，一个是具体的实例。
 */
public class NestedScrollWidget extends LinearLayout implements NestedScrollingParent {
    private int mTopViewHeight;
    private OverScroller mScroller;
    private View mTop;//顶部的View
    private View mNav;//中间的View
    private View mBtm;//底部的View

    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;
    private int mMaximumVelocity, mMinimumVelocity;

    private float mLastY;
    private boolean mDragging;

    public NestedScrollWidget(Context context) {
        super(context);
//        mScroller = new OverScroller(context);
    }

    public NestedScrollWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(LinearLayout.VERTICAL);
        mScroller = new OverScroller(context);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        mMaximumVelocity = ViewConfiguration.get(context)
                .getScaledMaximumFlingVelocity();
        mMinimumVelocity = ViewConfiguration.get(context)
                .getScaledMinimumFlingVelocity();
    }

    public NestedScrollWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        mScroller = new OverScroller(context);
    }


    private void initVelocityTrackerIfNotExists() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        initVelocityTrackerIfNotExists();
        mVelocityTracker.addMovement(event);
        int action = event.getAction();
        float y = event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished())
                    mScroller.abortAnimation();
                mLastY = y;
                return true;
            case MotionEvent.ACTION_MOVE:
                float dy = y - mLastY;
                if (!mDragging && Math.abs(dy) > mTouchSlop) {
                    mDragging = true;
                }
                if (mDragging) {
                    scrollBy(0, (int) -dy);
                }
                mLastY = y;
                break;
            case MotionEvent.ACTION_CANCEL:
                mDragging = false;
                recycleVelocityTracker();
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_UP:
                mDragging = false;
                mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
                int velocityY = (int) mVelocityTracker.getYVelocity();
                if (Math.abs(velocityY) > mMinimumVelocity) {
                    fling(-velocityY);
                }
                recycleVelocityTracker();
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * onFinishInflate
     * 布局加载完成
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTop = getChildAt(0);
        mNav = getChildAt(1);
        mBtm = getChildAt(2);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //不限制顶部的高度
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getChildAt(0).measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
        ViewGroup.LayoutParams params = mBtm.getLayoutParams();
        params.height = getMeasuredHeight() - mNav.getMeasuredHeight();
        setMeasuredDimension(getMeasuredWidth(), mTop.getMeasuredHeight() + mNav.getMeasuredHeight() + mBtm.getMeasuredHeight());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight = mTop.getMeasuredHeight();
    }

    public void fling(int velocityY) {
        mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopViewHeight);
        invalidate();
    }

    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > mTopViewHeight) {
            y = mTopViewHeight;
        }
        if (y != getScrollY()) {
            super.scrollTo(x, y);
        }
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            scrollTo(0, mScroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 有嵌套滑动到来了，问下该父View是否接受嵌套滑动
     *
     * @param child            嵌套滑动对应的父类的子类(因为嵌套滑动对于的父View不一定是一级就能找到的，可能挑了两级父View的父View，child的辈分>=target)
     * @param target           具体嵌套滑动的那个子类
     * @param nestedScrollAxes 支持嵌套滚动轴。水平方向，垂直方向，或者不指定
     * @return 是否接受该嵌套滑动
     */
    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return true;
    }

    /**
     * onStartNestedScroll返回true该函数会被调用。
     * 该父View接受了嵌套滑动的请求该函数调用。
     *
     * @param child  嵌套滑动对应的父类的子类(因为嵌套滑动对于的父View不一定是一级就能找到的，可能挑了两级父View的父View，child的辈分>=target)
     * @param target 具体嵌套滑动的那个子类
     * @param axes   支持嵌套滚动轴。水平方向，垂直方向，或者不指定
     */
    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
//        super.onNestedScrollAccepted(child, target, axes);
    }

    /**
     * 停止嵌套滑动
     *
     * @param child 具体嵌套滑动的那个子类
     */
    @Override
    public void onStopNestedScroll(View child) {
//        super.onStopNestedScroll(child);
    }

    /**
     * 嵌套滑动的子View在滑动之后报告过来的滑动情况
     *
     * @param target       具体嵌套滑动的那个子类
     * @param dxConsumed   水平方向嵌套滑动的子View滑动的距离(消耗的距离)
     * @param dyConsumed   垂直方向嵌套滑动的子View滑动的距离(消耗的距离)
     * @param dxUnconsumed 水平方向嵌套滑动的子View未滑动的距离(未消耗的距离)
     * @param dyUnconsumed 垂直方向嵌套滑动的子View未滑动的距离(未消耗的距离)
     */
    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    /**
     * 在嵌套滑动的子View未滑动之前告诉过来的准备滑动的情况
     *
     * @param target   具体嵌套滑动的那个子类
     * @param dx       水平方向嵌套滑动的子View想要变化的距离
     * @param dy       垂直方向嵌套滑动的子View想要变化的距离
     * @param consumed 这个参数要我们在实现这个函数的时候指定，回头告诉子View当前父View消耗的距离
     *                 consumed[0] 水平消耗的距离，consumed[1] 垂直消耗的距离 好让子view做出相应的调整
     */
    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
//        super.onNestedPreScroll(target, dx, dy, consumed);
        boolean hiddenTop = dy > 0 && getScrollY() < mTopViewHeight;
        boolean showTop = dy < 0 && getScrollY() >= 0 && !ViewCompat.canScrollVertically(target, -1);

        if (hiddenTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    /**
     * 嵌套滑动的子View在fling之后报告过来的fling情况
     *
     * @param target    具体嵌套滑动的那个子类
     * @param velocityX 水平方向速度
     * @param velocityY 垂直方向速度
     * @param consumed  子view是否fling了
     * @return true 父View是否消耗了fling
     */
    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    /**
     * 在嵌套滑动的子View未fling之前告诉过来的准备fling的情况
     *
     * @param target    具体嵌套滑动的那个子类
     * @param velocityX 水平方向速度
     * @param velocityY 垂直方向速度
     * @return true 父View是否消耗了fling
     */
    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        //down - //up+
        if (getScrollY() >= mTopViewHeight) return false;
        fling((int) velocityY);
        return true;
    }

    /**
     * 获取嵌套滑动的轴
     *
     * @see ViewCompat#SCROLL_AXIS_HORIZONTAL 垂直
     * @see ViewCompat#SCROLL_AXIS_VERTICAL 水平
     * @see ViewCompat#SCROLL_AXIS_NONE 都支持
     */
    @Override
    public int getNestedScrollAxes() {
        return 0;
    }
}
