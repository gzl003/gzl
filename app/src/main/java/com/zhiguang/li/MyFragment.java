package com.zhiguang.li;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaohua on 2016/3/17.
 */
public class MyFragment extends Fragment implements AdapterView.OnItemClickListener {
    private View mRootView;

    private List<String> list;
    private GridView mGridView;
    private MyAdapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fagment_layout, container, false);
            setData();
            mGridView = (GridView) mRootView.findViewById(R.id.fragmentGridView);
            myAdapter = new MyAdapter();
            mGridView.setAdapter(myAdapter);
            mGridView.setOnItemClickListener(this);
        }
        return mRootView;
    }

    private void setData() {
        list = new ArrayList<String>();
        for (int i = 0; i < 30; i++) {
            list.add("第" + i + "集");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        myAdapter.setCheckitem(position);
        myAdapter.notifyDataSetChanged();
//        ((MainActivity)getActivity()).switchFragment(1);
    }

    public class MyAdapter extends BaseAdapter {
        public int checkitem = -1;

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
            TextView textview = new TextView(parent.getContext());
            textview.setText(getItem(position).toString());
//            if(position == checkitem){
//                textview.setBackground(getResources().getDrawable(R.color.button_ched));
//            }else{
//                textview.setBackground(getResources().getDrawable(R.color.button_no_ched));
//            }
            return textview;
        }

        public void setCheckitem(int position) {
            checkitem = position;
        }
    }
}
