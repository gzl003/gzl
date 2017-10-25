package com.zhiguang.li.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhiguang.li.R;
import com.zhiguang.li.widget.RadiusImageWidget;

/**
 * 圆角图片
 * Created by 智光 on 2016/4/5.
 */
public class RadiusImage extends Activity {
    private RadiusImageWidget redius_img;
    private RadiusImageWidget redius_img1;
    private RadiusImageWidget redius_img2;
    private RadiusImageWidget redius_img3;
    private RadiusImageWidget redius_img4;
    private ImageView img_gif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radiuslmage_layout);
        redius_img = (RadiusImageWidget) findViewById(R.id.redius_img);
        redius_img1 = (RadiusImageWidget) findViewById(R.id.redius_img1);
        redius_img2 = (RadiusImageWidget) findViewById(R.id.redius_img2);
        redius_img3 = (RadiusImageWidget) findViewById(R.id.redius_img3);
        redius_img4 = (RadiusImageWidget) findViewById(R.id.redius_img4);
        img_gif = (ImageView) findViewById(R.id.img_gif);

        setRadiusImage();
    }


    private void setRadiusImage() {
//在布局中引用
        Glide.with(this)
                .load("http://tc.sinaimg.cn/maxwidth.2048/tc.service.weibo.com/p/mmbiz_qpic_cn/0dbdb8ca21725696a14d484e02f98a5a.jpg")
                .into(redius_img);

        Glide.with(this)
                .load("http://tc.sinaimg.cn/maxwidth.2048/tc.service.weibo.com/p/mmbiz_qpic_cn/0dbdb8ca21725696a14d484e02f98a5a.jpg")
                .into(redius_img1);


        Glide.with(this)
                .load("http://tc.sinaimg.cn/maxwidth.2048/tc.service.weibo.com/p/mmbiz_qpic_cn/0dbdb8ca21725696a14d484e02f98a5a.jpg")
                .into(redius_img2);


        Glide.with(this)
                .load("http://tc.sinaimg.cn/maxwidth.2048/tc.service.weibo.com/p/mmbiz_qpic_cn/0dbdb8ca21725696a14d484e02f98a5a.jpg")
                .into(redius_img3);


        Glide.with(this)
                .load("http://tc.sinaimg.cn/maxwidth.2048/tc.service.weibo.com/p/mmbiz_qpic_cn/0dbdb8ca21725696a14d484e02f98a5a.jpg")
                .into(redius_img4);//

        Glide.with(this)
                .load("http://images.17173.com/2013/news/2013/12/16/d1216t09.gif")
                .into(img_gif);

    }
}
