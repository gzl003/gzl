package com.example.gzl.activity;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gzl.R;
import com.example.gzl.widget.ptrecycleview.SwipeRefreshAdapterView;
import com.example.gzl.widget.ptrecycleview.SwipeRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 上拉下拉刷新的RecycleView
 * <p>
 * ptrecycleview
 */
public class PtrRecycleViewActivity extends AppCompatActivity implements SwipeRefreshAdapterView.OnListLoadListener, SwipeRefreshLayout.OnRefreshListener {
    public static final String TAG = "RecycleView";
    private SwipeRefreshRecyclerView mRecycleView;
    private RecyclerViewAdapter adapter;
    private List<String> objects = new ArrayList<>();
    private int footerIndex = 50;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptr_recycle_view);
        mRecycleView = (SwipeRefreshRecyclerView) findViewById(R.id.prt_recycleview);
        setReycleView();

    }

    private void setReycleView() {
        mRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mRecycleView.setOnListLoadListener(this);
        mRecycleView.setOnRefreshListener(this);

        for (int i = 0; i < footerIndex; i++) {
            objects.add("item:" + i);
        }
        adapter = new RecyclerViewAdapter(this);
        mRecycleView.setAdapter(adapter);
        mRecycleView.setEmptyText("数据又没有了!");
        mRecycleView.autoRefresh();

    }

    /**
     * 上拉加载
     */
    @Override
    public void onListLoad() {
        ++index;
        mRecycleView.postDelayed(new Runnable() {
            @Override
            public void run() {
                int count = footerIndex + 5;
                for (int i = footerIndex; i < count; i++) {
                    objects.add("上拉 = " + i);
                }
                footerIndex = count;
                adapter.notifyDataSetChanged();
                mRecycleView.setLoading(false);
//                if (index == 1) {
//                    mRecycleView.setLoadCompleted(true);
//                }
            }
        }, 2000);
        Log.e(TAG, "onListLoad");
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        index = 0;
        mRecycleView.postDelayed(new Runnable() {
            @Override
            public void run() {
                objects.add(0, "下拉刷新" + (--index));
                adapter.notifyDataSetChanged();
                mRecycleView.setRefreshing(false);
            }
        }, 1000);
        Log.e(TAG, "onRefresh");
    }


    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private Context context;

        public RecyclerViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getItemCount() {
            return objects.size();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    context).inflate(R.layout.item_recycle, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((MyViewHolder) holder).tv.setText(objects.get(position));
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.item_recycle);
            }
        }

    }
}
