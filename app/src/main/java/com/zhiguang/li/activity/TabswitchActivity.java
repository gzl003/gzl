package com.zhiguang.li.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.fragment.app.FragmentTabHost;
import androidx.appcompat.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TabHost;
import android.widget.TextView;

import com.zhiguang.li.Fragment.FragmentOne;
import com.zhiguang.li.Fragment.FragmentTwo;
import com.zhiguang.li.MyFragment;
import com.zhiguang.li.R;

/**
 * FrabmentTabHost 的使用
 */
public class TabswitchActivity extends AppCompatActivity {

    private Class<?> mTabFragments[] = {FragmentOne.class, FragmentTwo.class, MyFragment.class};
    private int mTabItemIcons[] = {R.drawable.main_tab_bigmovie_selector, R.drawable.main_tab_live_selector, R.drawable.main_tab_activity_selector};
    private String mTabTextStrings[] = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabswitch);
        mTabTextStrings = getResources().getStringArray(R.array.main_tab_title);
        FragmentTabHost mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);//android.R.id.tabcontent
        mTabHost.getTabWidget().setDividerDrawable(null);

        int count = mTabFragments.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTabTextStrings[i]).setIndicator(getTabItemView(i));
            if (tabSpec != null) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", i);
                mTabHost.addTab(tabSpec, mTabFragments[i], bundle);
            }
        }
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
