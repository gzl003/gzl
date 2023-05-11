package com.zhiguang.li.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.SpannableString;

import com.jiongbull.jlog.JLog;
import com.zhiguang.li.R;
import com.zhiguang.li.widget.ElipsisSpanTextView;

public class TextViewEndActivity extends AppCompatActivity {

    private ElipsisSpanTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_end);
        textView = findViewById(R.id.text);

//        textView.setText("大江东去，浪淘尽，千古风流人物。故垒西边，人道是，三国周郎赤壁。江山如画，一时多少豪杰。乱石穿空，惊涛拍岸，卷起千堆雪。遥想公瑾当年，小乔初嫁了，雄姿英发。羽扇纶巾，谈笑间，樯橹灰飞烟灭。(樯橹 一作：强虏)故国神游，多情应笑我，早生华发。人生如梦，一尊还酹江月。");
//        textView.setText("大江东去，浪淘尽，千古风流人物。");
        String s = "大江东去，浪淘尽，千古风流人物。故垒西边，人道是，三国周郎赤壁。江山如画，一时多少豪杰。乱石穿空，惊涛拍岸，卷起千堆雪。遥想公瑾当年，小乔初嫁了，雄姿英发。羽扇纶巾，谈笑间，樯橹灰飞烟灭。(樯橹 一作：强虏)故国神游，多情应笑我，早生华发。人生如梦，一尊还酹江月。";
        textView.setMaxLinesOnShrink(s, new SpannableString("全部"), 4);
        textView.post(new Runnable() {
            @Override
            public void run() {
                JLog.d("EllipsisCount: " + textView.getNewTextByConfig().toString());
            }
        });
    }
}
