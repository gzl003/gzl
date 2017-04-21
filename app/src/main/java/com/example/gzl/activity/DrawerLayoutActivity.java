package com.example.gzl.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.Button;

import com.example.gzl.R;
import com.example.gzl.widget.DeawerEpisodeWidget;

/**
 * DrawerLayout 的测试
 * Created by 智光 on 2016/5/3.
 */
public class DrawerLayoutActivity extends BaseActivity implements View.OnClickListener {

    private DrawerLayout myDrawerLayout;
    private Button myButton;

    private DeawerEpisodeWidget cehua_view;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.deawer_layout_activity);
        myDrawerLayout = (DrawerLayout) findViewById(R.id.my_drawer_view);
        cehua_view = (DeawerEpisodeWidget) findViewById(R.id.cehua_view);
        myButton = (Button) findViewById(R.id.my_button);
        myButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (myDrawerLayout.isDrawerOpen(cehua_view)) {
            myDrawerLayout.closeDrawer(cehua_view);
        } else {
            myDrawerLayout.openDrawer(cehua_view);
        }
    }
}
