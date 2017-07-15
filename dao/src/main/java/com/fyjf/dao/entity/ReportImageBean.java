package com.fyjf.dao.entity;

/**
 * Created by czf on 2017/7/16.
 */

public class ReportImageBean {

    /**
     * customerFinancialImgs :
     * reportId : f09754b2b8f34b18baef74a966a2fd5f
     * businessManageImgs : ab8fc398-5256-4428-8a7a-d20e10ddd110.png,ab8fc398-5256-4428-8a7a-d20e10ddd110.png
     * cumtomerQualityImgs : ab8fc398-5256-4428-8a7a-d20e10ddd110.png,ab8fc398-5256-4428-8a7a-d20e10ddd110.png
     * guaranteeImgs : d06e29a9-3f5b-4522-8051-b41bcfcf17b5.png
     * financeImgs : 5e1eeb60-3d7b-4385-93e6-39b661d0d0cd.png,5e1eeb60-3d7b-4385-93e6-39b661d0d0cd.png
     */

    private String customerFinancialImgs;
    private String reportId;
    private String businessManageImgs;
    private String cumtomerQualityImgs;
    private String guaranteeImgs;
    private String financeImgs;

    public String getCustomerFinancialImgs() {
        return customerFinancialImgs;
    }

    public void setCustomerFinancialImgs(String customerFinancialImgs) {
        this.customerFinancialImgs = customerFinancialImgs;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getBusinessManageImgs() {
        return businessManageImgs;
    }

    public void setBusinessManageImgs(String businessManageImgs) {
        this.businessManageImgs = businessManageImgs;
    }

    public String getCumtomerQualityImgs() {
        return cumtomerQualityImgs;
    }

    public void setCumtomerQualityImgs(String cumtomerQualityImgs) {
        this.cumtomerQualityImgs = cumtomerQualityImgs;
    }

    public String getGuaranteeImgs() {
        return guaranteeImgs;
    }

    public void setGuaranteeImgs(String guaranteeImgs) {
        this.guaranteeImgs = guaranteeImgs;
    }

    public String getFinanceImgs() {
        return financeImgs;
    }

    public void setFinanceImgs(String financeImgs) {
        this.financeImgs = financeImgs;
    }
}
