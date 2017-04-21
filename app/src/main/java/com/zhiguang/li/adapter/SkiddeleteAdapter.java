package com.zhiguang.li.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zhiguang.li.R;
import com.zhiguang.li.view.SkidDeletaItem;

import java.util.List;

/**
 *  * Created by 智光 on 2017/2/21 16:41
 *  
 */

public class SkiddeleteAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List mList;
    private boolean hasOpen = false;

    public SkiddeleteAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SkilddeleteItem imgViewItem = new SkilddeleteItem(LayoutInflater.from(mContext).inflate(R.layout.recycle_skild_item, parent, false));
        imgViewItem.skidDeletaItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "imgViewItem", Toast.LENGTH_SHORT).show();
            }
        });
        return imgViewItem;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (hasOpen) {
            ((SkilddeleteItem) holder).skidDeletaItem.scrollTo(0, 0);
            hasOpen = false;
        }

    }

    @Override
    public int getItemCount() {
        return 10;
    }


    class SkilddeleteItem extends RecyclerView.ViewHolder {
        SkidDeletaItem skidDeletaItem;

        SkilddeleteItem(View rootview) {
            super(rootview);
            skidDeletaItem = (SkidDeletaItem) rootview.findViewById(R.id.skid_view);
        }


    }
}
