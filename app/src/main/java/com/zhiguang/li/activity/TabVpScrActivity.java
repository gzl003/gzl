package com.zhiguang.li.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTabHost;
import androidx.viewpager.widget.ViewPager;

import com.zhiguang.li.Fragment.FragmentOne;
import com.zhiguang.li.Fragment.FragmentTwo;
import com.zhiguang.li.MyFragment;
import com.zhiguang.li.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 仿照微信左右切换
 */
public class TabVpScrActivity extends AppCompatActivity {

    public static final String TAG = "tabviewpager";

    private List<Fragment> listFafment;
    private ViewPager mViewPager;
    private FragmentTabHost mTabHost;
    private Class<?> mTabFragments[] = {FragmentOne.class, FragmentTwo.class, MyFragment.class};
    private Fragment mFragment[] = {new FragmentOne(), new FragmentTwo(), new MyFragment()};
    private int mTabItemIcons[] = {R.drawable.main_tab_bigmovie_selector, R.drawable.main_tab_live_selector, R.drawable.main_tab_activity_selector};
    private String mTabTextStrings[] = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_vp_scr);
        listFafment = new ArrayList<>();
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mTabTextStrings = getResources().getStringArray(R.array.main_tab_title);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        initView();
        initEvent();
    }

    private void initView() {
        for (int i = 0; i < mTabFragments.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabTextStrings[i]).setIndicator(getTabItemView(i));
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            listFafment.add(mFragment[i]);
            mTabHost.addTab(tabSpec, mTabFragments[i], bundle);
        }
        mViewPager.setOffscreenPageLimit(mTabFragments.length);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return listFafment.get(position);
            }

            @Override
            public int getCount() {
                return listFafment.size();
            }
        });
    }

    private void initEvent() {

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                mViewPager.setCurrentItem(mTabHost.getCurrentTab());
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private View getTabItemView(int index) {
        if ((mTabTextStrings != null) && (mTabTextStrings.length > index)) {
            TextView tabItemView = new TextView(this);
            tabItemView.setGravity(Gravity.CENTER);
            tabItemView.setText(mTabTextStrings[index]);
            tabItemView.setTextColor(getResources().getColorStateList(R.color.tab_bar_title_color_selector));
            tabItemView.setTextSize(TypedValue.COMPLEX_UNIT_PX, this.getResources().getDimension(R.dimen.home_tab_bar_title_size));
            Drawable drawable = getResources().getDrawable(mTabItemIcons[index]);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tabItemView.setCompoundDrawables(null, drawable, null, null);
            tabItemView.setCompoundDrawablePadding(this.getResources().getDimensionPixelSize(R.dimen.home_tab_bar_title_padding));
            tabItemView.setBackgroundColor(this.getResources().getColor(R.color.xiankan_gray2)); ///设置点击的状态颜色值
            tabItemView.setPadding(0, 5, 0, 5);
            return tabItemView;
        }
        return null;
    }
}
