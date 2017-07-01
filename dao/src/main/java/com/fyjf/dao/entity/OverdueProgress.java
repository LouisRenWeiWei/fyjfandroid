package com.fyjf.dao.entity;

import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by ASUS on 2017/5/23.
 */
public class OverdueProgress implements Serializable{
    private static final long serialVersionUID = 111111111111L;
    private String id;
    private String overdueId;		// 逾期方案id
    private String title;		// 标题
    private String description;		// 说明
    private String overdueImgs;		// 照片

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
