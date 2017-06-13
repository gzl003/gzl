package com.zhiguang.li.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zhiguang.li.been.DaoMaster;
import com.zhiguang.li.been.DaoSession;
import com.zhiguang.li.been.Dog;
import com.zhiguang.li.been.DogDao;
import com.zhiguang.li.been.User;
import com.zhiguang.li.been.UserDao;

import java.util.ArrayList;
import java.util.List;

/**
 *  * Created by 智光 on 2017/6/5 18:33
 *  
 */
public class DBManager {
    UserDao userDao;
    DogDao dogDao;

    public DBManager(Context context) {
        DaoMaster.DevOpenHelper  helper = new DaoMaster.DevOpenHelper(context, "JackWaiting-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
        dogDao = daoSession.getDogDao();
    }

    public ArrayList<User> getDatasByType(int type){
        return (ArrayList<User>) userDao.queryBuilder().where(UserDao.Properties.Name.eq(type)).list();
    }

    public List<User> getUserAll(){
        return  userDao.loadAll();
    }

    public List<Dog> getDogAll(){
        return  dogDao.loadAll();
    }

    public void insertUser(User user){
        userDao.insert(user);
    }

    public void insertDog(Dog dag){
        dogDao.insert(dag);
    }

    public void deleteUser(User user){
        userDao.delete(user);
    }

    public void deleteAll(){
        userDao.deleteAll();
    }

    public void updateUser(User user){
        userDao.update(user);
    }



}
