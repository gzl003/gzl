package com.zhiguang.li.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jiongbull.jlog.JLog;
import com.zhiguang.li.R;
import com.zhiguang.li.widget.ElipsisSpanTextView;
import com.zhiguang.li.widget.hornLayout.BubblePopupWindow;
import com.zhiguang.li.widget.hornLayout.HornLayout;

public class TextViewEndActivity extends AppCompatActivity implements View.OnTouchListener {

    private ElipsisSpanTextView textView;
    private BubblePopupWindow bubblePopupWindow;
    private Button huaKBtn;
    private ImageView imgTop;
    private int _xDelta;
    private int _yDelta;
    private HornLayout hornLayout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_end);
        textView = findViewById(R.id.text);
        imgTop = findViewById(R.id.img_top);

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

        huaKBtn = findViewById(R.id.hua_k_btn);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                250, 120);
        huaKBtn.setLayoutParams(layoutParams);
        huaKBtn.setOnTouchListener(this);


        hornLayout = findViewById(R.id.horn_layout);
        TextView tvContent = new TextView(TextViewEndActivity.this);
        tvContent.setText("<resources>\n" +
                        "\n" +
                        "    <declare-styleable name=\"bubble\">\n" +
                        "        <attr name=\"shadowColor\" format=\"color\" />\n" +
                        "        <attr name=\"padding\" format=\"dimension\" />\n" +
                        "        <attr name=\"strokeWidth\" format=\"float\" />\n" +
                        "        <attr name=\"cornerRadius\" format=\"float\" />\n" +
                        "        <attr name=\"halfBaseOfLeg\" format=\"dimension\" />\n" +
                        "    </declare-styleable>\n" +
                        "\n" +
                        "</resources>");
        hornLayout.addView(tvContent);
        hornLayout.setBubbleParams(HornLayout.HornOrientation.TOP, 50); // 设置气泡布局方向及尖角偏移
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) huaKBtn
                        .getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) huaKBtn
                        .getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                huaKBtn.setLayoutParams(layoutParams);

                hornLayout.setBubbleParams(HornLayout.HornOrientation.TOP, X);
                hornLayout.invalidate();

                imgTop.setX(X);

                break;
            default:
                break;
        }
//        _root.invalidate();
        return true;
    }
}
