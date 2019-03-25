package com.zhiguang.li.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhiguang.li.R;

import java.util.List;

/**
 *  * Created by 智光 on 2019/3/25 12:23
 *  
 */

public class ThreadListAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<String> list;

    public ThreadListAdapter(Context mContext, List list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(mContext).inflate(R.layout.threarlist_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Viewholder viewholder = (Viewholder) holder;
        viewholder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {

        TextView textView;

        public Viewholder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
