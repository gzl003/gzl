package com.zhiguang.li.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhiguang.li.modle.CityBean;
import com.zhiguang.li.utils.ScreenUtils;

import java.util.List;

/**
 * @author : 智光
 * @date: 2021/11/25 17:56
 * @desc:
 */
public class MyItemDecoration  extends RecyclerView.ItemDecoration {

    private List<CityBean.CitysBean> mCitys;
    private Context mContext;
    private Paint mBgPaint;//画背景
    private Paint mTextPaint;//画文字
    private int mTitleHeight;//Title的高度
    private final Paint mPaint;

    public MyItemDecoration(List<CityBean.CitysBean> citys, Context context) {
        mCitys = citys;
        mContext = context;
        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setColor(Color.GREEN);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(ScreenUtils.dip2px(mContext, 20));

        mTitleHeight = ScreenUtils.dip2px(mContext, 60);

        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = (parent.getLayoutManager()).getPosition(view);
        if (position == 0) {//第一个条目肯定需要Title
            outRect.set(0, mTitleHeight, 0, 0);
        } else if (mCitys.get(position).getPinyin().charAt(0) != mCitys.get(position - 1).getPinyin().charAt(0)) {
            //当前条目和上一个条目的第一个拼音不同时需要Title
            outRect.set(0, mTitleHeight, 0, 0);
        } else { //
            outRect.set(0, 0, 0, 3);
        }

    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        for (int i = 0; i < parent.getChildCount(); i++) {
            View child = parent.getChildAt(i);
            int position = (parent.getLayoutManager()).getPosition(child);
            c.drawRect(0, child.getBottom(), parent.getRight(), child.getBottom() + 3, mPaint);

            if (position == 0 || mCitys.get(position).getPinyin().charAt(0) != mCitys.get(position - 1).getPinyin().charAt(0)) {

                //画背景
                c.drawRect(0, child.getTop() - mTitleHeight, parent.getRight(), child.getTop(), mBgPaint);
                String c1 = String.valueOf(mCitys.get(position).getPinyin().charAt(0));
                Rect rect = new Rect();
                mTextPaint.getTextBounds(c1, 0, 1, rect);
                //画文字
                c.drawText(c1, 0, child.getTop() - (mTitleHeight / 2 - rect.height() / 2), mTextPaint);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //第一个可见条目的位置
        int position = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
        View firstChild = parent.getLayoutManager().findViewByPosition(position);
        View secondChild = parent.getLayoutManager().findViewByPosition(position + 1);
        if (secondChild.getTop() - firstChild.getTop() > firstChild.getHeight() * 2) {
            //当第二个title和第一个title重合时移动画板,产生动画效果
            c.translate(0, firstChild.getTop());
        }

        //画背景
        c.drawRect(0, 0, parent.getRight(), mTitleHeight, mBgPaint);
        String c1 = String.valueOf(mCitys.get(position).getPinyin().charAt(0));
        Rect rect = new Rect();
        mTextPaint.getTextBounds(c1, 0, 1, rect);
        //画文字
        c.drawText(c1, 0, mTitleHeight / 2 + rect.height() / 2, mTextPaint);
    }
}
