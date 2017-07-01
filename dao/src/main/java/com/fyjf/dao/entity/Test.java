package com.fyjf.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ASUS on 2017/5/23.
 */
@Entity
public class Test {
    @Id
    private int id;

    @Generated(hash = 442027691)
    public Test(int id) {
        this.id = id;
    }

    @Generated(hash = 372557997)
    public Test() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
