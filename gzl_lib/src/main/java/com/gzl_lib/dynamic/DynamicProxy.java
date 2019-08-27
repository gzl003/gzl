package com.gzl_lib.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  * Created by 智光 on 2019/8/27 12:14
 *  动态代理类
 */
public class DynamicProxy implements InvocationHandler {
    private Object targetObject;

    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        //第一个参数指定产生代理对象的类加载器，需要将其指定为和目标对象同一个类加载器
        //第二个参数要实现和目标对象一样的接口，所以只需要拿到目标对象的实现接口
        //第三个参数表明这些被拦截的方法在被拦截时需要执行哪个InvocationHandler的invoke方法
        //根据传入的目标返回一个代理对象
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

    /**
     * @param o
     * @param method  被调用的方法
     * @param objects 方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        if (objects != null) {
            System.out.println("----------------传递参数:---------------------------");
            for (Object object : objects) {
                System.out.println(object);
            }
            System.out.println("------------------传递参数 end-------------------------------- ");
            System.out.println();
        } else {
            System.out.println("------------------没有传递参数 --------------------------- ");
            System.out.println();
        }
        try {
            gzlone gzlone = targetObject.getClass().getAnnotation(gzlone.class);
            if (gzlone != null) {
                System.out.println(gzlone.name() + "干活前-- " + System.currentTimeMillis());
                method.invoke(targetObject, objects);
                System.out.println(gzlone.name() + "干完活之后--" + System.currentTimeMillis());
            } else {
                System.out.println(targetObject.getClass().getSimpleName() + "干活前-- " + System.currentTimeMillis());
                method.invoke(targetObject, objects);
                System.out.println("干完活之后--" + System.currentTimeMillis());
                System.out.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error-->>");
            throw e;
        }
        return o;
    }
}
