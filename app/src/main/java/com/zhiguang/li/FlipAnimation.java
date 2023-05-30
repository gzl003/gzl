package com.zhiguang.li;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/**
 * @author: 智光
 * @time: 2023/5/25 16:44
 * @desc: 功能描述
 */
public class FlipAnimation   extends Animation {
    private Camera camera;
    /**
     * 开始角度
     */
    private float fromDegree;
    /**
     * 结束角度
     */
    private float toDegree;
    /**
     * 中心点
     */
    private float centerX;
    private float centerY;
    private float depthZ;
    /**
     * 是否需要扭曲
     */
    private boolean reverse;

    public FlipAnimation(float fromDegree, float toDegree, float centerX, float centerY,
                         float depthZ, boolean reverse) {
        this.fromDegree = fromDegree;
        this.toDegree = toDegree;
        this.centerX = centerX;
        this.centerY = centerY;
        this.reverse = reverse;
        this.depthZ = depthZ;

        /**
         * 设置动画的差值器，决定了时间片的时间点
         */
        this.setInterpolator(new LinearInterpolator());
    }

    /**
     * 动画初始化函数
     */
    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        camera = new Camera();
    }

    /**
     * 动画调用的关键函数，在每一个动画时间片到达的时候调用，会调用多次，动画的差值器对它的执行频率直接影响
     *
     * @param interpolatedTime 0.0f ~ 1.0f
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        // 根据差值器差值，计算出该次显示，图片应该处于的角度
        float degree = fromDegree + (toDegree - fromDegree) * interpolatedTime;

        final Matrix matrix = t.getMatrix();
        camera.save();


        // 翻转过程中不断调整整个View的z轴景深，否则会出现翻转到一定位置，View消失，
        if (reverse) { // 0 - 90度时，调整z轴数值，让View往远离屏幕方向偏移
            camera.translate(depthZ * interpolatedTime, (1.0f - interpolatedTime), depthZ * interpolatedTime);
        } else {
            // 270 - 360度，View快翻转完成，调整z轴数值，让View往靠近屏幕
            //（即用户）方向偏移，z值逐步恢复到0
            camera.translate(0, (1.0f - interpolatedTime), depthZ * (1.0f - interpolatedTime));
        }
        camera.rotate(0,degree,degree);
        camera.getMatrix(matrix);  // 将camera旋转后参数与当前View的矩阵组合计算，得出新的矩阵
        camera.restore();

        // View的x左移到原点，y上移到原点，即让View中心位于坐标图原点位置
        // 以View中心为旋转中心
        matrix.preTranslate(-centerX, -centerY);
        // 旋转完成，恢复camera位置
        matrix.postTranslate(centerX, centerY);
    }

}
