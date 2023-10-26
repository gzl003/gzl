package com.gzl_lib;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MyClass {
    //\[\w+\][\u4e00-\u9fa5_a-zA-Z0-9\*]+\[\/\w+\]
    public static void main(String[] args) {


        BigDecimal b1 = BigDecimal.valueOf(2);
        BigDecimal b2 = BigDecimal.valueOf(2);
        System.out.println(b1.divide(b2, 0, RoundingMode.HALF_DOWN).toString());
        System.out.println(b1.divide(b2, 0, RoundingMode.FLOOR).toString());
        System.out.println('I' + 'T');

        int a = 0;
        do {
            a += 1;
            System.out.println("a: " + a);
        } while (a < 0);
        do {
            a += 1;
            System.out.println("a: " + a);
        } while (a < 2);
        do {
            a += 1;
            System.out.println("a: " + a);
        } while (a < 5);
    }

}
