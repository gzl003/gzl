package com.zhiguang.li.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * 把Hshmap转换成json
 * Created by 智光 on 2016/6/16.
 */
public class MapToJson {
    /**
     * 把数据源HashMap转换成json
     *
     * @param map
     */
    public static String hashMapToJson(HashMap map) {
        String string = "{";
        for (Iterator it = map.entrySet().iterator(); it.hasNext(); ) {
            Entry e = (Entry) it.next();
            string += "'" + e.getKey() + "':";
            string += "'" + e.getValue() + "',";
        }
        string = string.substring(0, string.lastIndexOf(","));
        string += "}";
        return string;
    }
}
