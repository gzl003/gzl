package com.zhiguang.li.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zhiguang.li.R;

/**
 * @author : 智光
 * @date: 2021/11/25 17:49
 * @desc: 悬停item
 */
public class HoveItemAdapter extends RecyclerView.Adapter {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycleview_hove_item, viewGroup, false);
        return new HoveViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class HoveViewHolder extends RecyclerView.ViewHolder {

        public HoveViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
