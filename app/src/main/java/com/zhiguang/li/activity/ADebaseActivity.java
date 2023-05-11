package com.zhiguang.li.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.zhiguang.li.R;
import com.zhiguang.li.adapter.WebBannerAdapter;
import com.zhiguang.li.widget.GzlBanner;
import com.zhiguang.li.widget.GzlBannerItemView;
import com.zhiguang.li.widget.banner.BannerLayout;

import java.util.Arrays;

/**
 * 广告位  轮播图
 */
public class ADebaseActivity extends Activity {

    private Banner banner;
    private BannerLayout bannerrv;
    private GzlBanner gzlbanner;

    private String[] images = new String[]{"http://img.xiankan.com/c4659e4c9dba480d06c2.jpg",
            "http://img.xiankan.com/32fc897fa6f06e8a21c9.jpg",
            "http://img.xiankan.com/2cb0abb206cc5f2b2d8d.jpg",
            "http://img.xiankan.com/bacee2c41adad8a77e67.jpg",
            "http://img.xiankan.com/116689352ede0a879914.jpg"};
    private String[] titles = new String[]{"捉迷藏", "电影留声机", "地狱宝贝", "亲见爱的", "斗龙战士"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adspase);
        banner = (Banner) findViewById(R.id.my_banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
//        //设置指示器位置（当banner模式中有指示器时）
//        banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(Arrays.asList(images));
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(Arrays.asList(titles));
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.ZoomIn);

        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);

        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getApplicationContext(), "你点击了：" + titles[position], Toast.LENGTH_LONG).show();
            }//设置点击事件
        });

/////////////////////////////////////////////////////////////////////////////
        bannerrv = findViewById(R.id.banner_rv);
        WebBannerAdapter webBannerAdapter = new WebBannerAdapter(this, Arrays.asList(images));
        webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(ADebaseActivity.this, "点击了第  " + position + "  项", Toast.LENGTH_SHORT).show();
            }
        });
        bannerrv.setAdapter(webBannerAdapter);
        bannerrv.setAutoPlayDuration(1000);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        gzlbanner = findViewById(R.id.gzlbanner);
        gzlbanner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public GzlBannerItemView createHolder(View itemView) {
                        return new GzlBannerItemView(itemView, ADebaseActivity.this);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.banner_layout;
                    }

                }, Arrays.asList(images))
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.focus_un, R.drawable.focus})
                //设置指示器的方向
                .setPageIndicatorAlign(GzlBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .startTurning(3000);
        gzlbanner.setPagePadding(5);
        gzlbanner.setShowLeftCardWidth(10);
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {

            RequestOptions options = new RequestOptions();
            options.centerCrop();
            /**
             常用的图片加载库：
             Universal Image Loader：一个强大的图片加载库，包含各种各样的配置，最老牌，使用也最广泛。
             Picasso: Square出品，必属精品。和OkHttp搭配起来更配呦！
             Volley ImageLoader：Google官方出品，可惜不能加载本地图片~
             Fresco：Facebook出的，天生骄傲！不是一般的强大。
             Glide：Google推荐的图片加载库，专注于流畅的滚动。
             */
            Glide.with(context)
                    .load(path)
                    .apply(options)
                    .into(imageView);
        }
    }


    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        banner.isAutoPlay(true);

    }

    @Override
    protected void onStop() {
        super.onStop();
        banner.isAutoPlay(false);

    }
}
