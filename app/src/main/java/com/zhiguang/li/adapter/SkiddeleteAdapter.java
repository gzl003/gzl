package com.zhiguang.li.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.zhiguang.li.R;

/**
 *  * Created by 智光 on 2017/2/21 16:41
 *  
 */

public class SkiddeleteAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public SkiddeleteAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SkilddeleteItem imgViewItem = new SkilddeleteItem(LayoutInflater.from(mContext).inflate(R.layout.recycle_skild_item, parent, false));
        imgViewItem.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "imgViewItem", Toast.LENGTH_SHORT).show();
            }
        });
        return imgViewItem;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SkilddeleteItem skilddeleteItem = (SkilddeleteItem) holder;
        skilddeleteItem.myitemtext.setText("测试数据第：" + position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }


    class SkilddeleteItem extends RecyclerView.ViewHolder {
        TextView textView;
        TextView myitemtext;

        SkilddeleteItem(View rootview) {
            super(rootview);
            textView = rootview.findViewById(R.id.mybtnDelete);
            myitemtext = rootview.findViewById(R.id.myitemtext);
        }


    }
}
