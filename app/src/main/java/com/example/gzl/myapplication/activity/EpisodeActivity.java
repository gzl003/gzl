package com.example.gzl.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.gzl.myapplication.R;
import com.example.gzl.myapplication.widget.EpisodeWidget;

import java.util.List;

/**
 * 剧集的测试
 */
public class EpisodeActivity extends Activity implements View.OnClickListener {

    private Button show_juji;
    private FrameLayout my_fram;
    private EpisodeWidget episodeWidget;
    private List<String> list;
    private boolean isshow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_activity);
        show_juji = (Button) findViewById(R.id.show_juji);
        my_fram = (FrameLayout) findViewById(R.id.my_fram);
        show_juji.setOnClickListener(this);
        episodeWidget = new EpisodeWidget(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_juji:
                if (!isshow) {
                    my_fram.addView(episodeWidget, 0);
                    isshow = true;
                }
                break;
        }
    }
}
