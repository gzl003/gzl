package com.zhiguang.li.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.zhiguang.li.Fragment.FragmentOne;
import com.zhiguang.li.R;
import com.zhiguang.li.widget.tablayout.MyPageTransformer;
import com.zhiguang.li.widget.tablayout.MyViewPagerAdapter;
import com.zhiguang.li.widget.tablayout.TabLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 仿优酷tab切换
 */
public class YkTopTabActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<FragmentOne> mFragmentList;
    private List<String> mPageTitleList = Arrays.asList("哈哈", "Android", "哈哈", "我是", "程序员",
            "真的是", "程序员", "哈哈", "hello", "world!");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yk_top_tab);
        initData();
        initView();

    }

    private void initData() {
        mFragmentList = new ArrayList<>();
        for (int i = 0; i < mPageTitleList.size(); i++) {
            mFragmentList.add(FragmentOne.newInstance(i));
        }
    }

    private void initView() {
        mViewPager = findViewById(R.id.vp_style_one_content);
        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(), mFragmentList, mPageTitleList));

        mTabLayout = findViewById(R.id.tl_style_one_tab);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.post(new Runnable() {
            @Override
            public void run() {
                setScale(0, MyPageTransformer.MAX_SCALE);
            }
        });

        mViewPager.setPageTransformer(false, new MyPageTransformer(mTabLayout));
    }

    /**
     * 将 Tab[index] 放大为初始的 scale 倍
     */
    private void setScale(int index, float scale) {
        LinearLayout ll = (LinearLayout) mTabLayout.getChildAt(0);
        TabLayout.TabView tb = (TabLayout.TabView) ll.getChildAt(0);
        View view = tb.getTextView();
        view.setScaleX(scale);
        view.setScaleY(scale);
    }
}