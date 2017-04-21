package com.zhiguang.li.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by 智光 on 2016/9/1
 */
public class SparkTextView extends TextView {
    public final static int PROGRESS_COUNT = 1000;

    private Matrix mGradientMatrix = null;
    private float progress = 0;
    private float mShimmerStep = 0;

    public SparkTextView(Context context) {
        super(context);
    }

    public SparkTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SparkTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setProgress(float progress) {
        //进度更新了以后，通过 invalidate 通知 View 更新自己的显示内容
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // View 加载以后， 创建一个 Matrix 实例， 后面就通过对它的变化来影响 LinearGradient
        mGradientMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // 动态设置 闪光条的宽度
        int shaderWidth = w / 3;

        //起始位置为负，让闪光默认不现实出来
        LinearGradient shader = new LinearGradient(-shaderWidth, 0, 0, 0,
                // 两头为文字的颜色， 中间 透明， 模拟光照在文字上
                new int[]{getCurrentTextColor(), Color.TRANSPARENT, getCurrentTextColor()},
                new float[]{0, 0.5f, 1},
                Shader.TileMode.CLAMP);

        getPaint().setShader(shader);

        // mShimmerStep 用于表示 每一个进度代表要移动多少的距离， 因为 Shader 默认要藏起来， 所以移动距离要多一个Shader 的宽度
        mShimmerStep = (1.0f * w + shaderWidth) / PROGRESS_COUNT;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        mGradientMatrix.setTranslate(progress * mShimmerStep, 0);

        getPaint().getShader().setLocalMatrix(mGradientMatrix);

        super.onDraw(canvas);
    }
}
