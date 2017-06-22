package com.zhiguang.li.been;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.zhiguang.li.been.Dog;
import com.zhiguang.li.been.User;

import com.zhiguang.li.been.DogDao;
import com.zhiguang.li.been.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig dogDaoConfig;
    private final DaoConfig userDaoConfig;

    private final DogDao dogDao;
    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        dogDaoConfig = daoConfigMap.get(DogDao.class).clone();
        dogDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        dogDao = new DogDao(dogDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(Dog.class, dogDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        dogDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
    }

    public DogDao getDogDao() {
        return dogDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}