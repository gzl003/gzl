package com.example.gzl.myapplication.modle;

import java.util.List;

/**
 * Created
 * 行为统计
 */
public class ActionStatist {
    public String ip;//手机的ip
    public String mac;//手机的mac地址
    public String terminalType;//终端类型
    public double longitude;//经度
    public double latitude;//纬度
    public String interType;//网络的类型
    public String deviceNum;//手机的标识
    public boolean isExtrSD;//是否有内存卡
    public String versionCode;//版本号

    public List<SlideAction> slideActions;//滑动行为
    public List<ClickAction> clickActions;//点击行为
    public List<PageCutAction> pageCutActions;//页面切换行为

    /**
     * 滑动行为
     */
    class SlideAction {
        public String pagename;//滑动页面的名字
        public String slideTo;//滑动的方向
    }

    /**
     * 点击行为
     */
    class ClickAction {
        public String clickintent;//切换意图
        public String clickname;
        public int clicknum;
    }

    /**
     * 页面切换行为
     */
    class PageCutAction {
        public String catintent;//切换意图
        public String fromPage;
        public String toPage;
    }
}
