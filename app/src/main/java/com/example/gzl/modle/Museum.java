package com.example.gzl.modle;

import java.io.Serializable;
import java.util.List;

/**
 *  * Created by 智光 on 2016/12/28 11:36
 *  
 */

public class Museum implements Serializable {


    /**
     * code : 2000
     * message : message
     * result : {"head":[{"id":"308","title":"如果爱","poster":"http://img5.wsy1.com/42f72be7f3e8765ed552.jpg","summaries":"孙纳出身贫苦但志存高远，为了生存和成功，她不择手段，终于成为人人艳羡的大明星，然而命运弄人，十年之后在她要努力忘记一切的时候，她生命中两个重要的男人同时出现了\u2014\u2014三人要同演一出戏，剧情和命运却惊人的相似，戏里戏外，已难分真假，在孙纳的心中，到底有没有爱过，他与他，究竟如何抉择\u2026\u2026","stills":"http://img6.wsy1.com/ab59297d24996562b2ba.jpg","desc":"","dir":"陈可辛","dir_pic":"","act":"周迅 / 金城武 / 张学友 / 池珍熙 / 曾志伟 / 吴君如 / 薛之谦","showtimes":"1133366400","copyright_starttime":"1476029605","copyright_endtime":"1509380005","alias":"如果爱","star":"","highlights":"在这个世界上，最爱你的还是自己。","type":"1","rating":"7.6","duration":"6495","stream_url":"ea7ff590-081d-49d3-a2d3-5cafa8692ea9","look_url":"28593618-3809-4344-80ff-195c6413e4b9","pid":"","up":"","pcstills":"http://img4.wsy1.com/4647b66b129e6a25a785.jpg","pcslogan":"人生如戏，悲欢离合如戏一场。短，不过一瞬。长，不过永远。","appslogan":"在这个世界上，最爱你的还是自己。","area":"中国","winning":"","padpic_head":"","padpic_1":"","padpic_2":"","padpic_3":"","padpic_4":""}],"list":[{"channelName":"纷争世代|shidai","channelID":"14295","listUrl":"/app_dev/b/a/dclist.shtml"},{"channelName":"合家欢喜|huanxi","channelID":"14296","listUrl":"/app_dev/b/b/dclist.shtml"},{"channelName":"人世浮沉|fuchen","channelID":"14297","listUrl":"/app_dev/b/c/dclist.shtml"}]}
     */

    public String code;
    public String message;
    public ResultBean result;

    public static class ResultBean {
        /**
         * id : 308
         * title : 如果爱
         * poster : http://img5.wsy1.com/42f72be7f3e8765ed552.jpg
         * summaries : 孙纳出身贫苦但志存高远，为了生存和成功，她不择手段，终于成为人人艳羡的大明星，然而命运弄人，十年之后在她要努力忘记一切的时候，她生命中两个重要的男人同时出现了——三人要同演一出戏，剧情和命运却惊人的相似，戏里戏外，已难分真假，在孙纳的心中，到底有没有爱过，他与他，究竟如何抉择……
         * stills : http://img6.wsy1.com/ab59297d24996562b2ba.jpg
         * desc :
         * dir : 陈可辛
         * dir_pic :
         * act : 周迅 / 金城武 / 张学友 / 池珍熙 / 曾志伟 / 吴君如 / 薛之谦
         * showtimes : 1133366400
         * copyright_starttime : 1476029605
         * copyright_endtime : 1509380005
         * alias : 如果爱
         * star :
         * highlights : 在这个世界上，最爱你的还是自己。
         * type : 1
         * rating : 7.6
         * duration : 6495
         * stream_url : ea7ff590-081d-49d3-a2d3-5cafa8692ea9
         * look_url : 28593618-3809-4344-80ff-195c6413e4b9
         * pid :
         * up :
         * pcstills : http://img4.wsy1.com/4647b66b129e6a25a785.jpg
         * pcslogan : 人生如戏，悲欢离合如戏一场。短，不过一瞬。长，不过永远。
         * appslogan : 在这个世界上，最爱你的还是自己。
         * area : 中国
         * winning :
         * padpic_head :
         * padpic_1 :
         * padpic_2 :
         * padpic_3 :
         * padpic_4 :
         */

        public List<HeadBean> head;
        /**
         * channelName : 纷争世代|shidai
         * channelID : 14295
         * listUrl : /app_dev/b/a/dclist.shtml
         */

        public List<ListBean> list;

        public static class HeadBean {
            public String id;
            public String title;
            public String poster;
            public String summaries;
            public String stills;
            public String desc;
            public String dir;
            public String dir_pic;
            public String act;
            public String showtimes;
            public String copyright_starttime;
            public String copyright_endtime;
            public String alias;
            public String star;
            public String highlights;
            public String type;
            public String rating;
            public String duration;
            public String stream_url;
            public String look_url;
            public String pid;
            public String up;
            public String pcstills;
            public String pcslogan;
            public String appslogan;
            public String area;
            public String winning;
            public String padpic_head;
            public String padpic_1;
            public String padpic_2;
            public String padpic_3;
            public String padpic_4;
        }

        public static class ListBean {
            public String channelName;
            public String channelID;
            public String listUrl;
        }
    }


    public static class MuseunInfo extends BaseMuseum{
        public String id;
        public String title;
        public String stills;
        public String type;
        public String duration;
        public String appslogan;
        public String rating;
        public List<ResultBean.HeadBean> banner;
    }

}
