package com.zhiguang.li.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.zhiguang.li.R;

/**
 *  * Created by 智光 on 2017/12/14 15:16
 *  
 */
public class GralleryPagerAdapter extends PagerAdapter {

    private String[] listViews;
    private LayoutInflater mInflater;
    private Context mContext;
    private Runnable mBlurRunnable;
    private Bitmap bitmap;
    private ImageView img_bar;

    public GralleryPagerAdapter(Context context, String[] listViews, ImageView img_bar) {
        this.listViews = listViews;
        this.mContext = context;
        this.img_bar = img_bar;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listViews == null ? 0 : listViews.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mInflater.inflate(R.layout.page_item, container, false);
        ImageView photoView = (ImageView) view.findViewById(R.id.image_item);
        Glide.with(mContext)
                .load(listViews[position])
                .into(photoView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            container.removeView((View) object);

        }
    }

    @Override
    public void setPrimaryItem(ViewGroup container, final int position, Object object) {
        super.setPrimaryItem(container, position, object);


    }


}
