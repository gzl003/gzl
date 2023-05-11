package com.zhiguang.li.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.convenientbanner.adapter.CBPageAdapterHelper;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;

import java.util.List;

/**
 *  * Created by 智光 on 2018/12/19 11:14
 *  
 */

public class GzlPagerAdapter<T> extends RecyclerView.Adapter<Holder> {
    protected List<T> datas;
    private CBViewHolderCreator creator;
    private CBPageAdapterHelper helper;
    private boolean canLoop;
    private OnItemClickListener onItemClickListener;

    public GzlPagerAdapter(CBViewHolderCreator creator, List<T> datas, boolean canLoop) {
        this.creator = creator;
        this.datas = datas;
        this.canLoop = canLoop;
        helper = new CBPageAdapterHelper();
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = creator.getLayoutId();
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        helper.onCreateViewHolder(parent, itemView);
        return creator.createHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        helper.onBindViewHolder(holder.itemView, position, getItemCount());
        int realPosition = position % datas.size();
        holder.updateUI(datas.get(realPosition));

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener(new GzlPagerAdapter.OnPageClickListener(realPosition));
        }
    }

    @Override
    public int getItemCount() {
        //根据模式决定长度
        if (datas.size() == 0) return 0;
        return canLoop ? 3 * datas.size() : datas.size();
    }

    public void setCanLoop(boolean canLoop) {
        this.canLoop = canLoop;
    }

    public int getRealItemCount() {
        return datas.size();
    }

    public boolean isCanLoop() {
        return canLoop;
    }

    public void setPagePadding(int pagePadding) {
        helper.setPagePadding(pagePadding);
    }

    public void setShowLeftCardWidth(int showLeftCardWidth) {
        helper.setShowLeftCardWidth(showLeftCardWidth);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class OnPageClickListener implements View.OnClickListener {
        private int position;

        public OnPageClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClick(position);
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
