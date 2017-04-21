package com.example.gzl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gzl.R;
import com.example.gzl.widget.ProgressWidget;

public class ProgressActivity extends Activity implements View.OnClickListener {
    private Button stert_show;
    private ProgressWidget progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        stert_show = (Button) findViewById(R.id.stert_show);
        stert_show.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        progressDialog = new ProgressWidget(this,R.style.Widget);
        progressDialog.show();
    }
}
