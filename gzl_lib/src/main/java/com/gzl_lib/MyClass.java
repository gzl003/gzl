package com.gzl_lib;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class MyClass {
//\[\w+\][\u4e00-\u9fa5_a-zA-Z0-9\*]+\[\/\w+\]
    public static void main(String[] args) {
//        WorkerProxy workerProxy = new WorkerProxy(new Worker());
//        workerProxy.eatChicken()
//                .sleep()
//                .travel()
//                .love();

//        DynamicProxy dynamicProxy = new DynamicProxy();
//
//        WorkInterface workInterface = (WorkInterface) dynamicProxy.newProxyInstance(new LiWorker());
//        workInterface.fixBigBug("内存泄漏");

//        OneWorkInterface oneWorkInterface = (OneWorkInterface) dynamicProxy.newProxyInstance(new HongWorker("洪工"));
//        oneWorkInterface.writeBug("空指针异常了");
//        oneWorkInterface.fixBug();

        String string = "观看完[t2]整影片，请[t1]购买[/t1]，已[t3]购买[t4]请登录[/t4]";
        List<String> strings = new ArrayList<>();
        int t1index = string.indexOf("[t1]");
        int t2index = string.indexOf("[t2]");
        int t3index = string.indexOf("[t3]");
        int t4index = string.indexOf("[t4]");

        Map<String, Integer> treeMap = new TreeMap<String, Integer>();
        if (t1index >= 0) {
            treeMap.put("[t1]", t1index);
        }
        if (t2index >= 0) {
            treeMap.put("[t2]", t2index);
        }
        if (t3index >= 0) {
            treeMap.put("[t3]", t3index);
        }
        if (t4index >= 0) {
            treeMap.put("[t4]", t4index);
        }

        TreeSet entries = (TreeSet) entriesSortedByValues(treeMap);

        for(Iterator iter = entries.iterator(); iter.hasNext(); ) {
            System.out.printf("asc : %s\n", iter.next());
        }
//        for (String key : treeMap.keySet()) {
//            System.out.println("key= " + key + " and value= " + treeMap.get(key));
//
//        }
//        if (string.contains("[t1]")) {
//            //开通会员  观看完整影片，请[t1]购买[/t1]，已购买[t4]请登录[/t4]
//            strings = Arrays.asList(string.split("[t1]"));
//            strings.addAll(Arrays.asList(strings.get(1).split("[/t1]")));
//
//
//        }
//        if (string.contains("[t2]")) {
//            //购买单片
//            for (int i = 0; i < strings.size(); i++) {
//                if (strings.get(i).contains("[t2]")) {
//                    strings.get(i).split("[t2]");
//                    strings.addAll(i, Arrays.asList(strings.get(i).split("[t2]")));
//
//                }
//
//            }
//        }
//        if (string.contains("[t3]")) {
//            //开通会员
//        }
//        if (string.contains("[t4]")) {
//            //登录
//        }

    }

    static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
        SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
                new Comparator<Map.Entry<K, V>>() {
                    @Override
                    public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
                        int res = e1.getValue().compareTo(e2.getValue());
                        return res != 0 ? res : 1; // Special fix to preserve items with equal values
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }
}
