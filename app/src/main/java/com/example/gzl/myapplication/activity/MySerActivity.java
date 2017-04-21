package com.example.gzl.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.gzl.myapplication.R;
import com.example.gzl.myapplication.service.MyService;

public class MySerActivity extends Activity implements View.OnClickListener {
    static int i = 0;
    private Intent intent;
    private static TextView setvice_text;
    private static int[] color = {Color.GREEN, Color.TRANSPARENT, Color.BLUE, Color.YELLOW, Color.LTGRAY, Color.MAGENTA,Color.GRAY,Color.YELLOW,Color.GREEN};
    public static Runnable runnable = new Runnable() {
        @Override
        public void run() {
            i += 1;
            Log.e("MyService", "---> Service onStartCommand()" + i);
            setvice_text.setText(i + " ");
            setvice_text.setBackgroundColor(color[i % 5]);
            MyService.handler.postDelayed(runnable, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ser);
        findViewById(R.id.start_service).setOnClickListener(this);
        findViewById(R.id.stop_service).setOnClickListener(this);
        setvice_text = (TextView) findViewById(R.id.setvice_text);
        setService();
    }

    private void setService() {
        intent = new Intent(this, MyService.class);
        intent.setAction("lizhiguang");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                startService(intent);
                break;
            case R.id.stop_service:
                stopService(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyService.handler.removeCallbacks(runnable);
        stopService(intent);
    }
}
