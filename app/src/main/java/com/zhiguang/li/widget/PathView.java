package com.zhiguang.li.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.view.View;

/**
 * Created by 智光 on 2016/6/23.
 */
public class PathView extends View {

    float phase;
    PathEffect[] effects = new PathEffect[7];
    int[] colors;
    private Paint paint;
    Path path;

    public PathView(Context context) {
        super(context);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(2);
        //创建,并初始化Path
        path = new Path();
        path.moveTo(20, 20);
//        for (int i = 1; i <= 4; i++) {
        //生成15个点,随机生成它们的坐标,并将它们连成一条Path
//            path.lineTo(i * 20, (float) Math.random() * 600);
        path.lineTo(100, 20);
        path.lineTo(100, 100);
        path.lineTo(20, 100);
        path.lineTo(20, 20);
//        }
        //初始化七个颜色
        colors = new int[]{
                Color.BLACK, Color.BLUE, Color.CYAN,
                Color.GREEN, Color.MAGENTA, Color.RED, Color.YELLOW
        };
    }

    protected void onDraw(Canvas canvas) {
        //将背景填充成白色
        canvas.drawColor(Color.WHITE);
        //-------下面开始初始化7中路径的效果
        //使用路径效果
        effects[0] = null;
        //使用CornerPathEffect路径效果
        effects[1] = new CornerPathEffect(10);
        //初始化DiscretePathEffect
        effects[2] = new DiscretePathEffect(3.0f, 5.0f);
        //初始化DashPathEffect
        effects[3] = new DashPathEffect(new float[]{20, 10, 5, 10}, phase);
        //初始化PathDashPathEffect
        Path p = new Path();
        p.addRect(0, 0, 3, 10, Path.Direction.CW);
        effects[4] = new PathDashPathEffect(p, 12, phase, PathDashPathEffect.Style.ROTATE);
        //初始化PathDashPathEffect
        effects[5] = new ComposePathEffect(effects[2], effects[4]);
        effects[6] = new SumPathEffect(effects[4], effects[3]);
        //将画布移到8,8处开始绘制
        canvas.translate(8, 8);
        //依次使用7中不同路径效果,7种不同的颜色来绘制路径
//        for (int i = 0; i < effects.length; i++) {
        paint.setPathEffect(effects[4]);
        paint.setColor(colors[4]);
        canvas.drawPath(path, paint);
        canvas.translate(0, 60);

        Path path2 = new Path();
        path2.moveTo(230, 230);

        path2.lineTo(730, 230);
        path2.lineTo(730, 880);
        path2.lineTo(230, 880);
        path2.lineTo(230, 230);

        paint.setPathEffect(effects[4]);
        paint.setColor(colors[4]);
        canvas.drawPath(path2, paint);
        canvas.translate(0, 60);
//        }
        //改变phase值,形成动画效果
        phase += 3;
        paint.setTextSize(60);
        paint.setPathEffect(effects[0]);
        paint.setColor(colors[0]);
        canvas.drawText("李", 240, 240, paint);
        paint.setPathEffect(effects[1]);
        paint.setColor(colors[1]);
        canvas.drawText("智", 240, 300, paint);
        paint.setPathEffect(effects[2]);
        paint.setColor(colors[2]);
        canvas.drawText("光", 240, 360, paint);


        paint.setPathEffect(effects[5]);
        paint.setColor(colors[5]);
        paint.setTextSize(200);
        canvas.drawText("哈哈", 240, 760, paint);

        invalidate();
    }
}

