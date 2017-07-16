package com.fyjf.dao.entity;

import java.io.Serializable;

/**
 * Created by ASUS on 2017/6/25.
 */
/*
* author: renweiwei
* datetime: 
*/
public class OverdueReport implements Serializable{

    /**
     * overdueImgs : a37c8d8ee198.png
     * money : 2323434
     * overdueStart : 1498626723000
     * overduePDF : 362a7ca6-d66a-40b1-9796-3a15bc9617b6.pdf
     * overdueId : 7632bd4e76184a07be9801d09d5d93b3
     * customerId : 4b1340b4a45b4e35bee4b9978ccd0cc2
     * overdueDays : 112
     * msgCount : 0
     * managerId : 06ffa40eabd442fa902fe44c1620226e
     * managerName : test
     * customerName : 2323
     */

    private String overdueImgs;
    private String money;
    private String overdueStart;
    private String overduePDF;
    private String overdueId;
    private String customerId;
    private int overdueDays;
    private int msgCount;
    private String managerId;
    private String managerName;
    private String customerName;

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

    public String getOverdueStart() {
        return overdueStart;
    }

    public void setOverdueStart(String overdueStart) {
        this.overdueStart = overdueStart;
    }

    public String getOverduePDF() {
        return overduePDF;
    }

    public void setOverduePDF(String overduePDF) {
        this.overduePDF = overduePDF;
    }

    public String getOverdueId() {
        return overdueId;
    }

    public void setOverdueId(String overdueId) {
        this.overdueId = overdueId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(int overdueDays) {
        this.overdueDays = overdueDays;
    }

    public int getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(int msgCount) {
        this.msgCount = msgCount;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
