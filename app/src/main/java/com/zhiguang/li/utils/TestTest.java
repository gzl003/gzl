package com.zhiguang.li.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  * Created by 智光 on 2019/3/28 09:47
 *  
 */

public class TestTest {

    static int i = 0;

    public static void main(String[] str) {

        String regEx = "[^0-9.]";

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher("v4.2.4qa_Beta");
        String result = m.replaceAll("").trim();
        System.out.println(result);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (i < 5) {
                    try {
                        synchronized (this) {
                            System.out.println(i);
                            wait(1500);
                            i++;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }
}
