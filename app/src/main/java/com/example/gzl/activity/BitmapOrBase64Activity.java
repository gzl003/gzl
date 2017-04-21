package com.example.gzl.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gzl.R;

import java.io.ByteArrayOutputStream;

/**
 * 图片和base64之间的转换
 */
public class BitmapOrBase64Activity extends AppCompatActivity {

    private ImageView bit_img;
    private Bitmap bitmap = null;
    private TextView mytext;
    private long longtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_or_base64);
        bit_img = (ImageView) findViewById(R.id.bit_img);
        mytext = (TextView) findViewById(R.id.mytext);
        bitmap = stringtoBitmap(getString(R.string.base64str));
        bit_img.setImageBitmap(bitmap);
        mytext.setText("高：" + bitmap.getHeight() + "  宽：" + bitmap.getWidth() + "  大小" + bitmap.getByteCount() / 1024 + "Kb");

        longtime = System.currentTimeMillis();


        SpannableStringBuilder spann = new SpannableStringBuilder();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bitmap != null && !bitmap.isRecycled()) {
            // 回收并且置为null
            bitmap.recycle();
            bitmap = null;
        }
        System.gc();
    }


    public Bitmap drawableTobitmap(Drawable drawable) {
        Bitmap bitmap = null;
        Resources res = getResources();
        bitmap = BitmapFactory.decodeResource(res, R.drawable.grid_img);
        return bitmap;
    }

    //将Bitmap转换成字符串
    public String bitmaptoString(Bitmap bitmap) {

        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    /**
     * 　 将base64转换成bitmap图片
     * 　　@param string base64字符串
     * 　　@return bitmap
     */
    public Bitmap stringtoBitmap(String string) {
        // 将字符串转换成Bitmap类型
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0,
                    bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}
