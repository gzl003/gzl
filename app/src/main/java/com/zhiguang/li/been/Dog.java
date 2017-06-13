package com.zhiguang.li.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 *  * Created by 智光 on 2017/6/6 10:32
 *  
 */
@Entity
public class Dog {
    @Id
    private int id;
    private String name;
    private String cretry;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCretry() {
        return cretry;
    }

    public void setCretry(String cretry) {
        this.cretry = cretry;
    }

    @Generated(hash = 1725285966)
    public Dog(int id, String name, String cretry) {
        this.id = id;
        this.name = name;
        this.cretry = cretry;
    }

    @Generated(hash = 2001499333)
    public Dog() {
    }


}
