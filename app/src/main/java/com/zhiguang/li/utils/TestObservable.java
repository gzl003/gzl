package com.zhiguang.li.utils;

import java.util.Observable;

/**
 *  * Created by 智光 on 2019/1/15 16:08
 *  被观察者
 *
 * 被观察者做成单例类 比较完美
 */
public class TestObservable extends Observable {

    public void addone(int number) {
        setChanged();
        this.notifyObservers("加一=" + number);

    }

    public void removeone(int number) {
        setChanged();

        this.notifyObservers("减一=" + number);

    }


}
