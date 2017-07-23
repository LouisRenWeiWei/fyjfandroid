package com.fyjf.dao.entity;

import java.io.Serializable;

/**
 * Created by czf on 2017/7/23.
 */

public class MangerModel implements Serializable {

    /**
     * name : 员工1
     * id : 025a62f835dc49db9259bcada943fad1
     */

    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
}
