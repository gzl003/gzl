package com.example.gzl.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 智光 on 2016/7/5.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List list;

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setDataList(List list) {
        this.list = list;
//        if (list != null && list.size() > 0) {
        notifyDataSetChanged();
//        }
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (list != null) {
            return list.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(list.get(position).toString());
        textView.setGravity(Gravity.CENTER);
        textView.setPadding(0, 20, 0, 20);
        textView.setTextSize(15);
        textView.setTextColor(Color.GRAY);
        return textView;
    }
}
