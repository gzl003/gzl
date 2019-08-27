package com.gzl_lib.dynamic;

/**
 *  * Created by 智光 on 2019/8/27 13:35
 *  李工
 * 被代理的对象
 */
@gzlone(name = "高级李工")
public class LiWorker implements WorkInterface {

    @Override
    public LiWorker writeCode() {
        System.out.println(": 搬砖搬砖....");
        return this;
    }

    @Override
    public LiWorker fixBigBug(String str) {
        System.out.println(": 修复重大bug" + str);
        return this;
    }
}
