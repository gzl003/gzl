package com.zhiguang.li.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiguang.li.R;

/**
 *  * Created by 智光 on 2017/2/8 15:58
 *  
 */

public class ImgViewItem extends RecyclerView.ViewHolder {
    public ImageView imgview;
    public TextView textView;

    public ImgViewItem(View itemView) {
        super(itemView);
        imgview = (ImageView) itemView.findViewById(R.id.img);
        textView = (TextView) itemView.findViewById(R.id.text);
    }




}
