package com.zhiguang.li;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.loader.ImageLoader;
import com.zhiguang.li.activity.ADebaseActivity;
import com.zhiguang.li.activity.AdaptrViewActivity;
import com.zhiguang.li.activity.BaseActivity;
import com.zhiguang.li.activity.BasicVideoActivity;
import com.zhiguang.li.activity.BitmapOrBase64Activity;
import com.zhiguang.li.activity.CodeLayActivity;
import com.zhiguang.li.activity.DesTest;
import com.zhiguang.li.activity.DrawerLayoutActivity;
import com.zhiguang.li.activity.EpisodeActivity;
import com.zhiguang.li.activity.FiltrateActivity;
import com.zhiguang.li.activity.FlowViewActivity;
import com.zhiguang.li.activity.FollowAnmia;
import com.zhiguang.li.activity.GPSActivity;
import com.zhiguang.li.activity.GifActivity;
import com.zhiguang.li.activity.GreenDaoActivity;
import com.zhiguang.li.activity.HasMapJsonActivity;
import com.zhiguang.li.activity.ImageSuoFang;
import com.zhiguang.li.activity.LikeAnimationActivity;
import com.zhiguang.li.activity.MadiaPlayerActivity;
import com.zhiguang.li.activity.MuseumActivity;
import com.zhiguang.li.activity.MySerActivity;
import com.zhiguang.li.activity.NestedScrollingActivity;
import com.zhiguang.li.activity.PaletteActivity;
import com.zhiguang.li.activity.PasswordinputActivity;
import com.zhiguang.li.activity.PathLineActivity;
import com.zhiguang.li.activity.PoorVisionActivity;
import com.zhiguang.li.activity.ProgressActivity;
import com.zhiguang.li.activity.PtrRecycleViewActivity;
import com.zhiguang.li.activity.RadiusImage;
import com.zhiguang.li.activity.RcycleViewImagetActivity;
import com.zhiguang.li.activity.RecyclerViewActivity;
import com.zhiguang.li.activity.ScrollingActivity;
import com.zhiguang.li.activity.SelectMovieActivity;
import com.zhiguang.li.activity.SkiddeleteActivity;
import com.zhiguang.li.activity.SlidingCloseActivity;
import com.zhiguang.li.activity.SparkActivity;
import com.zhiguang.li.activity.SuperSwipeRefreshLayoutActivity;
import com.zhiguang.li.activity.SwichButtonActivity;
import com.zhiguang.li.activity.SwioerefreshActivity;
import com.zhiguang.li.activity.TabTopActivity;
import com.zhiguang.li.activity.TabVpScrActivity;
import com.zhiguang.li.activity.TabswitchActivity;
import com.zhiguang.li.activity.TextInfoActivity;
import com.zhiguang.li.activity.VerticalSeekbarActivity;
import com.zhiguang.li.activity.WebViewActivity;
import com.zhiguang.li.activity.WebViewRefshActivity;
import com.zhiguang.li.activity.YUyunsuanActivity;
import com.zhiguang.li.network.NetworkObservable;
import com.zhiguang.li.network.NetworkObservable.NetConnectStatus;
import com.zhiguang.li.utils.CKEven;
import com.zhiguang.li.utils.GPSUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class MainActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener, Observer {

    private FragmentManager fragmentManager;
    private MyFragment myFragment;
    private FragentTwo fragentTwo;
    public Class[] classes = {RecyclerViewActivity.class, LikeAnimationActivity.class, RadiusImage.class, FiltrateActivity.class, SelectMovieActivity.class, AdaptrViewActivity.class, GPSActivity.class
            , TextInfoActivity.class, ADebaseActivity.class, EpisodeActivity.class, DrawerLayoutActivity.class, FollowAnmia.class, HasMapJsonActivity.class, DesTest.class, PathLineActivity.class
            , BasicVideoActivity.class, SwioerefreshActivity.class, MySerActivity.class, WebViewActivity.class, ProgressActivity.class, ScrollingActivity.class, FlowViewActivity.class, NestedScrollingActivity.class
            , CodeLayActivity.class, TabswitchActivity.class, TabVpScrActivity.class, TabTopActivity.class, SparkActivity.class, PtrRecycleViewActivity.class, BitmapOrBase64Activity.class
            , SuperSwipeRefreshLayoutActivity.class, MadiaPlayerActivity.class, GifActivity.class, MuseumActivity.class, PasswordinputActivity.class, ImageSuoFang.class, SkiddeleteActivity.class, VerticalSeekbarActivity.class
            , WebViewRefshActivity.class, SwichButtonActivity.class, RcycleViewImagetActivity.class, GreenDaoActivity.class, YUyunsuanActivity.class, SlidingCloseActivity.class, PoorVisionActivity.class
            , PaletteActivity.class
    };
    public String[] strings = {"RecyclerView", "点赞动画", "圆角图片", "分类", "选集", "AdapterViewFlipper", "gps"
            , "信息", "轮播图", "剧集", "drawer侧滑", "跟随动画", "map json", "destest", "贝塞尔曲线"
            , "视频录制", "google自家的刷新View", "服务测试", "webView测试", "加载框", "滚动的Activity", "RecyclerView 实现流式布局", "嵌套滚动", "CodeLayActivity", "TAB切换", "仿照微信左右切换", "TABlayout顶部切换", "闪光字体",
            "上下刷新的RecycleView", "图片和base64之间的转换", "SuperSwipeRefresh", "MediaPlayer测试", "gif图", "典藏馆", "密码输入框", "图片缩放", "侧滑删除item", "垂直进度条", "滑动的webView", "切换开关", "recycleImage",
            "GreenDao", "与 运算", "右滑关闭页面", "滚动视觉差", "Palette"};
    private List<MClassmodle> mClassmodles;
    private Banner banner;

    private String[] images = new String[]{"http://img.xiankan.com/c4659e4c9dba480d06c2.jpg",
            "http://img.xiankan.com/32fc897fa6f06e8a21c9.jpg",
            "http://img.xiankan.com/2cb0abb206cc5f2b2d8d.jpg",
            "http://img.xiankan.com/bacee2c41adad8a77e67.jpg",
            "http://img.xiankan.com/116689352ede0a879914.jpg"};
    private String[] titles = new String[]{"捉迷藏", "电影留声机", "地狱宝贝", "亲见爱的", "斗龙战士"};
//    private PlayerNetworkHelper mNetworkHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textview = (TextView) findViewById(R.id.textview);
        textview.setOnClickListener(this);
        GPSUtils.getInstance().onCreate(this);
        fragmentManager = getSupportFragmentManager();
        initBanner();
        openGPS(this);
        GridView mainListview = (GridView) findViewById(R.id.main_listview);
        MainAdapter mainAdapter = new MainAdapter();
        mainListview.setAdapter(mainAdapter);
        mainListview.setOnItemClickListener(this);

//        mNetworkHelper = new PlayerNetworkHelper(this);
        NetworkObservable.getInstance(this).addObserver(this);
//        mNetworkHelper.onCreate();
    }

    private void initBanner() {
        banner = (Banner) findViewById(R.id.main_banner);

        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
//        //设置指示器位置（当banner模式中有指示器时）
//        banner.setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器
        banner.setImageLoader(new MyImageLoader());
        //设置图片集合
        banner.setImages(Arrays.asList(images));
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(Arrays.asList(titles));
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.ZoomIn);

        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);

        //banner设置方法全部调用完毕时最后调用
        banner.start();

        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getApplicationContext(), "你点击了：" + titles[position - 1], Toast.LENGTH_LONG).show();
            }//设置点击事件
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        banner.isAutoPlay(true);
        banner.start();
        if (mClassmodles != null) {
            mClassmodles.clear();
        } else {
            mClassmodles = new ArrayList<>();
        }
        for (int i = 0; i < classes.length; i++) {
            MClassmodle mClassmodle = new MClassmodle(strings[i], classes[i]);
            mClassmodles.add(mClassmodle);
        }
//        mNetworkHelper.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        banner.isAutoPlay(false);
//        mNetworkHelper.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mNetworkHelper.onDestroy();
        NetworkObservable.getInstance(this).deleteObserver(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textview:
                switchFragment(0);
                CKEven.ckEvenStat("选择Fragment", "分类");
                break;
        }
    }

//    private void setListener() {
//        NetWorkChecker.checkNetWorkPlayer(this, new NetWorkChecker.NetWorkCheckListener() {
//            @Override
//            public void onNetWorkOk(boolean is3G) {
//                if (is3G) {
//                    //3g 不允许  给出提示
//                    Toast.makeText(MainActivity.this, "网络存在是运营商网络", Toast.LENGTH_SHORT).show();
//                } else {
//                    //不是3g
//                    Toast.makeText(MainActivity.this, "网络存在是WIFI", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailed(boolean is3G) {
//                if (is3G) {
//                    //网络存在 3g
//                    Toast.makeText(MainActivity.this, "onFailed 网络存在是运营商网络", Toast.LENGTH_SHORT).show();
//                } else {
//                    //网络中断
//                    Toast.makeText(MainActivity.this, "onFailed 网络断开了", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

    /**
     * 将所有的Fragment都置为隐藏状态
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (myFragment != null) {
            transaction.hide(myFragment);
        }
        if (fragentTwo != null) {
            transaction.hide(fragentTwo);
        }
    }

    /**
     * 切换fragment页面
     */
    public void switchFragment(int index) {
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        //noinspection ResourceType
//        transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.fragment_slide_left_out);
        switch (index) {
            case 0:
                // 练习
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    if (!myFragment.isAdded()) {
                        transaction.add(R.id.bootrem_view, myFragment);
                    }
                } else {
                    transaction.show(myFragment);
                }

                break;
            case 1:
                // 学习
                if (fragentTwo == null) {
                    fragentTwo = new FragentTwo();
                    if (!fragentTwo.isAdded()) {
                        transaction.add(R.id.bootrem_view, fragentTwo);
                    }
                } else {
                    transaction.show(fragentTwo);
                }
                break;
        }
        transaction.commit();
    }


    @Override
    public void doResult(int action) {
        super.doResult(action);
        switch (action) {
            case RIGHT:
                Toast.makeText(this, "RIGHT", Toast.LENGTH_SHORT).show();
                break;
            case LEFT:
                Toast.makeText(this, "LEFT", Toast.LENGTH_SHORT).show();
                break;
            case TOP:
                Toast.makeText(this, "TOP", Toast.LENGTH_SHORT).show();
                break;
            case BOTTOM:
                Toast.makeText(this, "BOTTOM", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    /**
     * 强制帮用户打开GPS
     *
     * @param context 上下文
     */
    public void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings",
                "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, mClassmodles.get(position).aClass);
        startActivity(intent);
    }

    /**
     * 网络变化的更新回调
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg != null && arg instanceof NetConnectStatus) {
            Log.e("update", "update net:" + arg);
            if (arg.equals(NetConnectStatus.DISCONNECTED)) {
                Toast.makeText(this, "断网啦", Toast.LENGTH_SHORT).show();
            } else if (arg.equals(NetConnectStatus.CONNECTED_WIFI)) {
                Toast.makeText(this, "WIFI", Toast.LENGTH_SHORT).show();
            } else if (arg.equals(NetConnectStatus.CONNECTED_3G)) {
                Toast.makeText(this, "运营商网络", Toast.LENGTH_SHORT).show();
            }
        }
    }

    class MainAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (mClassmodles != null) {
                return mClassmodles.size();
            } else {
                return 0;
            }
        }

        @Override
        public Object getItem(int position) {
            return mClassmodles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHodler viewHodler;
            if (convertView == null) {
                viewHodler = new ViewHodler();
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.main_item_layout, null);
                viewHodler.textView = (TextView) convertView.findViewById(R.id.item_text);
                convertView.setTag(viewHodler);
            } else {
                viewHodler = (ViewHodler) convertView.getTag();
            }
            viewHodler.textView.setText(mClassmodles.get(position).name);
            return convertView;
        }
    }

    class ViewHodler {
        TextView textView;
    }

    class MClassmodle {
        public MClassmodle(String name, Class aClass) {
            this.name = name;
            this.aClass = aClass;
        }

        public String name;
        public Class aClass;
    }

    public class MyImageLoader implements ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            /**
             常用的图片加载库：
             Universal Image Loader：一个强大的图片加载库，包含各种各样的配置，最老牌，使用也最广泛。
             Picasso: Square出品，必属精品。和OkHttp搭配起来更配呦！
             Volley ImageLoader：Google官方出品，可惜不能加载本地图片~
             Fresco：Facebook出的，天生骄傲！不是一般的强大。
             Glide：Google推荐的图片加载库，专注于流畅的滚动。
             */
            Glide.with(context)
                    .load(path)
                    .into(imageView);

        }
    }
}
