package com.zhiguang.li.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiguang.li.R;
import com.zhiguang.li.utils.ScreenUtils;
import com.zhiguang.li.widget.pageindector.ColorBar;
import com.zhiguang.li.widget.pageindector.FixedIndicatorView;
import com.zhiguang.li.widget.pageindector.IndicatorViewPager;
import com.zhiguang.li.widget.pageindector.OnTransitionTextListener;
import com.zhiguang.li.widget.pageindector.ScrollBar;

/**
 *  * Created by 智光 on 2018/4/25 16:50
 *  
 */
public class PageIntedectorActivity extends BaseActivity {

    private IndicatorViewPager indicatorViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pageindector);
        initView();
    }

    private void initView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.moretab_viewPager);
        FixedIndicatorView fixedIndicatorView = (FixedIndicatorView) findViewById(R.id.moretab_indictor);

        float unSelectSize = 12;
        float selectSize = unSelectSize * 1.3f;
        fixedIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(0xFF2196F3, Color.GRAY).setSize(selectSize, unSelectSize));

        fixedIndicatorView.setScrollBar(new ColorBar(this, 0xFF2196F3, 10, ScrollBar.Gravity.BOTTOM));
        viewPager.setOffscreenPageLimit(2);
        indicatorViewPager = new IndicatorViewPager(fixedIndicatorView, viewPager);
        indicatorViewPager.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
        private String[] versions = {"Cupcake", "Donut", "Lolipop", "Marshmallow","哈哈","我","是","一个","程序员"};
        private String[] names = {"纸杯蛋糕", "甜甜圈", "闪电泡芙", "冻酸奶","哈哈","我","是","一个","程序员"};

        @Override
        public int getCount() {
            return versions.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.tab_top, container, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(versions[position]);

            int witdh = getTextWidth(textView);
            int padding = ScreenUtils.dip2px(getApplicationContext(), 8);
            //因为wrap的布局 字体大小变化会导致textView大小变化产生抖动，这里通过设置textView宽度就避免抖动现象
            //1.3f是根据上面字体大小变化的倍数1.3f设置
            textView.setWidth((int) (witdh * 1.3f) + padding);

            return convertView;
        }

        @Override
        public View getViewForPage(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = new TextView(container.getContext());
            }
            TextView textView = (TextView) convertView;
            textView.setText(names[position]);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.GRAY);
            return convertView;
        }

        @Override
        public int getItemPosition(Object object) {
            //这是ViewPager适配器的特点,有两个值 POSITION_NONE，POSITION_UNCHANGED，默认就是POSITION_UNCHANGED,
            // 表示数据没变化不用更新.notifyDataChange的时候重新调用getViewForPage
            return PagerAdapter.POSITION_UNCHANGED;
        }

        private int getTextWidth(TextView textView) {
            if (textView == null) {
                return 0;
            }
            Rect bounds = new Rect();
            String text = textView.getText().toString();
            Paint paint = textView.getPaint();
            paint.getTextBounds(text, 0, text.length(), bounds);
            int width = bounds.left + bounds.width();
            return width;
        }

    }
}
