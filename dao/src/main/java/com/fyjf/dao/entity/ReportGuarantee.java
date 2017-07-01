package com.fyjf.dao.entity;

/**
 * Created by ASUS on 2017/6/25.
 */
/*
* author: renweiwei
* datetime: 
*/
public class ReportGuarantee {
    private String id;
    private String loanReportId;		// 贷后报告id
    private String guaranteeType;		// 担保方式
    private String guaranteeOther;		// 担保方式--其他
    private String collateralType;		// 抵（质）押品种类
    private String collateralPerson;		// 抵（质）押人名称
    private String guaranteeState;		// 抵（质）押品状态

    private String collateralValue;		// 抵质押品价值
    private String collateralRate;		// 抵质押率
    private String collateralCurrentValue;		// 抵（质）押品目前价值
    private String guaranteeRemark;		// 担保情况 备注
    private String guaranteeImgs;		// 上传的照片，以，分割存储
    private String reportGuaranteeOther;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoanReportId() {
        return loanReportId;
    }

    public void setLoanReportId(String loanReportId) {
        this.loanReportId = loanReportId;
    }

    public String getGuaranteeType() {
        return guaranteeType;
    }

    public void setGuaranteeType(String guaranteeType) {
        this.guaranteeType = guaranteeType;
    }

    public String getGuaranteeOther() {
        return guaranteeOther;
    }

    public void setGuaranteeOther(String guaranteeOther) {
        this.guaranteeOther = guaranteeOther;
    }

    public String getCollateralType() {
        return collateralType;
    }

    public void setCollateralType(String collateralType) {
        this.collateralType = collateralType;
    }

    public String getCollateralPerson() {
        return collateralPerson;
    }

    public void setCollateralPerson(String collateralPerson) {
        this.collateralPerson = collateralPerson;
    }

    public String getGuaranteeState() {
        return guaranteeState;
    }

    public void setGuaranteeState(String guaranteeState) {
        this.guaranteeState = guaranteeState;
    }

    public String getCollateralValue() {
        return collateralValue;
    }

    public void setCollateralValue(String collateralValue) {
        this.collateralValue = collateralValue;
    }

    public String getCollateralRate() {
        return collateralRate;
    }

    public void setCollateralRate(String collateralRate) {
        this.collateralRate = collateralRate;
    }

    public String getCollateralCurrentValue() {
        return collateralCurrentValue;
    }

    public void setCollateralCurrentValue(String collateralCurrentValue) {
        this.collateralCurrentValue = collateralCurrentValue;
    }

    public String getGuaranteeRemark() {
        return guaranteeRemark;
    }

    public void setGuaranteeRemark(String guaranteeRemark) {
        this.guaranteeRemark = guaranteeRemark;
    }

    public String getGuaranteeImgs() {
        return guaranteeImgs;
    }

    public void setGuaranteeImgs(String guaranteeImgs) {
        this.guaranteeImgs = guaranteeImgs;
    }

    public String getReportGuaranteeOther() {
        return reportGuaranteeOther;
    }

    public void setReportGuaranteeOther(String reportGuaranteeOther) {
        this.reportGuaranteeOther = reportGuaranteeOther;
    }
}
