package com.zhiguang.li.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zhiguang.li.R;

public class YUyunsuanActivity extends AppCompatActivity {

    private TextView text_yu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yuyunsuan);
        text_yu = (TextView) findViewById(R.id.text_yu);

        setView();
    }

    private void setView() {
        text_yu.setText(""+ (15 & 15)+ "\n" + (15 & 1)+ "\n" + (15 & 2)+ "\n" + (15 & 4) + (15 & 8) + "\n" + (15 & 16));
    }
}
