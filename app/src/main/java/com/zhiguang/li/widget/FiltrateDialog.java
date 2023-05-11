package com.zhiguang.li.widget;


import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.zhiguang.li.Fragment.FragmentOne;
import com.zhiguang.li.Fragment.FragmentTwo;
import com.zhiguang.li.R;

/**
 * Created by 智光 on 2016/4/6.
 *
 */
public class FiltrateDialog extends Dialog implements View.OnClickListener {

    private FragmentManager mFragmentManager;
    private Button fenlei_one;
    private FragmentOne fragmentOne;
    private FragmentTwo fragmentTwo;
    private Context context;

    public FiltrateDialog(Context context, FragmentManager mFragmentManager) {
        super(context,R.style.Transparent);
        this.mFragmentManager = mFragmentManager;
        this.context = context;
        initView();
    }


    private void initView() {
        View mView = LayoutInflater.from(context).inflate(R.layout.dialog_layput, null);
        fenlei_one = (Button) mView.findViewById(R.id.fenlei_one);
        fenlei_one.setOnClickListener(this);
        fragmentOne = new FragmentOne();
        fragmentTwo = new FragmentTwo();
//        setLayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true
        mFragmentManager.beginTransaction().add(R.id.tab_framelayout,fragmentOne);
        mFragmentManager.beginTransaction().commit();
        super.setContentView(mView);
    }

    /**
     * 将所有的Fragment都置为隐藏状态
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (fragmentOne != null) {
            transaction.hide(fragmentOne);
        }
        if (fragmentTwo != null) {
            transaction.hide(fragmentTwo);
        }
//        if (personal_fragment != null) {
//            transaction.hide(personal_fragment);
//        }
    }

    /**
     * 切换fragment页面
     */
    public void switchFragment(int index) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 练习
                if (fragmentOne == null) {
                    fragmentOne = new FragmentOne();
                    if (!fragmentOne.isAdded()) {
                        transaction.add(R.id.tab_framelayout, fragmentOne);
//                        rg_title.setVisibility(View.GONE);
                    }
                } else {
                    transaction.show(fragmentOne);
//                    rg_title.setVisibility(View.GONE);
                }

                break;
            case 1:
                // 学习
                if (fragmentTwo == null) {
//                    study_fragment = new ActionGroupActivity();
                    fragmentTwo = new FragmentTwo();
                    if (!fragmentTwo.isAdded()) {
                        transaction.add(R.id.tab_framelayout, fragmentTwo);
//                        rg_title.setVisibility(View.GONE);
                    }
                } else {
                    transaction.show(fragmentTwo);
//                    rg_title.setVisibility(View.GONE);
                }
                break;
            case 2:
                //我
//                if (personal_fragment == null) {
//                    personal_fragment = new PersonalFragment();
//                    if (!personal_fragment.isAdded()) {
//                        transaction.add(R.id.tab_framelayout, personal_fragment);
////                        rg_title.setVisibility(View.VISIBLE);
//                    }
//                } else {
//                    transaction.show(personal_fragment);
////                    rg_title.setVisibility(View.VISIBLE);
//                }
                break;
        }
        transaction.commit();
    }


    @Override
    public void onClick(View v) {
        switchFragment(0);
    }
}
