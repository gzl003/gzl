package com.zhiguang.li.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.zhiguang.li.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 智光 on 2016/5/3.
 */
public class DeawerEpisodeWidget extends RelativeLayout implements RadioGroup.OnCheckedChangeListener {
    private Context context;

    private RadioGroup radioGroup;
    private RadioButton rb_episode_sele;
    private RadioButton rb_episode_huaxu;
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private List<View> list;

    public DeawerEpisodeWidget(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public DeawerEpisodeWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public DeawerEpisodeWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.episode_drawer_widget, this);
        radioGroup = (RadioGroup) findViewById(R.id.drawer_rg);
        rb_episode_sele = (RadioButton) findViewById(R.id.rb_episode_sele);
        rb_episode_huaxu = (RadioButton) findViewById(R.id.rb_episode_huaxu);
        radioGroup.setOnCheckedChangeListener(this);
        viewPager = (ViewPager) findViewById(R.id.episode_pager);
        list = new ArrayList<View>();
        GridView gridview = new GridView(context);
        gridview.setNumColumns(5);
        gridview.setAdapter(new MyAdapter());
        ListView listView = new ListView(context);
        listView.setAdapter(new MyListAdapter());
        list.add(gridview);
        list.add(listView);
        myViewPagerAdapter = new MyViewPagerAdapter(list);
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_episode_sele:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rb_episode_huaxu:
                viewPager.setCurrentItem(1);
                break;
        }
    }

    class MyViewPagerAdapter extends PagerAdapter {

        private List<View> mViewLists = null;

        public MyViewPagerAdapter(List<View> viewLists) {
            // TODO Auto-generated constructor stub
            mViewLists = viewLists;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewLists.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mViewLists.get(position);
            container.addView(view, 0);//
            return view;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return (mViewLists == null ? 0 : mViewLists.size());
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(context);
            textView.setBackgroundColor(Color.parseColor("#dd0061"));
            textView.setText(position + "");
            return textView;
        }
    }

    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 50;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = new TextView(context);
            textView.setText(position + "");
            return textView;
        }
    }
}
