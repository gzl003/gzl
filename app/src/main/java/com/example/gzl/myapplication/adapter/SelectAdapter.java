package com.example.gzl.myapplication.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.gzl.myapplication.widget.SelectItemWidget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 智光 on 2016/4/12.
 */
public class SelectAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mList;
    private SelectItemWidget itemview;
    private List<SelectItemWidget> viewlist;
    private SelectItemWidget.OnClickedListener clickedListener;

    public void setmList(List<String> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public List<SelectItemWidget> getViewlist() {
        return viewlist;
    }

    public void setViewlist(List<SelectItemWidget> viewlist) {
        this.viewlist = viewlist;
    }

    public SelectAdapter(Context context, List<String> mList, SelectItemWidget.OnClickedListener clickedListener) {
        this.mContext = context;
        this.mList = mList;
        this.clickedListener = clickedListener;
        viewlist = new ArrayList<SelectItemWidget>();
        for (int i = 0; i < mList.size(); i++) {
            viewlist.add(new SelectItemWidget(mContext));
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        itemview = viewlist.get(position);
        itemview.setClickedListener(clickedListener);
        itemview.setTextContext(mList.get(position));
        if (position == 0) {
            itemview.setCanDown(true);
            itemview.setDowned(true);
        } else if (position % 2 == 1) {
            itemview.setCanDown(false);
        }
        return itemview;
    }
}
