package com.example.gzl.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by 智光 on 2017/3/24.
 */
public class SameCircle extends View {
    /**
     * view宽度
     */
    private int width;
    /**
     * view高度
     */
    private int height;
    /**
     * 椭圆的宽
     */
    private int widthRect;
    /**
     * 椭圆的宽
     */
    private int heigheRect;
    /**
     * 绘制画笔
     */
    private Paint rectPaint;
    /**
     * 循环绘制位置
     */
    private int pos = 0;
    /**
     * 椭圆的矩形
     */
    private RectF rectf;
    /**
     * 循环颜色
     */
    private String[] color = {"#00ffffff", "#33ffffff", "#66ffffff", "#99ffffff", "#ccffffff", "#ffffff"};

    public SameCircle(Context context) {
        this(context, null);
    }

    public SameCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SameCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //根据个人习惯设置  这里设置  如果是wrap_content  则设置为宽高200
        if (widthMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.AT_MOST) {
            width = 200;
        } else {
            width = MeasureSpec.getSize(widthMeasureSpec);
            height = MeasureSpec.getSize(heightMeasureSpec);
            width = Math.min(width, height);
        }

        widthRect = width / 8;   //椭圆的宽
        heigheRect = widthRect;  //椭圆的高   设置宽高相同的时候绘制出来的就是一个圆
        setMeasuredDimension(width, width);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制部分是关键了，椭圆有12个，我们不可能去一个一个的算出所有的矩形坐标，我们可以考虑
        //旋转下面的画布canvas来实现绘制，每次旋转30度  12个正好是360°
        //首先定义一个椭圆
        if (rectf == null) {
            rectf = new RectF((width - widthRect) / 2, 0, (width + widthRect) / 2, heigheRect);
        }
        for (int i = 0; i < 12; i++) {
            if (i - pos >= 5) {
                rectPaint.setColor(Color.parseColor(color[5]));
            } else if (i - pos >= 0 && i - pos < 5) {
                rectPaint.setColor(Color.parseColor(color[i - pos]));
            } else if (i - pos >= -7 && i - pos < 0) {
                rectPaint.setColor(Color.parseColor(color[5]));
            } else if (i - pos >= -11 && i - pos < -7) {
                rectPaint.setColor(Color.parseColor(color[12 + i - pos]));
            }
            canvas.drawOval(rectf, rectPaint);  //绘制
            canvas.rotate(30, width / 2, width / 2);    //旋转
        }
        pos++;
        if (pos > 11) {
            pos = 0;
        }
        postInvalidateDelayed(100);  //一个周期用时1200
    }
}
