package com.zhiguang.li.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zhiguang.li.R;

/**
 * http://www.open-open.com/lib/view/open1436152483833.html#articleHeader10
 */
public class CodeLayActivity extends AppCompatActivity {
    FloatingActionButton fabBtn;
    CoordinatorLayout rootLayout;
    Toolbar toolbar;
//    TabLayout tabLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_lay);
        initInstances();
    }


    private void initInstances() {
        rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
        fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn);
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(rootLayout, "Hello. I am Snackbar!", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .show();
            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        tabLayout.addTab(tabLayout.newTab().setText("GZL 1"));
//        tabLayout.addTab(tabLayout.newTab().setText("GZL 2"));
//        tabLayout.addTab(tabLayout.newTab().setText("GZL 3"));
//        tabLayout.addTab(tabLayout.newTab().setText("GZL 4"));
//        tabLayout.addTab(tabLayout.newTab().setText("GZL 5"));
//        tabLayout.addTab(tabLayout.newTab().setText("GZL 6"));
//        tabLayout.addTab(tabLayout.newTab().setText("GZL 7"));
//        tabLayout.addTab(tabLayout.newTab().setText("GZL 8"));
//        tabLayout.addTab(tabLayout.newTab().setText("GZL 9"));

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("这是我的ActionBar");
    }
}
