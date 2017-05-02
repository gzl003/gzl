package com.zhiguang.li.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.zhiguang.li.R;
import com.zhiguang.li.adapter.ImageRecycleAdapter;
import com.zhiguang.li.modle.FlowInfo;

import java.util.ArrayList;
import java.util.List;

public class RcycleViewImagetActivity extends AppCompatActivity {

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

    private RecyclerView image_recycle;
    private ImageRecycleAdapter adapter;
    private List<FlowInfo> flowInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rcycle_view_imaget);
        image_recycle = (RecyclerView) findViewById(R.id.image_recycle);
        setDate();
        adapter = new ImageRecycleAdapter(this, flowInfos);
        image_recycle.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器
        image_recycle.setAdapter(adapter);
    }

    private void setDate() {
        flowInfos = new ArrayList<FlowInfo>();
        FlowInfo flowInfo;
        for (int i = 0; i < name.length; i++) {
            flowInfo = new FlowInfo(name[i], imgurl[i]);
            flowInfos.add(flowInfo);
        }
    }
}
