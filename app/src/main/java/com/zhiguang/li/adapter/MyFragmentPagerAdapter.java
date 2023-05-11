package com.zhiguang.li.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.zhiguang.li.Fragment.FragmentOne;

/**
 *  * 作者：user on 2016/10/12 15:03
 *  TabLayout的填充器
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private String tabTitle[] = new String[]{"哈哈哈", "我是", "一个", "程序猿", "哈哈哈", "我是", "一个", "程序猿"};

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
//        if (position == 1) {
//            return CinemaFragment.newInstance(position);
//        }
        return FragmentOne.newInstance(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
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
