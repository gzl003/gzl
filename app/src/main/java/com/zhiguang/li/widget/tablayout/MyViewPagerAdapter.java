package com.zhiguang.li.widget.tablayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.zhiguang.li.Fragment.FragmentOne;

import java.util.List;

/**
 * @author Feng Zhaohao
 * Created on 2018/9/2
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private List<FragmentOne> mFragmentList;   //碎片集合
    private List<String> mPageTitleList;    //tab的标题

    public MyViewPagerAdapter(FragmentManager fm, List<FragmentOne> mFragmentList, List<String> mPageTitleList) {
        super(fm);
        this.mFragmentList = mFragmentList;
        this.mPageTitleList = mPageTitleList;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    /**
     * 当TabLayout与ViewPager绑定的时候能够绑定Tab标签的标题
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mPageTitleList.get(position);
    }
}
