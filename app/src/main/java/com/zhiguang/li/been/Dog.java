package com.zhiguang.li.been;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 *  * Created by 智光 on 2017/6/6 10:32
 *  
 */
@Entity
public class Dog {
    @Id
    private long id;
    private String name;
    private String cretry;
    public String getCretry() {
        return this.cretry;
    }
    public void setCretry(String cretry) {
        this.cretry = cretry;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Generated(hash = 817338413)
    public Dog(long id, String name, String cretry) {
        this.id = id;
        this.name = name;
        this.cretry = cretry;
    }
    @Generated(hash = 2001499333)
    public Dog() {
    }
    


}
