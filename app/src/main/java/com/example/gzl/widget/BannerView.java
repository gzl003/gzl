package com.example.gzl.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.bumptech.glide.Glide;
import com.example.gzl.R;
import com.example.gzl.modle.Museum;

/**
 *  Created by 智光 on 2016/12/28 14:18
 *  
 */

public class BannerView implements Holder<Museum.ResultBean.HeadBean> {

    private Context mContext;
    private ImageView imageView;
    private TextView textTitle;
    private TextView textDes;

    @Override
    public View createView(Context context) {
        this.mContext = context;
        View view = View.inflate(mContext, R.layout.banner_layout, null);
        imageView = (ImageView) view.findViewById(R.id.banner_img);
        textTitle = (TextView) view.findViewById(R.id.banner_title);
        textDes = (TextView) view.findViewById(R.id.banner_des);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, Museum.ResultBean.HeadBean data) {
        textTitle.setText(data.title);
        textDes.setText(data.appslogan);
        /**
         常用的图片加载库：
         Universal Image Loader：一个强大的图片加载库，包含各种各样的配置，最老牌，使用也最广泛。
         Picasso: Square出品，必属精品。和OkHttp搭配起来更配呦！
         Volley ImageLoader：Google官方出品，可惜不能加载本地图片~
         Fresco：Facebook出的，天生骄傲！不是一般的强大。
         Glide：Google推荐的图片加载库，专注于流畅的滚动。
         */
        Glide.with(mContext)
                .load(data.stills)
                .fitCenter()
                .placeholder(R.drawable.image)
                .crossFade()
                .into(imageView);
    }
}
