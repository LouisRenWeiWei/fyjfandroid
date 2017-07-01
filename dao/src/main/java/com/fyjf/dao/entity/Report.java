package com.fyjf.dao.entity;

/**
 * Created by ASUS on 2017/6/25.
 */
/*
* author: renweiwei
* datetime: 
*/
public class Report {
    private String id;
    private String customerId;		// customer_id
    private String year;		// 年度
    private String reportNo;		// 年度第几次检查
    private String reportCount;		// 年度总共多少次检查
    private User examiner;		// 检查人
    private String examinTime;		// 检查日期
    private String riskWarning;		// 是否发起风险预警
    private String riskWarningReason;		// 风险预警原因
    private String proposedMeasures;		// 建议采取的措施
    private String supervisor;		// 贷后监管员签名
    private String businessDirectorAdvice;		// 业务主管意见
    private String businessDirector;		// 业务主管签名
    private String bankCreditReport;		// 银行信证pdf
    private String socialCreditReport;		// 社会信证pdf

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo;
    }

    public String getReportCount() {
        return reportCount;
    }

    public void setReportCount(String reportCount) {
        this.reportCount = reportCount;
    }

    public User getExaminer() {
        return examiner;
    }

    public void setExaminer(User examiner) {
        this.examiner = examiner;
    }

    public String getExaminTime() {
        return examinTime;
    }

    public void setExaminTime(String examinTime) {
        this.examinTime = examinTime;
    }

    public String getRiskWarning() {
        return riskWarning;
    }

    public void setRiskWarning(String riskWarning) {
        this.riskWarning = riskWarning;
    }

    public String getRiskWarningReason() {
        return riskWarningReason;
    }

    public void setRiskWarningReason(String riskWarningReason) {
        this.riskWarningReason = riskWarningReason;
    }

    public String getProposedMeasures() {
        return proposedMeasures;
    }

    public void setProposedMeasures(String proposedMeasures) {
        this.proposedMeasures = proposedMeasures;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getBusinessDirectorAdvice() {
        return businessDirectorAdvice;
    }

    public void setBusinessDirectorAdvice(String businessDirectorAdvice) {
        this.businessDirectorAdvice = businessDirectorAdvice;
    }

    public String getBusinessDirector() {
        return businessDirector;
    }

    public void setBusinessDirector(String businessDirector) {
        this.businessDirector = businessDirector;
    }

    public String getBankCreditReport() {
        return bankCreditReport;
    }

    public void setBankCreditReport(String bankCreditReport) {
        this.bankCreditReport = bankCreditReport;
    }

    public String getSocialCreditReport() {
        return socialCreditReport;
    }

    public void setSocialCreditReport(String socialCreditReport) {
        this.socialCreditReport = socialCreditReport;
    }
}
