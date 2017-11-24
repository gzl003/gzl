package com.zhiguang.li.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.widget.ImageView;

import com.zhiguang.li.R;
import com.zhiguang.li.utils.ImagetUtils;

public class WatermarkActivity extends BaseActivity {

    private ImageView imageView;
    private MediaScannerConnection msc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatmack);
        imageView = (ImageView) findViewById(R.id.image_warte);
        initView();
    }

    private void initView() {
        //获取本地资源图片(这里你可以通过照相机或者相册获取 bitmap 图片对象)
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.th);
        int w = src.getWidth();//获取图片的宽
        int h = src.getHeight();//获取图片的高
        //压缩图片的bitmap
        float scale = (float) (1000000.0 / (w * h));
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        Bitmap src1 = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
        //释放掉原始位图
        src.recycle();
        Bitmap water = BitmapFactory.decodeResource(getResources(), R.drawable.main_tab_bigmovie_normal);
        //加水印
        src1 = ImagetUtils.createWaterBitmap(src1, water, String.valueOf(System.currentTimeMillis()));
        //src1 = WaterMark.watermarkBitmap(src1,water,"2016-5-19");

        //显示
        imageView.setImageBitmap(src1);

//        ImagetUtils.saveImageToGallery(this, src1);
        ImagetUtils.insertImageToAllbum(src1,this);

    }

}
