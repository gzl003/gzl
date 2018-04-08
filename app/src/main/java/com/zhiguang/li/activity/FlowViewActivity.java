package com.zhiguang.li.activity;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiguang.li.R;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView 实现流式布局
 */
public class FlowViewActivity extends Activity {
    private String[] name = {"一拍成名一拍成名", "穿越侠穿越侠 我穿 我穿我穿穿穿", "王朝的女人，杨贵妃王朝的女人，杨贵妃", "左耳", "战狼", "港囧", "煎饼侠", "千金女侠傻白甜", "碟中谍5，神秘的傻逼碟中谍5，神秘的傻逼碟中谍5，神秘的傻逼", "终结者。创世纪终结者。创世纪终结者。创世纪", "陪安东尼度过漫长的岁月"
            , "赤裸女特工之夜序曲赤裸女特工之夜序曲赤裸女特工之夜序曲", "杀破狼", "捉妖记", "速度与激情7", "喜气洋洋小金莲----喜气洋洋小金莲-----喜气洋洋小金莲--金莲啊 大郎哪里去了", "我的青春期", "赤裸特工", "我是谁的2015", "速度与激情5", "这个杀手不太冷这个杀手不太冷这个杀手不太冷", "变形金刚", "谁的青春不热血谁的青春不热血谁的青春不热血"
            , "我是谁的2015", "速度与激情5", "这个杀手不太冷这个杀手不太冷这个杀手不太冷", "变形金刚", "谁的青春不热血谁的青春不热血谁的青春不热血"};
    private String[] imgurl = {"http://img.xiankan.com/af0502331b5fbca3f504.jpg",
            "http://img.xiankan.com/761faa01d3c0638f8d08.jpg",
            "http://img.xiankan.com/9dee32261681a9e61eaf.jpg",
            "http://img.xiankan.com/2e81864230de9f09d371.jpg",
            "http://img.xiankan.com/55ec1cf9a3be6f51e2f7.jpg",
            "http://img.xiankan.com/a1820b54f2b57e76082c.jpg",
            "http://img.xiankan.com/171585770b0859bcde4e.jpg",
            "http://img.xiankan.com/f9ddab552f64a2ae7e6b.jpg",
            "http://img.xiankan.com/bc9646f3f1c0dd82c860.jpg",
            "http://img.xiankan.com/31760ad63c653a866b72.jpg",
            "http://img.xiankan.com/5316b94e6f2e8e790dfc.jpg",
            "http://p1.qhimg.com/t01cbed7ececf242fac.jpg",
            "http://img.xiankan.com/190d0778df4717a86cb7.jpg",
            "http://img.xiankan.com/3fb9d28d7568ac736a40.jpg",
            "http://img.xiankan.com/485a81031a2a2b03dfef.jpg",
            "http://img.xiankan.com/3adaf2f57bb4440ee8a2.jpg",
            "http://img.xiankan.com/96811a796644afc698ed.jpg",
            "http://img.xiankan.com/e56e560bd86f079ccafa.jpg",
            "http://p4.qhimg.com/t0102e1a11ce0d7011a.jpg",
            "http://img.xiankan.com/3a8ceea57fa079af1123.jpg",
            "http://img.xiankan.com/96811a796644afc698ed.jpg",
            "http://img.xiankan.com/e56e560bd86f079ccafa.jpg",
            "http://p4.qhimg.com/t0102e1a11ce0d7011a.jpg",
            "http://img.xiankan.com/3a8ceea57fa079af1123.jpg",
            "http://img.xiankan.com/3a8ceea57fa079af1123.jpg",
            "http://img.xiankan.com/96811a796644afc698ed.jpg",
            "http://img.xiankan.com/e56e560bd86f079ccafa.jpg",
            "http://p4.qhimg.com/t0102e1a11ce0d7011a.jpg",
            "http://img.xiankan.com/3a8ceea57fa079af1123.jpg"};

    private List<FlowInfo> flowInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.flow_recycleview);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));//上下滚动
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));//左右滚动
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.GAP_HANDLING_NONE));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        passData();
        FlowAdapter flowAdapter = new FlowAdapter();
        recyclerView.setAdapter(flowAdapter);
        //设置item之间的间隔
//        SpacesItemDecoration decoration = new SpacesItemDecoration(50);
//        recyclerView.addItemDecoration(decoration);
    }

    private void passData() {
        flowInfos = new ArrayList<>();
        FlowInfo flowInfo;
        for (int i = 0; i < name.length; i++) {
            flowInfo = new FlowInfo(name[i], imgurl[i]);
            flowInfos.add(flowInfo);
        }
    }

    class FlowAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new FlowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.flowview_item, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            //
            if (holder instanceof FlowViewHolder) {
                FlowViewHolder flowViewHolder = (FlowViewHolder) holder;
                flowViewHolder.textView.setText(flowInfos.get(position).name);
                Glide.with(FlowViewActivity.this)
                        .load(flowInfos.get(position).imgurl)
                        .into(flowViewHolder.imageView);
            }
        }

        @Override
        public int getItemCount() {
            return flowInfos.size();
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }
    }

    public class FlowViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public FlowViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_grid);
            textView = (TextView) itemView.findViewById(R.id.text_grid);
        }

    }


    class FlowInfo {

        public FlowInfo(String name, String imgurl) {
            this.name = name;
            this.imgurl = imgurl;
        }

        String name;
        String imgurl;
    }

    class SpacesItemDecoration extends RecyclerView.ItemDecoration {

        private int space;

        public SpacesItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }
}
