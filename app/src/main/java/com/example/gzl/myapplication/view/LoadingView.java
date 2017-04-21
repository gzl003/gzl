package com.example.gzl.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 *  * Created by 智光 on 2017/3/24 17:09
 *  
 */
public class LoadingView extends View {

    private Paint paint;
    private int pos = 0;
    private String[] color = {"#00ffffff", "#33ffffff", "#66ffffff", "#99ffffff", "#ccffffff", "#ffffff"};
    private int maxRadius = 30;
    private int height;
    private int width;

    private float r;
    private boolean init = false;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        //设置为宽高120
//        width = 120;
//        setMeasuredDimension(width, width);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!init) {
            init = true;
            width = getWidth() / 2;
            height = getHeight() / 2;
            r = width - maxRadius;
        }

        for (int i = 0; i < 12; i++) {
            if (i - pos >= 5) {
                paint.setColor(Color.parseColor(color[5]));
            } else if (i - pos >= 0 && i - pos < 5) {
                paint.setColor(Color.parseColor(color[i - pos]));
            } else if (i - pos >= -7 && i - pos < 0) {
                paint.setColor(Color.parseColor(color[5]));
            } else if (i - pos >= -11 && i - pos < -7) {
                paint.setColor(Color.parseColor(color[12 + i - pos]));
            }
            canvas.drawCircle( (width + r ),  (height + r ), 18, paint);
            canvas.rotate(30, width / 2, width / 2);    //旋转
        }

        pos++;
        if (pos > 11) {
            pos = 0;
        }

        postInvalidateDelayed(80);  //一个周期用时1200

    }
}
