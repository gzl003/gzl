package com.zhiguang.li.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhiguang.li.R;
import com.zhiguang.li.utils.ScreenUtils;
import com.zhiguang.li.widget.MyGridView;
import com.zhiguang.li.widget.NoScrollListview;

import java.util.ArrayList;

/**
 * Created by zhaohua on 2016/3/21.
 * RecyclerView
 * <p>
 * http://blog.csdn.net/lmj623565791/article/details/45059587
 */
public class RecyclerViewActivity extends BaseActivity {

    public RecyclerView mRecyclerView;
    private HomeAdapter mAdapter;
    private ArrayList<String> mDatas;
    private LayoutInflater mInflater;
    private MyListAdapter listAdapter;

    private int imgWhidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycleview_activity);
        mInflater = LayoutInflater.from(RecyclerViewActivity.this);
        initData();
        imgWhidth = (int) ScreenUtils.getScreenWidth(this) / 2;
        mAdapter = new HomeAdapter();
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));//左右滑动
        mRecyclerView.setAdapter(mAdapter);//设置adapter
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置Item增加、移除动画
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(
//                getActivity(), DividerItemDecoration.HORIZONTAL_LIST));//添加分割线
//        mRecyclerView.addItemDecoration(new );


    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }


    class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = null;
            switch (viewType) {
                case 1:
                    viewHolder = new HeaderViewHodler(mInflater.inflate(R.layout.recycle_item_topview, parent,
                            false));
                    break;
                case 2:
                    viewHolder = new MyViewHolder(mInflater.inflate(R.layout.recycle_item_topview, parent,
                            false));
                    break;
                case 3:
                    viewHolder = new GridViewHodler(mInflater.inflate(R.layout.recycle_gridview_item, parent, false));
                    break;
            }

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            switch (getItemViewType(position)) {
                case 1:
                    if (holder instanceof HeaderViewHodler) {
                        HeaderViewHodler headerView = (HeaderViewHodler) holder;
                        headerView.imgtwo.setImageResource(R.drawable.ic_launcher);
                        headerView.imgone.setImageResource(R.drawable.ic_launcher);
                        listAdapter = new MyListAdapter();
                        headerView.mlistview.setAdapter(listAdapter);
                    }
                    break;
                case 2:
                    if (holder instanceof MyViewHolder) {
                        MyViewHolder myViewHolder = (MyViewHolder) holder;
//                        myViewHolder.tv.setText("textView  " + mDatas.get(position));
                        myViewHolder.imgtwo.setImageResource(R.drawable.image);
                        myViewHolder.imgone.setImageResource(R.drawable.grid_img);
                        listAdapter = new MyListAdapter();
                        myViewHolder.mlistview.setAdapter(listAdapter);
                    }
                    break;
                case 3:
                    if (holder instanceof GridViewHodler) {
                        GridViewHodler gridViewHodler = (GridViewHodler) holder;
                        gridViewHodler.recyleitem_gridview.setAdapter(new GridViewAdapter());
                    }
                    break;
            }

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        @Override
        public int getItemViewType(int position) {
//            return super.getItemViewType(position);
            if (position == 0) {
                return 3;
            } else if (position == 1) {
                return 1;
            } else {
                return 2;
            }
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            NoScrollListview mlistview;
            TextView tv;
            ImageView imgone;
            ImageView imgtwo;

            public MyViewHolder(View view) {
                super(view);
//                tv = (TextView) view.findViewById(R.id.item_recycle);
                imgone = (ImageView) itemView.findViewById(R.id.img_one);
                imgtwo = (ImageView) itemView.findViewById(R.id.img_two);
                mlistview = (NoScrollListview) itemView.findViewById(R.id.my_listview);
            }
        }

        class HeaderViewHodler extends RecyclerView.ViewHolder {
            ImageView imgone;
            ImageView imgtwo;
            NoScrollListview mlistview;

            public HeaderViewHodler(View itemView) {
                super(itemView);
                imgone = (ImageView) itemView.findViewById(R.id.img_one);
                imgtwo = (ImageView) itemView.findViewById(R.id.img_two);
                mlistview = (NoScrollListview) itemView.findViewById(R.id.my_listview);
            }
        }

        class GridViewHodler extends RecyclerView.ViewHolder {
            MyGridView recyleitem_gridview;

            public GridViewHodler(View itemView) {
                super(itemView);
                recyleitem_gridview = (MyGridView) itemView.findViewById(R.id.recyleitem_gridview);
            }
        }
    }


    class GridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView img;
            View view = View.inflate(RecyclerViewActivity.this, R.layout.gridview_item, null);
            img = (ImageView) view.findViewById(R.id.img_grid);
            ViewGroup.LayoutParams para = img.getLayoutParams();
            para.height = imgWhidth * 9 / 16;
            para.width = imgWhidth;
            img.setLayoutParams(para);
            return view;
        }
    }

    class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 6;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(RecyclerViewActivity.this, R.layout.recyle_item_list, null);
            ImageView list_imgview = (ImageView) view.findViewById(R.id.list_imgview);
            list_imgview.setImageResource(R.drawable.image);
            return view;
        }
    }
}
