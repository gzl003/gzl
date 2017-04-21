package com.zhiguang.li.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhiguang.li.R;
import com.zhiguang.li.adapter.MuseumRecycleViewAdapter;
import com.zhiguang.li.adapter.MuseumSpanSizeLookup;
import com.zhiguang.li.modle.Museum;
import com.zhiguang.li.modle.MuseunMovie;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MuseumActivity extends AppCompatActivity {

    private static final String HOST = "https://devbeta.huanxi.com/";
    private Museum museum;
    private MuseunMovie museunMovie;

    //    private ConvenientBanner convenientBanner;
    private RecyclerView recyclerView;
    private MuseumRecycleViewAdapter viewAdapter;
    private List<Museum.MuseunInfo> museumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum);
        initData();
        initView();
    }

    private void initView() {
//        convenientBanner = (ConvenientBanner) findViewById(R.id.header_banner);
        recyclerView = (RecyclerView) findViewById(R.id.museum_list);
        viewAdapter = new MuseumRecycleViewAdapter(this);
        recyclerView.setAdapter(viewAdapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        MuseumSpanSizeLookup lookup = new MuseumSpanSizeLookup(viewAdapter, layoutManager);
        layoutManager.setSpanSizeLookup(lookup);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void initData() {
        museumList = new ArrayList<>();
        String url = HOST + "app_dev/b/channel_list.shtml";
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MuseumActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (response != null && !response.isEmpty()) {
//                            try {
//                                JSONObject json = new JSONObject(response);
//                                JSONObject jsonString = json.getJSONObject("result");
                                museum = JSON.parseObject(response, Museum.class);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }

                        }
                        if (museum != null) {
                            Toast.makeText(MuseumActivity.this, museum.toString(), Toast.LENGTH_SHORT).show();
                            paseHeadData(museum);

                            for (int i = 0; i < museum.result.list.size(); i++) {
                                initMuseum(museum.result.list.get(i).listUrl, i);
                            }
                        }
                    }
                });
    }

    private void initMuseum(String path, final int index) {
        OkHttpUtils
                .get()
                .url(HOST + path)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MuseumActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        if (response != null && !response.isEmpty()) {
//                            museum = JSON.parseObject(response, Museum.class);
//                            JSONObject json = null;
//                            try {
//                                json = new JSONObject(response);
//                                JSONObject jsonString = json.getJSONObject("result");
                                museunMovie = JSON.parseObject(response, MuseunMovie.class);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }

                        }
                        if (museunMovie != null) {
                            Museum.MuseunInfo museunInfo = new Museum.MuseunInfo();
                            museunInfo.title = museum.result.list.get(index).channelName;
                            museunInfo.viewtype = MuseumRecycleViewAdapter.ITEM_VIEW_TYPE_TITLE;
                            museumList.add(museunInfo);
                            paseData(museunMovie);
                        }
//                        JLog.e("initMuseum", index + "");
//                        if (index >= 3) {
                        viewAdapter.setMuseumList(museumList);
//                        }
                    }

                });
    }

    private void setView() {
//自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
//        convenientBanner.setPages(
//                new CBViewHolderCreator<BannerView>() {
//                    @Override
//                    public BannerView createHolder() {
//                        return new BannerView();
//                    }
//                }, museum.head)
//                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
//                .setPageIndicator(new int[]{R.drawable.focus_un, R.drawable.focus})
//                //设置指示器的方向
//                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
        //设置翻页的效果，不需要翻页效果可用不设
        //.setPageTransformer(Transformer.DefaultTransformer);    集成特效之后会有白屏现象，新版已经分离，如果要集成特效的例子可以看Demo的点击响应。
//        convenientBanner.setManualPageable(false);//设置不能手动影响
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void paseHeadData(Museum museum) {
        Museum.MuseunInfo museunInfo = new Museum.MuseunInfo();
        museunInfo.banner = museum.result.head;
        museunInfo.viewtype = MuseumRecycleViewAdapter.ITEM_VIEW_TYPE_BANNER;
        museumList.add(museunInfo);
    }

    public void paseData(MuseunMovie museunMovie) {
        MuseunMovie.ResultBean.ListBean listBean;
        for (int i = 0; i < museunMovie.result.list.size(); i++) {
            Museum.MuseunInfo museunInfo = new Museum.MuseunInfo();
            listBean = museunMovie.result.list.get(i);
            museunInfo.appslogan = listBean.appslogan;
            museunInfo.duration = listBean.duration;
            museunInfo.id = listBean.id;
            museunInfo.rating = listBean.rating;
            museunInfo.stills = listBean.stills;
            museunInfo.title = listBean.title;
            museunInfo.type = listBean.type;
            if (i == 0) {
                museunInfo.viewtype = MuseumRecycleViewAdapter.ITEM_VIEW_TYPE_BIG;
            } else {
                museunInfo.viewtype = MuseumRecycleViewAdapter.ITEM_VIEW_TYPE_SMALL;
            }
            museumList.add(museunInfo);
        }

    }
}
