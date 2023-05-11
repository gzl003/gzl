package com.zhiguang.li.activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.zhiguang.li.R;
import com.zhiguang.li.utils.ImagetUtils;

public class PaletteActivity extends BaseActivity {

    private ImageView img_palette;
    private TextView text_palette;
    private Bitmap bitmap;
    private Palette.Swatch s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
        img_palette = (ImageView) findViewById(R.id.img_palette);
        text_palette = (TextView) findViewById(R.id.text_palette);

        Glide.with(this).load("http://pic7.huanxi.com/6cf29b903204405160ce.png").into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                img_palette.setImageDrawable(resource);
                bitmap = ImagetUtils.drawableToBitmap(resource);
            }
        });


        if (bitmap != null) {
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @Override
                public void onGenerated(Palette palette) {
                    s = palette.getVibrantSwatch();       //获取到充满活力的这种色调
                    s = palette.getDarkVibrantSwatch();    //获取充满活力的黑
                    s = palette.getLightVibrantSwatch();   //获取充满活力的亮
                    s = palette.getMutedSwatch();           //获取柔和的色调
                    s = palette.getDarkMutedSwatch();      //获取柔和的黑
                    s = palette.getLightMutedSwatch();    //获取柔和的亮


                    s.getPopulation();
                    s.getRgb();//这个颜色的RGB
                    s.getHsl();//这个颜色的hsl
                    s.getBodyTextColor();//
                    s.getTitleTextColor();
                    if (s != null) {
                        text_palette.setTextColor(s.getBodyTextColor());
                    }
                }
            });
        }


    }
}
