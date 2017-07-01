package com.fyjf.dao.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

/**
 * Created by ASUS on 2017/5/23.
 */
public class Customer implements Serializable{
    private static final long serialVersionUID = 333333333333L;
    @Id
    private String id;
    private String name;		// 客户名称
    private String creditCode;		// 信用代码
    private Integer customerState;//1：贷后  2：预警 3： 逾期
    private String address;		// 经营地址
    private String telphone;		// 联系电话
    private String manager;		// 实际控制人
    private IndustryType industryType;//industryTypeId;		// 行业类型
    private String mainBusiness;		// 主营业务
    private User managerByUs;		// 我方负责人员
    private String bankId;		// 银行
    //	private String bankOfficeId;		// 银行部门
    private Office bankOffice;
    private User bankOfficeWorker;		// 银行员工

    private Report report;

    private OverdueReport overdueReport;//逾期报告

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

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public Integer getCustomerState() {
        return customerState;
    }

    public void setCustomerState(Integer customerState) {
        this.customerState = customerState;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public IndustryType getIndustryType() {
        return industryType;
    }

    public void setIndustryType(IndustryType industryType) {
        this.industryType = industryType;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public User getManagerByUs() {
        return managerByUs;
    }

    public void setManagerByUs(User managerByUs) {
        this.managerByUs = managerByUs;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public Office getBankOffice() {
        return bankOffice;
    }

    public void setBankOffice(Office bankOffice) {
        this.bankOffice = bankOffice;
    }

    public User getBankOfficeWorker() {
        return bankOfficeWorker;
    }

    public void setBankOfficeWorker(User bankOfficeWorker) {
        this.bankOfficeWorker = bankOfficeWorker;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public OverdueReport getOverdueReport() {
        return overdueReport;
    }

    public void setOverdueReport(OverdueReport overdueReport) {
        this.overdueReport = overdueReport;
    }
}
