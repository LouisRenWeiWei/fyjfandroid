package com.fyjf.dao.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2017/6/25.
 */
/*
* author: renweiwei
* datetime: 
*/
public class OverdueReport implements Serializable{
    private static final long serialVersionUID = 22222222222222222L;
    private String id;
    private Customer customer;		// 客户id
    private String overduePdf;		// 催收方案PDF
    private String overdueDays;		// 逾期天数
    private String createDate;

    private List<OverdueProgress> overdueProgress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OverdueProgress> getOverdueProgress() {
        return overdueProgress;
    }

    public void setOverdueProgress(List<OverdueProgress> overdueProgress) {
        this.overdueProgress = overdueProgress;
    }

    public String getOverduePdf() {
        return overduePdf;
    }

    public void setOverduePdf(String overduePdf) {
        this.overduePdf = overduePdf;
    }

    public String getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(String overdueDays) {
        this.overdueDays = overdueDays;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
