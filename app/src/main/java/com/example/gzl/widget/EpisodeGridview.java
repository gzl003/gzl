package com.example.gzl.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gzl.R;

import java.util.List;

/**
 * Created by 智光 on 2016/4/25.
 *
 */
public class EpisodeGridview extends RelativeLayout {
    private Context context;
    private List<String> list;
    private GridView myGridView;
    private MygridAdapter myAdapter;

    public EpisodeGridview(Context context, List<String> list) {
        super(context);
        this.context = context;
        this.list = list;
        initView();
    }

    private void initView() {
        View view = View.inflate(context, R.layout.viewpager_episo_item, null);
        myGridView = (GridView) view.findViewById(R.id.myepigridview);
        myAdapter = new MygridAdapter();
        myGridView.setAdapter(myAdapter);
        addView(view);
    }

    public class MygridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textview = new TextView(context);
            textview.setText(list.get(position));
            return textview;
        }
    }
}
