package com.zhiguang.li.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhiguang.li.R;
import com.zhiguang.li.adapter.SkiddeleteAdapter;

/**
 * 测滑删除菜单点击删除item
 */
public class SkiddeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_activity);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
        final SkiddeleteAdapter adapter = new SkiddeleteAdapter(this);
        mRecyclerView.setAdapter(adapter);
        findViewById(R.id.refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();
            }
        });
    }
}
