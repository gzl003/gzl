package com.example.gzl.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gzl.myapplication.R;
import com.example.gzl.myapplication.view.LoadingView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaohua on 2016/3/22.
 * 点赞动画
 */
public class LikeAnimationActivity extends Activity implements View.OnClickListener {
    private ViewPager mviewPager;
    private Button mButton;
    private List<View> viewList;
    private MpagerAdapter mAdapter;
    private ImageView imageView;
    private LoadingView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.likeanimation_activity_layout);
        mviewPager = (ViewPager) findViewById(R.id.viewpaget);
        mButton = (Button) findViewById(R.id.like_btn);
        mButton.setOnClickListener(this);
        viewList = new ArrayList<View>();
        setData();
        // 1.设置幕后item的缓存数目
        mviewPager.setOffscreenPageLimit(3);
        // 2.设置页与页之间的间距
        mviewPager.setPageMargin(1);
        mAdapter = new MpagerAdapter();
        mviewPager.setAdapter(mAdapter);


        // 3.将父类的touch事件分发至viewPgaer，否则只能滑动中间的一个view对象
//        mviewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return v.dispatchTouchEvent(event);
//            }
//        });

    }

    private void setData() {
        for (int i = 0; i < 1; i++) {
            imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.ic_launcher);
            viewList.add(imageView);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.like_btn:
                imageView = new ImageView(this);
//                imageView.setImageBitmap(ImagetUtils.getRadiosImage(getDrawable(R.drawable.image), 300));
                imageView.setImageResource(R.drawable.image);
                viewList.add(imageView);
                mAdapter.notifyDataSetChanged();
                mviewPager.setCurrentItem(viewList.size() - 1);
                break;
        }
    }

    /**
     * instantiateItem(ViewGroup, int)
     * destroyItem(ViewGroup, int, Object)
     * getCount()
     * isViewFromObject(View, Object)
     */
    public class MpagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(viewList.get(position));

        }

        @Override
        public int getItemPosition(Object object) {

            return super.getItemPosition(object);
        }


        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(viewList.get(position));
            viewList.get(position).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(LikeAnimationActivity.this, "我是第" + position + "个头像", Toast.LENGTH_SHORT).show();
                }
            });
            return viewList.get(position);
        }


    }
}
