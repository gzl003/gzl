package com.gzl_lib;

import com.gzl_lib.dynamic.DynamicProxy;
import com.gzl_lib.dynamic.LiWorker;
import com.gzl_lib.dynamic.WorkInterface;

public class MyClass {

    public static void main(String[] args) {
//        WorkerProxy workerProxy = new WorkerProxy(new Worker());
//        workerProxy.eatChicken()
//                .sleep()
//                .travel()
//                .love();
        DynamicProxy dynamicProxy = new DynamicProxy();

        WorkInterface workInterface = (WorkInterface) dynamicProxy.newProxyInstance(new LiWorker());
        workInterface.fixBigBug("内存泄漏");

//        OneWorkInterface oneWorkInterface = (OneWorkInterface) dynamicProxy.newProxyInstance(new HongWorker("洪工"));
//        oneWorkInterface.writeBug("空指针异常了");
//        oneWorkInterface.fixBug();

    }
}
