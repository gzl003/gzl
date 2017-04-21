package com.example.gzl.adapter;

import android.support.v7.widget.GridLayoutManager;

/**
 *  * Created by 智光 on 2016/12/28 17:38
 *  
 */

public class MuseumSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    protected MuseumRecycleViewAdapter adapter = null;
    protected GridLayoutManager layoutManager = null;


    public MuseumSpanSizeLookup(MuseumRecycleViewAdapter adapter, GridLayoutManager layoutManager) {
        this.adapter = adapter;
        this.layoutManager = layoutManager;
    }

    /**
     * @param position
     * @return 表示当前item的跨度 及是当前item所占的比例 相当于权重
     * 如果spanCount 是 6  return 2 就是占 三分之一
     */
    @Override
    public int getSpanSize(int position) {
        switch (adapter.getItemViewType(position)) {
            case MuseumRecycleViewAdapter.ITEM_VIEW_TYPE_BANNER:
                return 2;
            case MuseumRecycleViewAdapter.ITEM_VIEW_TYPE_BIG:
                return 2;
            case MuseumRecycleViewAdapter.ITEM_VIEW_TYPE_SMALL:
                return 1;
            case MuseumRecycleViewAdapter.ITEM_VIEW_TYPE_TITLE:
                return 2;
            default:
                return 2;
        }
    }
}
