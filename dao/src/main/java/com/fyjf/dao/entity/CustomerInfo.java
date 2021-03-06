package com.fyjf.dao.entity;

/**
 * Created by ASUS on 2017/6/28.
 */
/*
* author: renweiwei
* datetime: 
*/
public class CustomerInfo {
//    private String id;
//    private String name;		// 客户名称
//    private Integer customerState;		// 1：贷后  2：预警 3： 逾期
//    private String createDate;		//
//    private String areaName;		//
//    private String industryTypeName;
//    private String reportId;
//    private String overdueId;
//    private String reportImages;
//    private String examinTime;


    private String id;                  //报告ID
    private String customerName;		// 客户名称
    private String customerManager;     //客户经理名称
    private String examinTime;          //检查日期
    private String msgCount;            //回复消息数
    private String reportImages;        //报告图片（以，分割的，可能为空，检查如果为空取下一个，到3个为止）

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerManager() {
        return customerManager;
    }

    public void setCustomerManager(String customerManager) {
        this.customerManager = customerManager;
    }

    public String getExaminTime() {
        return examinTime;
    }

    public void setExaminTime(String examinTime) {
        this.examinTime = examinTime;
    }

    public String getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }

    public String getReportImages() {
        return reportImages;
    }

    public void setReportImages(String reportImages) {
        this.reportImages = reportImages;
    }
}
