package com.zhiguang.li.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.zhiguang.li.R;
import com.zhiguang.li.utils.ScrollableViewhotUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ScrollScreenhotActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ScrollView scrollView;
    private View view;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_screenhot);
        recyclerView = findViewById(R.id.recyclerView);
        scrollView = findViewById(R.id.scrollView);
        view = findViewById(R.id.view);
        findViewById(R.id.starhot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }


    private void start() {
        final ScrollableViewhotUtil scrollableViewRECUtil = new ScrollableViewhotUtil(view, ScrollableViewhotUtil.VERTICAL);

        scrollableViewRECUtil.start(new ScrollableViewhotUtil.OnRecFinishedListener() {
            @Override
            public void onRecFinish(Bitmap bitmap) {
                File appDir = new File(Environment.getExternalStorageDirectory(), "Huanxi");
                if (!appDir.exists()) {
                    appDir.mkdir();
                }
                String fileName = System.currentTimeMillis() + ".jpg";
                File file = new File(appDir, fileName);
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 其次把文件插入到系统图库
                try {
                    MediaStore.Images.Media.insertImage(ScrollScreenhotActivity.this.getContentResolver(),
                            file.getAbsolutePath(), "aaa/gzl.jpg", null);
                    // 最后通知图库更新
                    ScrollScreenhotActivity.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });


        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                scrollableViewRECUtil.stop();
            }
        }, 3 * 1000);
    }
}
