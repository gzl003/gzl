package com.zhiguang.li.utils;

import java.util.Observable;
import java.util.Observer;

/**
 *  * Created by 智光 on 2019/1/15 16:13
 *  观察者
 */
public abstract class TestObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        onDataChanged("观察到的变化", arg);
    }

    public abstract void onDataChanged(String message, Object object);
}
