package com.gzl_lib.proxy;

/**
 *  * Created by 智光 on 2019/8/27 11:30
 *  工人
 * 真正干活的人
 */
public class Worker implements PersionManager<Worker> {

    @Override
    public Worker eatChicken() {
        System.out.println("吃鸡了");
        return this;
    }

    @Override
    public Worker sleep() {
        System.out.println("休息了睡觉了");
        return this;
    }

    @Override
    public Worker travel() {
        System.out.println("去旅行ing");
        return this;
    }

    @Override
    public Worker love() {
        System.out.println("恋爱了");
        return this;
    }
}
