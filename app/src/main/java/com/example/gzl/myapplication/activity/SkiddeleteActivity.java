package com.example.gzl.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gzl.myapplication.R;
import com.example.gzl.myapplication.adapter.SkiddeleteAdapter;

public class SkiddeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_activity);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
        SkiddeleteAdapter adapter = new SkiddeleteAdapter(this);
        mRecyclerView.setAdapter(adapter);
    }
}
