package com.gzl_lib;

public class MyClass {
    //\[\w+\][\u4e00-\u9fa5_a-zA-Z0-9\*]+\[\/\w+\]
    public static void main(String[] args) {
        int[] numArray = new int[]{5, 3,1,5, 3};
        int aim = numArray[0];

        for (int i = 1; i < numArray.length; i++) {
            aim = aim ^ numArray[i];
            System.out.println(Integer.numberOfLeadingZeros(numArray[i]));
            System.out.println(Integer.numberOfTrailingZeros(numArray[i]));
            System.out.println(" I：" + i + " aim: " + aim);
        }
        System.out.println("最后：" + aim);

    }

}
