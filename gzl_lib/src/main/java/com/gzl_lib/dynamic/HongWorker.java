package com.gzl_lib.dynamic;

/**
 *  * Created by 智光 on 2019/8/27 13:36
 *  洪工
 * 被代理的对象
 */
public class HongWorker implements OneWorkInterface<HongWorker> {

    private String name;

    public HongWorker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public HongWorker fixBug() {
        System.out.println(name + ": 修复bug");
        return this;
    }

    @Override
    public HongWorker writeBug(String str) {
        System.out.println(name + ": 写bug" + str);
        return this;
    }


}
