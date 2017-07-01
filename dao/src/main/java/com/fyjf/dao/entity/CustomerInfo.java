package com.fyjf.dao.entity;

/**
 * Created by ASUS on 2017/6/28.
 */
/*
* author: renweiwei
* datetime: 
*/
public class CustomerInfo {
    private String id;
    private String name;		// 客户名称
    private Integer customerState;		// 1：贷后  2：预警 3： 逾期
    private String createDate;		//
    private String areaName;		//
    private String industryTypeName;
    private String reportId;
    private String overdueId;
    private String reportImages;
    private String examinTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCustomerState() {
        return customerState;
    }

    public void setCustomerState(Integer customerState) {
        this.customerState = customerState;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getIndustryTypeName() {
        return industryTypeName;
    }

    public void setIndustryTypeName(String industryTypeName) {
        this.industryTypeName = industryTypeName;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getOverdueId() {
        return overdueId;
    }

    public void setOverdueId(String overdueId) {
        this.overdueId = overdueId;
    }

    public String getReportImages() {
        return reportImages;
    }

    public void setReportImages(String reportImages) {
        this.reportImages = reportImages;
    }

    public String getExaminTime() {
        return examinTime;
    }

    public void setExaminTime(String examinTime) {
        this.examinTime = examinTime;
    }
}
