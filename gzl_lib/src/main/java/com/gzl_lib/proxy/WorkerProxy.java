package com.gzl_lib.proxy;

/**
 *  * Created by 智光 on 2019/8/27 11:34
 *  管理干活的人
 */
public class WorkerProxy implements PersionManager<Worker> {

    private Worker worker;

    public WorkerProxy(Worker worker) {
        this.worker = worker;
    }

    @Override
    public Worker eatChicken() {
        return worker.eatChicken();
    }

    @Override
    public Worker sleep() {
        return worker.sleep();
    }

    @Override
    public Worker travel() {
        return worker.travel();
    }

    @Override
    public Worker love() {
        return worker.love();
    }
}
