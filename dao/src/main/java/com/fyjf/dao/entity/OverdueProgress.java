package com.fyjf.dao.entity;

import java.io.Serializable;

/**
 * Created by ASUS on 2017/5/23.
 */
public class OverdueProgress implements Serializable{


    /**
     * id : c39609f70b7f4e5f9f401898a48af6cf
     * createDate : 2017-06-27 12:58:16
     * overdueId : 14c5f8e40a4e4fa5a619b6581bc9f4ec
     * title : 222
     * description : 222
     * overdueImgs : b0f9a498-a1e2-491b-91cf-4911955711c8.png
     * money : 2323
     */

    private String id;
    private String createDate;
    private String overdueId;
    private String title;
    private String description;
    private String overdueImgs;
    private String money;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getOverdueId() {
        return overdueId;
    }

    public void setOverdueId(String overdueId) {
        this.overdueId = overdueId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOverdueImgs() {
        return overdueImgs;
    }

    public void setOverdueImgs(String overdueImgs) {
        this.overdueImgs = overdueImgs;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
