package com.example.gzl.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.example.gzl.Fragment.FragmentOne;

/**
 *  * 作者：user on 2016/10/12 15:03
 *  TabLayout的填充器
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private String tabTitle[] = new String[]{"我", "是", "一", "个", "程", "序", "哈哈哈", "猿", "我", "是", "一", "个", "程", "序", "猿", "我", "是", "一", "个", "程", "序", "猿"};

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    /**
     * 每个item
     *
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {

        return FragmentOne.newInstance(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        FragmentOne fragment = (FragmentOne) super.instantiateItem(container, position);
        return fragment;
    }


    @Override
    public int getCount() {
        return tabTitle.length;
    }

    /**
     * 返回每个title
     *
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
