package com.zhiguang.li.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.zhiguang.li.R;
import com.zhiguang.li.widget.SwitchButton;

public class SwichButtonActivity extends AppCompatActivity {

    private SwitchButton switchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swich_button);
        switchButton = (SwitchButton) findViewById(R.id.swich_btn);
        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(SwichButtonActivity.this,"打开",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SwichButtonActivity.this,"关闭",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
