package com.zhiguang.li.activity;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.jiongbull.jlog.JLog;
import com.zhiguang.li.R;
import com.zhiguang.li.utils.Utils;

import java.util.Random;

/**
 * 抛物线动画
 */
public class ParabolaActivity extends AppCompatActivity implements View.OnTouchListener {

    private Button btn;
    private RelativeLayout boot_layout;
    private AppCompatButton wallet;
    private AppCompatButton endBtn;
    private RelativeLayout rootLayout;
    private Random random = new Random();
    private PathMeasure mPathMeasure;
    /**
     * 贝塞尔曲线中间过程的点的坐标
     */
    private float[] mCurrentPosition = new float[2];

    private int _xDelta;
    private int _yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_parabola);
        Utils.countDevicePixels(this);

        boot_layout = (RelativeLayout) findViewById(R.id.boot_layout);
        rootLayout = (RelativeLayout) findViewById(R.id.root_layout);
        btn = (Button) findViewById(R.id.btn);
        wallet = (AppCompatButton) findViewById(R.id.iv_wallet);
        endBtn = (AppCompatButton) findViewById(R.id.iv_end);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                startShakeAnim();
//                for (int i = 0; i < 1; i++) {
//                    ImageView imgGold = new ImageView(ParabolaActivity.this);
//                    imgGold.setBackgroundResource(R.drawable.ic_launcher);
//                    imgGold.setVisibility(View.INVISIBLE);
//                    boot_layout.addView(imgGold);
//                    startParabolaAnim(imgGold, i);
//                }
                redAmin();
            }
        });


        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                150, 80);
        endBtn.setLayoutParams(layoutParams);
        endBtn.setOnTouchListener(this);
    }




//    private int count = 0;
//    private void startShakeAnim() {
//        final Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_1);
//        final Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_1);
//        final Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_2);
//        final Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_3);
//        wallet.startAnimation(animation1);
//        animation1.setAnimationListener(new AnimationListener() {
//
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                wallet.startAnimation(animation2);
//            }
//        });
//        animation2.setAnimationListener(new AnimationListener() {
//
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                wallet.startAnimation(animation3);
//            }
//        });
//        animation3.setAnimationListener(new AnimationListener() {
//
//            @Override
//            public void onAnimationStart(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                count++;
//                if (count == 5){
//                    count = 0;
//                    wallet.startAnimation(animation4);
//                } else {
//                    wallet.startAnimation(animation2);
//                }
//            }
//        });
//    }

    private void startParabolaAnim(final View view, int index) {
        final float x = wallet.getX() + Utils.dp2px(30, this);//起点x
        final float y = wallet.getY() + Utils.dp2px(30, this);//起点y
        final int maxHeight = (int) (random.nextInt(100) + y);
        final int xRandom = random.nextInt(200) + 300;
        final boolean isLeft = random.nextBoolean();

        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(2000);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            /**
             *
             * @param fraction   The fraction from the starting to the ending values
             * @param startValue The start value.
             * @param endValue   The end value.
             * @return
             */
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                PointF point = new PointF();


                if (isLeft) {
                    point.x = x - xRandom * fraction /** xRandomFrac*/;
                } else {
                    point.x = x + xRandom * fraction /** xRandomFrac*/;
                }
                if (fraction < 0.3) {
                    point.y = y + 0.5f * 200 * (fraction * 3) * (fraction * 3) + (-fraction) * Utils.dp2px(maxHeight, ParabolaActivity.this);
                } else {
                    view.bringToFront();
                    point.y = y + 0.5f * 200 * (fraction * 3) * (fraction * 3) + (fraction - 0.6f) * Utils.dp2px(maxHeight, ParabolaActivity.this);
                }
                JLog.d("fraction : " + fraction);
                JLog.d("point x : " + point.x);
                JLog.d("point y : " + point.y);
                return point;
            }
        });
        valueAnimator.setStartDelay(index * 20);
        valueAnimator.start();
        valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                if (animation.getAnimatedFraction() > 0.05) {
                    view.setVisibility(View.VISIBLE);
                }
                PointF point = (PointF) animation.getAnimatedValue();
                view.setX(point.x);
                view.setY(point.y);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                boot_layout.removeView(view);
            }

        });
    }

    private void redAmin() {
        ImageView goods = new ImageView(ParabolaActivity.this);
        goods.setBackgroundResource(R.drawable.ic_launcher);
        goods.setVisibility(View.VISIBLE);
        // (这个图片就是执行动画的图片，从开始位置出发，经过一个抛物线（贝塞尔曲线），移动到购物车里)
        rootLayout.addView(goods);
        //    二、计算动画开始/结束点的坐标的准备工作
        //得到父布局的起始点坐标（用于辅助计算动画开始/结束时的点的坐标）
        int[] parentLocation = new int[2];
        rootLayout.getLocationInWindow(parentLocation);
        //得到商品图片的坐标（用于计算动画开始的坐标）
        int startLoc[] = new int[2];
        wallet.getLocationInWindow(startLoc);
        //得到购物车图片的坐标(用于计算动画结束后的坐标)
        int endLoc[] = new int[2];
        endBtn.getLocationInWindow(endLoc);
        //    三、正式开始计算动画开始/结束的坐标
        //开始掉落的商品的起始点：商品起始点-父布局起始点+该商品图片的一半
        float startX = startLoc[0] - parentLocation[0] + wallet.getWidth() / 2;
        float startY = startLoc[1] - parentLocation[1] + wallet.getHeight() / 2;
        //商品掉落后的终点坐标：购物车起始点-父布局起始点+购物车图片的1/5
        float toX = endLoc[0] - parentLocation[0] + endBtn.getWidth() / 5;
        float toY = endLoc[1] - parentLocation[1];
        //    四、计算中间动画的插值坐标（贝塞尔曲线）（其实就是用贝塞尔曲线来完成起终点的过程）
        //开始绘制贝塞尔曲线
        Path path = new Path();
        //移动到起始点（贝塞尔曲线的起点）
        path.moveTo(startX, startY);
        //使用二次萨贝尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
//        path.quadTo((startX + toX) / 2, startY, toX, toY);
        path.cubicTo(startX , startY, toX + (startX - toX) / 2, toY - 550, toX, toY);
        //mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，
        // 如果是true，path会形成一个闭环
        mPathMeasure = new PathMeasure(path, false);
        //★★★属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
        valueAnimator.setDuration(1000);
        // 匀速线性插值器
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 当插值计算进行时，获取中间的每个值，
                // 这里这个值是中间过程中的曲线长度（下面根据这个值来得出中间点的坐标值）
                float value = (Float) animation.getAnimatedValue();
                // ★★★★★获取当前点坐标封装到mCurrentPosition
                // boolean getPosTan(float distance, float[] pos, float[] tan) ：
                // 传入一个距离distance(0<=distance<=getLength())，然后会计算当前距
                // 离的坐标点和切线，pos会自动填充上坐标，这个方法很重要。
                mPathMeasure.getPosTan(value, mCurrentPosition, null); //mCurrentPosition此时就是中间距离点的坐标值
                // 移动的商品图片（动画图片）的坐标设置为该中间点的坐标
                goods.setTranslationX(mCurrentPosition[0]);
                goods.setTranslationY(mCurrentPosition[1]);


            }
        });
        //   五、 开始执行动画
        valueAnimator.start();
        //   六、动画结束后的处理
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            //当动画结束后：
            @Override
            public void onAnimationEnd(Animator animation) {
                // 购物车的数量加1
                rootLayout.removeView(goods);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });


//        Animation animation = new FlipAnimation(0, 180, (float) (goods.getWidth() - goods.getX()) / 2,
//                (float) (goods.getHeight() - goods.getY()) / 2, 60f, true);
//        animation.setDuration(1000);
//        animation.setFillAfter(true);
//        animation.setInterpolator(new AccelerateInterpolator());
//        goods.startAnimation(animation);


//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(goods,"rotationZ",0,90,180,270);
//        objectAnimator.setDuration(1000);
//        objectAnimator.start();
        ObjectAnimator rotationX = ObjectAnimator.ofFloat(goods,"rotationX",0,90,180);
        rotationX.setDuration(1000);
        rotationX.start();
        ObjectAnimator rotationY = ObjectAnimator.ofFloat(goods,"rotationY",90,180,270);
        rotationY.setDuration(1000);
        rotationY.start();

        ObjectAnimator alpha = ObjectAnimator.ofFloat(goods,"alpha",0,1,0.6f);
        alpha.setDuration(1000);
        alpha.start();




    }




    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) endBtn
                        .getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) endBtn
                        .getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                endBtn.setLayoutParams(layoutParams);
                break;
            default:
                break;
        }
//        _root.invalidate();
        return true;
    }

}