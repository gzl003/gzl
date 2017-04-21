package com.zhiguang.li.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import com.zhiguang.li.R;
import com.zhiguang.li.utils.DesUtils;

/**
 * Created by 智光 on 2016/6/22
 */
public class DesTest extends Activity implements View.OnClickListener {
    private EditText editText;
    private TextView textView1, textView2;
    private String text;
    private TextClock textClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.des_layout);
        editText = (EditText) findViewById(R.id.editText);
        textView1 = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textClock = (TextClock) findViewById(R.id.textClock);
//        textClock.setShowSoftInputOnFocus(true);
        findViewById(R.id.button).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                textView2.setText(DesUtils.decryptDES(text,DesUtils.key));
                break;
            case R.id.button:
                text = DesUtils.qucDesEncryptStr(editText.getText().toString().trim(), DesUtils.key);
                textView1.setText(text);
                break;
        }
    }
}
