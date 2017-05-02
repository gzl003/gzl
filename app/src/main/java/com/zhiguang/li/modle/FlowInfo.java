package com.zhiguang.li.modle;

/**
 *  * Created by 智光 on 2017/4/26 12:17
 *  
 */

public class FlowInfo {

    public FlowInfo(String name, String imgurl) {
        this.name = name;
        this.imgurl = imgurl;
    }

    private String name;
    private String imgurl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
