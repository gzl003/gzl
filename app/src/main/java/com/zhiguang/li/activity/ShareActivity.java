package com.zhiguang.li.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.zhiguang.li.R;

/**
 *  * Created by 智光 on 2018/6/7 11:52
 *  
 */
public class ShareActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        findViewById(R.id.text_share1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent textIntent = new Intent(Intent.ACTION_SEND);
                textIntent.setType("text/plain");
                textIntent.putExtra(Intent.EXTRA_SUBJECT, "我是标题");
                textIntent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");
                startActivity(Intent.createChooser(textIntent, "分享测试"));
            }
        });
        findViewById(R.id.text_share2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources r = getBaseContext().getResources();
                Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + r.getResourcePackageName(R.drawable.adapterview_2) + "/"
                        + r.getResourceTypeName(R.drawable.adapterview_2) + "/"
                        + r.getResourceEntryName(R.drawable.adapterview_2));
                Uri imguri = Uri.parse("http://t2.hddhhn.com/uploads/tu/201805/9999/5e815cc9ee.jpg");
                Intent imageIntent = new Intent(Intent.ACTION_SEND);
                imageIntent.setType("image/*");
                imageIntent.putExtra(Intent.EXTRA_STREAM, uri);
                imageIntent.putExtra(Intent.EXTRA_ASSIST_CONTEXT, imguri);
                startActivity(Intent.createChooser(imageIntent, "分享"));

            }
        });

        findViewById(R.id.text_share3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resources r = getBaseContext().getResources();
                Uri imguri = Uri.parse("http://t2.hddhhn.com/uploads/tu/201805/9999/5e815cc9ee.jpg");
                Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                        + r.getResourcePackageName(R.drawable.adapterview_2) + "/"
                        + r.getResourceTypeName(R.drawable.adapterview_2) + "/"
                        + r.getResourceEntryName(R.drawable.adapterview_2));
                Intent imageIntent = new Intent(Intent.ACTION_SEND);
                imageIntent.setType("image/*");
                imageIntent.putExtra(Intent.EXTRA_STREAM, imguri);
                imageIntent.putExtra(Intent.EXTRA_SUBJECT, "我是标题");
                imageIntent.putExtra(Intent.EXTRA_TEXT, "这是一段分享的文字");
                startActivity(Intent.createChooser(imageIntent, "分享"));
            }
        });


    }
}
