package com.example.gzl.Fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;

import com.example.gzl.R;
import com.example.gzl.activity.TabVpScrActivity;

/**
 * Created by 智光 on 2016/4/6
 */
public class FragmentOne extends Fragment implements View.OnClickListener {

    private Button fenlei_one;
    private View rootview;

    private int page;

    public static FragmentOne newInstance(int pager) {
        FragmentOne fragment = new FragmentOne();
        Bundle args = new Bundle();
        args.putInt("page", pager);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(TabVpScrActivity.TAG, "FragmentOne onAttach");
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
        Log.e(TabVpScrActivity.TAG, "FragmentOne onAttachFragment");
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        Log.e(TabVpScrActivity.TAG, "FragmentOne onCreateAnimation");
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.e(TabVpScrActivity.TAG, "FragmentOne onHiddenChanged");
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.e(TabVpScrActivity.TAG, "FragmentOne onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(TabVpScrActivity.TAG, "FragmentOne onActivityCreated");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.e(TabVpScrActivity.TAG, "FragmentOne onContextItemSelected");
        return super.onContextItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.e(TabVpScrActivity.TAG, "FragmentOne onConfigurationChanged");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(TabVpScrActivity.TAG, "FragmentOne onDetach");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TabVpScrActivity.TAG, "FragmentOne onViewCreated");
    }

    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(context, attrs, savedInstanceState);
        Log.e(TabVpScrActivity.TAG, "FragmentOne onInflate");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.e(TabVpScrActivity.TAG, "FragmentOne onLowMemory");
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        Log.e(TabVpScrActivity.TAG, "FragmentOne onMultiWindowModeChanged");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e(TabVpScrActivity.TAG, "FragmentOne onViewStateRestored");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e(TabVpScrActivity.TAG, "FragmentOne onRequestPermissionsResult");
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        Log.e(TabVpScrActivity.TAG, "FragmentOne setArguments");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        page = getArguments().getInt("page");
        Log.e(TabVpScrActivity.TAG, "FragmentOne onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootview == null) {
            rootview = inflater.inflate(R.layout.dialog_layput, null);
            Log.e(TabVpScrActivity.TAG, "FragmentOne onCreateView");
            init(rootview);
        }
        return rootview;
    }


    private void init(View view) {
        fenlei_one = (Button) view.findViewById(R.id.fenlei_one);
        fenlei_one.setText("我是二级分类");
        fenlei_one.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        ((FiltrateActivity)(getActivity())).filtrateDialog.switchFragment(1);
        fenlei_one.setTextColor(Color.RED);
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        Log.e(TabVpScrActivity.TAG, "FragmentOne setMenuVisibility");
        if (this.getView() != null) {
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }
}
