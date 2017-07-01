package com.fyjf.dao.entity;

/**
 * Created by ASUS on 2017/6/25.
 */
/*
* author: renweiwei
* datetime: 
*/
public class ReportCumtomerQuality {
    private String id;
    private String loanReportId;		// 贷后报告id
    private String communicationState;		// 与客户沟通渠道是否正常
    private String communicationRemark;		// 与客户沟通渠道备注
    private String badEvaluate;		// 客户的供货商或其他客户对授信客户产生负面评价
    private String badEvaluateRemark;		// 负面评价备注
    private String loadState;		// 是否在几个银行借款或不断在这些银行之间借新还旧
    private String loadStateRemark;		// 银行借款或不断在这些银行之间借新还旧备注
    private String customerInfoState;		// 客户是否及时提供真实的财务、税收或抵押担保信息
    private String customerInfoStateRemark;		// 客户及时提供真实的财务、税收或抵押担保信息备注
    private String repaymentState;		// 还款意愿
    private String repaymentRemark;		// 还款意愿备注
    private String cumtomerQualityImgs;		// cumtomer_quality_imgs
    private String reportCumtomerQualityOther;

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

    public String getCommunicationState() {
        return communicationState;
    }

    public void setCommunicationState(String communicationState) {
        this.communicationState = communicationState;
    }

    public String getCommunicationRemark() {
        return communicationRemark;
    }

    public void setCommunicationRemark(String communicationRemark) {
        this.communicationRemark = communicationRemark;
    }

    public String getBadEvaluate() {
        return badEvaluate;
    }

    public void setBadEvaluate(String badEvaluate) {
        this.badEvaluate = badEvaluate;
    }

    public String getBadEvaluateRemark() {
        return badEvaluateRemark;
    }

    public void setBadEvaluateRemark(String badEvaluateRemark) {
        this.badEvaluateRemark = badEvaluateRemark;
    }

    public String getLoadState() {
        return loadState;
    }

    public void setLoadState(String loadState) {
        this.loadState = loadState;
    }

    public String getLoadStateRemark() {
        return loadStateRemark;
    }

    public void setLoadStateRemark(String loadStateRemark) {
        this.loadStateRemark = loadStateRemark;
    }

    public String getCustomerInfoState() {
        return customerInfoState;
    }

    public void setCustomerInfoState(String customerInfoState) {
        this.customerInfoState = customerInfoState;
    }

    public String getCustomerInfoStateRemark() {
        return customerInfoStateRemark;
    }

    public void setCustomerInfoStateRemark(String customerInfoStateRemark) {
        this.customerInfoStateRemark = customerInfoStateRemark;
    }

    public String getRepaymentState() {
        return repaymentState;
    }

    public void setRepaymentState(String repaymentState) {
        this.repaymentState = repaymentState;
    }

    public String getRepaymentRemark() {
        return repaymentRemark;
    }

    public void setRepaymentRemark(String repaymentRemark) {
        this.repaymentRemark = repaymentRemark;
    }

    public String getCumtomerQualityImgs() {
        return cumtomerQualityImgs;
    }

    public void setCumtomerQualityImgs(String cumtomerQualityImgs) {
        this.cumtomerQualityImgs = cumtomerQualityImgs;
    }

    public String getReportCumtomerQualityOther() {
        return reportCumtomerQualityOther;
    }

    public void setReportCumtomerQualityOther(String reportCumtomerQualityOther) {
        this.reportCumtomerQualityOther = reportCumtomerQualityOther;
    }
}
