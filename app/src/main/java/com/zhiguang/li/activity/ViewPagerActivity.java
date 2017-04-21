package com.zhiguang.li.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.zhiguang.li.R;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class ViewPagerActivity extends Activity {
    private ViewPager myViewPager;
    private RadioGroup pager_radio;
    private MyPagerAdapter myAdapter;
    private List<Integer> list;
    private List<ImageView> imgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_layout);
        initView();
        setOnListener();
    }

    private void setOnListener() {
        myAdapter = new MyPagerAdapter();
        myViewPager.setAdapter(myAdapter);
        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            boolean isAutoPlay;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                ((RadioButton) pager_radio.getChildAt(position)).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case 1:// 手势滑动，空闲中
                        isAutoPlay = false;
                        Log.e("viewpager", " 手势滑动，空闲中");
                        break;
                    case 2:// 界面切换中
                        isAutoPlay = true;
                        Log.e("viewpager", "界面切换中");
                        break;
                    case 0:// 滑动结束，即切换完毕或者加载完毕
                        // 当前为最后一张，此时从右向左滑，则切换到第一张
//                        Log.e("viewpager", "滑动结束，即切换完毕或者加载完毕");
//                        if (myViewPager.getCurrentItem() == myViewPager.getAdapter()
//                                .getCount() - 1 && !isAutoPlay) {
//                            myViewPager.setCurrentItem(0, false);
//                        }
//                        // 当前为第一张，此时从左向右滑，则切换到最后一张
//                        else if (myViewPager.getCurrentItem() == 0 && !isAutoPlay) {
//                            myViewPager.setCurrentItem(myViewPager.getAdapter()
//                                    .getCount() - 1, false);
//                        }
                        myViewPager.setCurrentItem(myViewPager.getCurrentItem() + 1);
                        break;
                }
            }
        });

        pager_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                myViewPager.setCurrentItem(checkedId, false);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

        }
        return super.onTouchEvent(event);
    }

    private void initView() {
        myViewPager = (ViewPager) findViewById(R.id.my_viewpager);
        pager_radio = (RadioGroup) findViewById(R.id.pager_radio);
        list = new ArrayList<Integer>();
        list.add(R.drawable.th);
        list.add(R.drawable.thb);
        list.add(R.drawable.thc);
        list.add(R.drawable.thd);
        list.add(R.drawable.tha);
        imgList = new ArrayList<ImageView>();
        ImageView img;
        RadioButton radioButton;
        for (int i = 0; i < list.size(); i++) {
            img = new ImageView(this);
            img.setImageResource(list.get(i));
            imgList.add(img);

            radioButton = new RadioButton(this);
            radioButton.setText(i + "");
            pager_radio.addView(radioButton);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imgList.get(position));
            return imgList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imgList.get(position));
        }
    }
}
