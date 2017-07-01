package com.fyjf.dao.entity;

/**
 * Created by ASUS on 2017/6/25.
 */
/*
* author: renweiwei
* datetime: 
*/
public class ReportFinance {
    private String id;
    private String loanReportId;		// 贷后报告id
    private String financePurpose;		// 融资批准用途
    private String financePurposeActual;		// 融资实际用途
    private String usedByAllowed;		// 是否按规定用途使用
    private String moneyInPlace;		// 客户配套资金是否到位
    private String repaymentState;		// 还款记录是否正常
    private String aplayDeferred;		// 是否不断申请延期支付或申请实施新的授信
    private String debtState;		// 短期债务是否超常增加
    private String repaymentStateRemark;		// repayment_state_remark
    private String otherFinaceCreditAmount;		// 其他金融机构授信金额
    private String otherFinaceLoan;		// 贷款
    private String otherFinaceOtherAmount;		// 其他信贷品种及金额
    private String loanHasBadRecordState;		// 有无不良贷款或不良记录
    private String loanHasBadRecordAmount;		// 不良贷款或不良记录金额
    private String outerGuaranteeLoanAmount;		// 对外担保总额
    private String outerGuaranteeLoan;		// 对外担保总额中保证担保
    private String outerGuaranteeLoanMortgage;		// 对外担保总额中抵质押担保
    private String financeImgs;		// finance_imgs
    private String reportFinanceOther;		// reportFinanceOther

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

    public String getFinancePurpose() {
        return financePurpose;
    }

    public void setFinancePurpose(String financePurpose) {
        this.financePurpose = financePurpose;
    }

    public String getFinancePurposeActual() {
        return financePurposeActual;
    }

    public void setFinancePurposeActual(String financePurposeActual) {
        this.financePurposeActual = financePurposeActual;
    }

    public String getUsedByAllowed() {
        return usedByAllowed;
    }

    public void setUsedByAllowed(String usedByAllowed) {
        this.usedByAllowed = usedByAllowed;
    }

    public String getMoneyInPlace() {
        return moneyInPlace;
    }

    public void setMoneyInPlace(String moneyInPlace) {
        this.moneyInPlace = moneyInPlace;
    }

    public String getRepaymentState() {
        return repaymentState;
    }

    public void setRepaymentState(String repaymentState) {
        this.repaymentState = repaymentState;
    }

    public String getAplayDeferred() {
        return aplayDeferred;
    }

    public void setAplayDeferred(String aplayDeferred) {
        this.aplayDeferred = aplayDeferred;
    }

    public String getDebtState() {
        return debtState;
    }

    public void setDebtState(String debtState) {
        this.debtState = debtState;
    }

    public String getRepaymentStateRemark() {
        return repaymentStateRemark;
    }

    public void setRepaymentStateRemark(String repaymentStateRemark) {
        this.repaymentStateRemark = repaymentStateRemark;
    }

    public String getOtherFinaceCreditAmount() {
        return otherFinaceCreditAmount;
    }

    public void setOtherFinaceCreditAmount(String otherFinaceCreditAmount) {
        this.otherFinaceCreditAmount = otherFinaceCreditAmount;
    }

    public String getOtherFinaceLoan() {
        return otherFinaceLoan;
    }

    public void setOtherFinaceLoan(String otherFinaceLoan) {
        this.otherFinaceLoan = otherFinaceLoan;
    }

    public String getOtherFinaceOtherAmount() {
        return otherFinaceOtherAmount;
    }

    public void setOtherFinaceOtherAmount(String otherFinaceOtherAmount) {
        this.otherFinaceOtherAmount = otherFinaceOtherAmount;
    }

    public String getLoanHasBadRecordState() {
        return loanHasBadRecordState;
    }

    public void setLoanHasBadRecordState(String loanHasBadRecordState) {
        this.loanHasBadRecordState = loanHasBadRecordState;
    }

    public String getLoanHasBadRecordAmount() {
        return loanHasBadRecordAmount;
    }

    public void setLoanHasBadRecordAmount(String loanHasBadRecordAmount) {
        this.loanHasBadRecordAmount = loanHasBadRecordAmount;
    }

    public String getOuterGuaranteeLoanAmount() {
        return outerGuaranteeLoanAmount;
    }

    public void setOuterGuaranteeLoanAmount(String outerGuaranteeLoanAmount) {
        this.outerGuaranteeLoanAmount = outerGuaranteeLoanAmount;
    }

    public String getOuterGuaranteeLoan() {
        return outerGuaranteeLoan;
    }

    public void setOuterGuaranteeLoan(String outerGuaranteeLoan) {
        this.outerGuaranteeLoan = outerGuaranteeLoan;
    }

    public String getOuterGuaranteeLoanMortgage() {
        return outerGuaranteeLoanMortgage;
    }

    public void setOuterGuaranteeLoanMortgage(String outerGuaranteeLoanMortgage) {
        this.outerGuaranteeLoanMortgage = outerGuaranteeLoanMortgage;
    }

    public String getFinanceImgs() {
        return financeImgs;
    }

    public void setFinanceImgs(String financeImgs) {
        this.financeImgs = financeImgs;
    }

    public String getReportFinanceOther() {
        return reportFinanceOther;
    }

    public void setReportFinanceOther(String reportFinanceOther) {
        this.reportFinanceOther = reportFinanceOther;
    }
}
