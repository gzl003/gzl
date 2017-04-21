package com.zhiguang.li.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.zhiguang.li.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 剧集的V
 */
public class EpisodeWidget extends RelativeLayout {
    private Context context;
    private ViewPager myViewPager;
    private PagerAdapter pageAdapter;
    private RadioGroup myRadioGroup;
    private List<String> list;

    private List<EpisodeGridview> pagerlist;
    private EpisodeGridview episodeGridview;

    public EpisodeWidget(Context context) {
        super(context);
        this.context = context;
        list = new ArrayList<String>();
        pagerlist = new ArrayList<EpisodeGridview>();
        initView();
        setDate();
    }

    public EpisodeWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public EpisodeWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void setListener() {
        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                myViewPager.setCurrentItem(checkedId);
                Log.e("episodeWidget", "RadioButton：" + checkedId);

            }
        });
    }

    private void setDate() {
        list = new ArrayList<String>();
        for (int i = 0; i < 91; i++) {
            list.add(i + 1 + "");
        }
        double c = (double) list.size() / 15;
        addRadioButton(myRadioGroup, c);//向RadioGroup中动态添加RadioButton
        cutDatelist(list, c);//分割集合数据
        pageAdapter = new PagerAdapter();
        myViewPager.setAdapter(pageAdapter);
    }

    /**
     * 分割数据 设置到每个view上
     *
     * @param list
     * @param c
     */
    private void cutDatelist(List<String> list, double c) {
        for (int i = 0; i < Math.ceil(c); i++) {
            if (i == Math.ceil(c) - 1) {
                episodeGridview = new EpisodeGridview(context, list.subList(i * 15, list.size()));
            } else {
                episodeGridview = new EpisodeGridview(context, list.subList(i * 15, (i + 1) * 15));
            }
            pagerlist.add(episodeGridview);
        }
    }

    /**
     * 向RadioGroup中动态添加RadioButton
     *
     * @param myRadioGroup
     */
    private void addRadioButton(RadioGroup myRadioGroup, double c) {
        myRadioGroup.removeAllViews();
        for (int i = 0; i < Math.ceil(c); i++) {
            RadioButton rb = new RadioButton(context);
            rb.setText("" + i);
            myRadioGroup.addView(rb);
            Log.e("episodeWidget", "RadioButton：");
        }
    }

    private void initView() {
        View view = View.inflate(context, R.layout.episode_widget, null);
        myViewPager = (ViewPager) view.findViewById(R.id.my_episode_pager);
        myRadioGroup = (RadioGroup) view.findViewById(R.id.my_radiogroup);
        setListener();
        addView(view);
    }


    class PagerAdapter extends android.support.v4.view.PagerAdapter {

        @Override
        public int getCount() {
            return pagerlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pagerlist.get(position));
            return pagerlist.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(pagerlist.get(position));
        }

    }

}
