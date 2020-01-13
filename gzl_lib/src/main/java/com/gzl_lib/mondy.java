package com.gzl_lib;

import java.util.ArrayList;
import java.util.List;

/**
 *  * Created by 智光 on 2019/9/17 18:31
 *  
 */
public class mondy {


    public List<List<Day>> getWeekLists(List<Day> list) {
        List<List<Day>> weekLists = new ArrayList<>();
        List<Day> weeek = new ArrayList<Day>();

        for (int i = 0; i < list.size(); i++) {
            list.get(i).formatweek();
            if (list.get(i).week == 1) {//判断周一
                weeek = new ArrayList<Day>();
                weeek.add(list.get(i));
                weekLists.add(weeek);
            } else {
                weeek.add(list.get(i));
            }
        }
        return weekLists;
    }

    class Day {
        public int week;

        public void formatweek() {
            week = 1;//格式周
        }
    }
}
