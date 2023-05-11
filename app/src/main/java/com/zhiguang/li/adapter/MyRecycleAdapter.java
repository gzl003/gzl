package com.zhiguang.li.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zhiguang.li.R;
import com.zhiguang.li.view.ImgViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 智光 on 2016/10/26 17:15
 *  
 */
public class MyRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List mList;


    public MyRecycleAdapter(Context mContext, List mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ImgViewItem imgViewItem = new ImgViewItem(LayoutInflater.from(mContext).inflate(R.layout.recycle_img_item, parent, false));

        return imgViewItem;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImgViewItem) {
            ((ImgViewItem) holder).textView.setText(mList.get(position) + "");

            Glide.with(mContext)
                    .load("http://img0.wsy1.com/0ef2e0ae127015113bbb.jpg")
                    .into(((ImgViewItem) holder).imgview);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    public static List setData() {
        List list = new ArrayList();
        for (int i = 0; i < 20; i++) {
            list.add("item i = " + i);
        }
        return list;
    }
}
