package com.zhiguang.li.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.zhiguang.li.R;
import com.zhiguang.li.modle.FlowInfo;
import com.zhiguang.li.view.ImgViewRecycleItem;

import java.util.List;

/**
 *  * Created by 智光 on 2017/4/26 12:20
 *  
 */
public class ImageRecycleAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<FlowInfo> mList;

    public ImageRecycleAdapter(Context mContext, List<FlowInfo> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImgViewRecycleItem recycleItem = new ImgViewRecycleItem(LayoutInflater.from(mContext).inflate(R.layout.image_recycle_item, parent, false));
        return recycleItem;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ImgViewRecycleItem) {
            FlowInfo flowInfo = mList.get(position);
            ((ImgViewRecycleItem) holder).textView.setText(flowInfo.getName());

            Glide.with(mContext)
                    .load(flowInfo.getImgurl())
                    .into(((ImgViewRecycleItem) holder).imgview);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
