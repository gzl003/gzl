package com.zhiguang.li.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhiguang.li.R;
import com.zhiguang.li.widget.DragDelItem;
import com.zhiguang.li.widget.DragDelListView;

import java.util.ArrayList;
import java.util.List;

public class ListSkideleteActivity extends AppCompatActivity {
    private List<String> mAppList;
    private DragDelListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_skidelete);
        mAppList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            mAppList.add("红包来了");
        }
        mListView = (DragDelListView) findViewById(R.id.listView);
        mListView.setAdapter(new AppAdapter(mAppList));
    }

    class AppAdapter extends BaseAdapter {
        private List<String> mAppList;
        public AppAdapter(List<String> appList)
        {
            mAppList=appList;
        }
        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public String getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            final int loc=position;
            ViewHolder holder=null;
            View menuView=null;
            View mainview = null;
            if (convertView == null) {
                mainview = View.inflate(getApplicationContext(),
                        R.layout.swipecontent, null);
                menuView = View.inflate(getApplicationContext(),
                        R.layout.swipemenu, null);
                //ºÏ³ÉÄÚÈÝÓë²Ëµ¥
                convertView = new DragDelItem(mainview,menuView);;
                holder=new ViewHolder(mainview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            String item = getItem(position);

            holder.tv_name.setText(item + "item->->" + position);
            holder.tv_open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    Toast.makeText(ListSkideleteActivity.this, "open:"+loc, Toast.LENGTH_SHORT).show();
                }
            });
            holder.tv_del.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                }
            });
            return convertView;
        }

        class ViewHolder {
            ImageView iv_icon;
            TextView tv_name;
            TextView tv_open,tv_del;
            public ViewHolder(View view) {
                iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
                tv_name = (TextView) view.findViewById(R.id.tv_name);
                tv_open=(TextView)view.findViewById(R.id.tv_open);
                tv_del=(TextView)view.findViewById(R.id.tv_del);
                view.setTag(this);
            }
        }

    }
}
