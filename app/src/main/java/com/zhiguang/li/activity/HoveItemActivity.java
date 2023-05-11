package com.zhiguang.li.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhiguang.li.R;
import com.zhiguang.li.adapter.SkiddeleteAdapter;

/**
 * @author : 智光
 * @date: 2021/11/25 17:46
 * @desc: 悬停item
 */
public class HoveItemActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SkiddeleteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_activity);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
        adapter = new SkiddeleteAdapter(this);
        mRecyclerView.setAdapter(adapter);
        findViewById(R.id.refresh).setVisibility(View.GONE);


    }
}
