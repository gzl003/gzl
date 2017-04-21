package com.example.gzl.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 智光 on 2016/4/13.
 */
public class PopAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> poplist;
    private HDChackedListener hdChackedListener;
    private int checknum = 0;

    public PopAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.poplist = list;
    }

    public void setHdChackedListener(HDChackedListener hdChackedListener) {
        this.hdChackedListener = hdChackedListener;
    }

    public int getChecknum() {
        return checknum;
    }

    @Override
    public int getCount() {
        return poplist.size();
    }

    @Override
    public Object getItem(int position) {
        return poplist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final TextView text = new TextView(mContext);
        text.setGravity(Gravity.CENTER);
        text.setText(poplist.get(position));
        text.setPadding(10, 10, 10, 10);
        text.setTextSize(18);
        if (position == checknum) {
            text.setTextColor(Color.GREEN);
        } else {
            text.setTextColor(Color.WHITE);
        }
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checknum = position;
                if (hdChackedListener != null) {
                    hdChackedListener.onChchecked(text.getText().toString());
                    text.setTextColor(Color.GREEN);
                }
            }
        });
        return text;
    }

    public interface HDChackedListener {
        void onChchecked(String string);
    }
}
