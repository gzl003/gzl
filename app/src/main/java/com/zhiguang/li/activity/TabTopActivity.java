package com.zhiguang.li.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.zhiguang.li.R;
import com.zhiguang.li.adapter.MyFragmentPagerAdapter;

/**
 * Android tab切换
 * TabLayout
 */
public class TabTopActivity extends AppCompatActivity {

    private ViewPager myViewpager;
    private TabLayout myTabLayout;
    private MyFragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_top);
        initView();
    }

    private void initView() {
        myTabLayout = (TabLayout) findViewById(R.id.tablayout);
        myViewpager = (ViewPager) findViewById(R.id.tab_viewpager);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), this);
        myViewpager.setAdapter(mAdapter);
        myViewpager.setOffscreenPageLimit(3);
        myTabLayout.setupWithViewPager(myViewpager);//绑定tablayout 和viewpager
        myTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        myTabLayout.setSelectedTabIndicatorHeight(20);



































    }
}
