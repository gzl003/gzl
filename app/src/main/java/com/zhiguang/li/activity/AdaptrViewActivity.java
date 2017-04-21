package com.zhiguang.li.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.zhiguang.li.R;

/**
 * Created by 智光 on 2016/4/19.
 * AdapterViewFlipper 的使用
 */
public class AdaptrViewActivity extends FragmentActivity {
    private int[] myList;
    private AdapterViewFlipper adapterViewFlipper;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragmentManager = getSupportFragmentManager();
        setContentView(R.layout.adapterview_layout);
        adapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.my_adapterview);
        myList = new int[]{R.drawable.adapterview_1, R.drawable.adapterview_2, R.drawable.adapterview_4, R.drawable.adapterview_5, R.drawable.adapterview_6, R.drawable.adapterview_7};


        BaseAdapter adapter = new BaseAdapter() {

            @Override
            public int getCount() {
                return myList.length;
            }

            @Override
            public Object getItem(int position) {
                // TODO Auto-generated method stub
                return position;
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return position;
            }


            //该方法返回的View代表了每个列表项
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                // TODO Auto-generated method stub
                //创建一个ImageView
                ImageView imageView = new ImageView(AdaptrViewActivity.this);
                imageView.setImageResource(myList[position]);
                //设置ImageView的缩放类型
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                //为ImageView设置布局参数
                imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                return imageView;
            }
        };

        adapterViewFlipper.setAdapter(adapter);

    }

    public void prev(View source) {
//        adapterViewFlipper.setInAnimation(this, R.anim.slide_right_in);
//        adapterViewFlipper.setOutAnimation(this, R.anim.fragment_slide_left_out);
        //显示上一个组件
        adapterViewFlipper.showPrevious();
        //停止自动播放
        adapterViewFlipper.stopFlipping();
    }

    public void next(View source) {
//        adapterViewFlipper.setInAnimation(this, R.anim.fragment_slide_left_in);
//        adapterViewFlipper.setOutAnimation(this, R.anim.fragment_slide_right_out);
        //显示一个组件
        adapterViewFlipper.showNext();
        //停止自动播放
        adapterViewFlipper.stopFlipping();
    }

    public void auto(View source) {
//        adapterViewFlipper.setInAnimation(this, R.anim.slide_right_in);
//        adapterViewFlipper.setOutAnimation(this, R.anim.fragment_slide_left_out);
        //开始自动播放
        adapterViewFlipper.startFlipping();
    }

}
