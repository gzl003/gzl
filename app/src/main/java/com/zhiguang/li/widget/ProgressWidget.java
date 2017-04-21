package com.zhiguang.li.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

import com.zhiguang.li.R;

/**
 * Created by 智光 on 2016/7/8
 */
public class ProgressWidget extends Dialog {
    private Context context;
    private RotateAnimation animation;
    private View image;

    public ProgressWidget(Context context, int theme) {
        super(context, theme);
        this.context = context;
        init();
    }


    private void init() {
        View vitwroot = LayoutInflater.from(context).inflate(R.layout.progress_widget, null);
        image = vitwroot.findViewById(R.id.xz_img);

        animation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF,
                5, Animation.RELATIVE_TO_SELF, 5);
        animation.setDuration(2000);//设置动画持续时间

        /** 常用方法 */
        animation.setRepeatCount(-1);//设置重复次数
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        animation.setStartOffset(0);//执行前的等待时间
        LinearInterpolator lir = new LinearInterpolator();
        animation.setInterpolator(lir);//匀速
        image.setAnimation(animation);
/** 开始动画 */

        animation.startNow();
/** 结束动画 */
//        animation.cancel();
        super.setContentView(vitwroot);
    }
}
