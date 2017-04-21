package com.example.gzl.myapplication.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;

import com.example.gzl.myapplication.R;
import com.example.gzl.myapplication.adapter.MyRecycleAdapter;
import com.example.gzl.myapplication.widget.SuperSwipeRefreshLayout;

/**
 * SuperSwipeRefreshLayout 的使用方法
 * 支持长拉下拉
 */
public class SuperSwipeRefreshLayoutActivity extends AppCompatActivity implements SuperSwipeRefreshLayout.OnPullRefreshListener, SuperSwipeRefreshLayout.OnPushLoadMoreListener {

    private SuperSwipeRefreshLayout superSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private MyRecycleAdapter myAdapter;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            superSwipeRefreshLayout.setRefreshing(false);
        }
    };
    private Runnable lodrunnable = new Runnable() {
        @Override
        public void run() {
            superSwipeRefreshLayout.setLoadMore(false);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_super_swipe_refresh_layout);
        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.My_recycleview);
        superSwipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        TextView textview = new TextView(this);
        textview.setText("正在刷新");
        textview.setGravity(Gravity.CENTER);
        superSwipeRefreshLayout.setHeaderView(textview);
        TextView textview2 = new TextView(this);
        textview2.setText("正在加载更多");
        superSwipeRefreshLayout.setFooterView(textview2);
        superSwipeRefreshLayout.setOnPullRefreshListener(this);
        superSwipeRefreshLayout.setOnPushLoadMoreListener(this);
        superSwipeRefreshLayout.setTargetScrollWithLayout(true);
        myAdapter = new MyRecycleAdapter(this, MyRecycleAdapter.setData());
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(myAdapter);
    }

    /**
     * 开始刷新
     */
    @Override
    public void onRefresh() {
        handler.postDelayed(runnable, 3000);
        Log.e("SuperSwipeRefreshLayoutActivity","onRefresh");
    }

    /**
     * 下拉的距离
     *
     * @param distance
     */
    @Override
    public void onPullDistance(int distance) {
        Log.e("SuperSwipeRefreshLayoutActivity","onPullDistance 下拉的距离 :" + distance);
    }

    /**
     * 下拉过程中,下拉的距离是否能够触发刷新
     *
     * @param enable
     */
    @Override
    public void onPullEnable(boolean enable) {
        Log.e("SuperSwipeRefreshLayoutActivity","onPullEnable");
    }

    /**
     * 开始加载更多
     */
    @Override
    public void onLoadMore() {
        handler.postDelayed(lodrunnable, 3000);
    }

    /**
     * 上拉的距离
     *
     * @param distance
     */
    @Override
    public void onPushDistance(int distance) {

    }

    /**
     * 上拉过程中上拉的距离是否能够触发加载更多
     *
     * @param enable
     */
    @Override
    public void onPushEnable(boolean enable) {

    }
}
