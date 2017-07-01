package com.fyjf.dao.entity;

/**
 * Created by ASUS on 2017/6/25.
 */
/*
* author: renweiwei
* datetime: 
*/
public class ReportCustomerFinancial {
    private String id;
    private String loanReportId;		// 贷后报告id

    private String customerAccountInBank;		// 本行结算账户账号
    private String moneyDuringCheck;		// 检查期间现金流入
    private String customerMoneyChangeState;		// 是否有异常变动情况
    private String customerMoneyReturnState;		// 是否达到归行比率
    private String customerAccountRemark;		// 备注

    private String customerFinanceType;		// 财务资料类型

    private String customerFinaceIncDecSaleCur;		// 损益情况 销售收入
    private String customerFinaceIncDecSalePre;		// 损益情况 销售收入
    private String customerFinaceIncDecProfitCur;		// 损益情况 利润总额
    private String customerFinaceIncDecProfitPre;		// 损益情况  利润总额

    private String customerFinaceIncDecConsumCur;		// 损益情况 主要能耗
    private String customerFinaceIncDecConsumPre;		// 损益情况  主要能耗
    private String customerFinaceAssetsAccountNeedCur;		// 资产情况  应收账款
    private String customerFinaceAssetsAccountNeedPre;		// 资产情况  应收账款

    private String customerFinaceAssetsInventoryCur;		// 资产情况 存货
    private String customerFinaceAssetsInventoryPre;		// 资产情况  存货
    private String customerFinaceDebtAccountNeedCur;		// 负债情况 应付账款
    private String customerFinaceDebtAccountNeedPre;		// 负债情况  应付账款

    private String customerFinaceDebtBorrowingCur;		// 负债情况 银行借款
    private String customerFinaceDebtBorrowingPre;		// 负债情况 银行借款
    private String customerFinaceDebtGuarantyCur;		// 负债情况  对外担保
    private String customerFinaceDebtGuarantyPre;		// 负债情况  对外担保

    private String customerFinaceRatioAssetsLiabilitiesCur;		// 比率情况 资产负债率
    private String customerFinaceRatioAssetsLiabilitiesPre;		// 比率情况  资产负债率
    private String customerFinaceRatioCurrentRatioCur;		// 比率情况 流动比率
    private String customerFinaceRatioCurrentRatioPre;		// 比率情况 流动比率

    private String customerFinaceRatioAccountTurnroundRateCur;		// 比率情况 应收帐款周转率
    private String customerFinaceRatioAccountTurnroundRatePre;		// 比率情况 应收帐款周转率
    private String customerFinaceRatioSaleProfitCur;		// 比率情况 销售利润率
    private String customerFinaceRatioSaleProfitPre;		// 比率情况 销售利润率

    private String customerFinaceRatioInventoryCur;		// 比率情况 存货周转率
    private String customerFinaceRatioInventoryPre;		// 比率情况 存货周转率

    private String customerFinaceForecastSale;		// 合同到期前财务状况预测  销售收入变化趋势
    private String customerFinaceForecastProfit;		// 合同到期前财务状况预测  利润变动趋势
    private String customerFinaceForecastCash;		// 合同到期前财务状况预测  现金流量变动趋势
    private String customerFinaceForecastBusinessCash;		// 合同到期前财务状况预测  经营活动现金流量变动趋势
    private String customerFinaceForecastRemark;		// 合同到期前财务状况预测 备注

    private String customerEffectFinanceWaring;		// 影响贷款偿还的因素分析  财务风险预警信号
    private String customerEffectPayMoney;		// 影响贷款偿还的因素分析  还本金能力
    private String customerEffectPayMoneyAmount;		// 影响贷款偿还的因素分析  预计还款金额
    private String customerEffectPayProfit;		// 影响贷款偿还的因素分析  还息能力
    private String customerEffectPayProfitRemark;		// customer_effect_pay_profit_remark
    private String customerFinancialImgs;		// customer_financial_imgs

    private String reportCustomerFinancialOther;

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

    public String getCustomerAccountInBank() {
        return customerAccountInBank;
    }

    public void setCustomerAccountInBank(String customerAccountInBank) {
        this.customerAccountInBank = customerAccountInBank;
    }

    public String getMoneyDuringCheck() {
        return moneyDuringCheck;
    }

    public void setMoneyDuringCheck(String moneyDuringCheck) {
        this.moneyDuringCheck = moneyDuringCheck;
    }

    public String getCustomerMoneyChangeState() {
        return customerMoneyChangeState;
    }

    public void setCustomerMoneyChangeState(String customerMoneyChangeState) {
        this.customerMoneyChangeState = customerMoneyChangeState;
    }

    public String getCustomerMoneyReturnState() {
        return customerMoneyReturnState;
    }

    public void setCustomerMoneyReturnState(String customerMoneyReturnState) {
        this.customerMoneyReturnState = customerMoneyReturnState;
    }

    public String getCustomerAccountRemark() {
        return customerAccountRemark;
    }

    public void setCustomerAccountRemark(String customerAccountRemark) {
        this.customerAccountRemark = customerAccountRemark;
    }

    public String getCustomerFinanceType() {
        return customerFinanceType;
    }

    public void setCustomerFinanceType(String customerFinanceType) {
        this.customerFinanceType = customerFinanceType;
    }

    public String getCustomerFinaceIncDecSaleCur() {
        return customerFinaceIncDecSaleCur;
    }

    public void setCustomerFinaceIncDecSaleCur(String customerFinaceIncDecSaleCur) {
        this.customerFinaceIncDecSaleCur = customerFinaceIncDecSaleCur;
    }

    public String getCustomerFinaceIncDecSalePre() {
        return customerFinaceIncDecSalePre;
    }

    public void setCustomerFinaceIncDecSalePre(String customerFinaceIncDecSalePre) {
        this.customerFinaceIncDecSalePre = customerFinaceIncDecSalePre;
    }

    public String getCustomerFinaceIncDecProfitCur() {
        return customerFinaceIncDecProfitCur;
    }

    public void setCustomerFinaceIncDecProfitCur(String customerFinaceIncDecProfitCur) {
        this.customerFinaceIncDecProfitCur = customerFinaceIncDecProfitCur;
    }

    public String getCustomerFinaceIncDecProfitPre() {
        return customerFinaceIncDecProfitPre;
    }

    public void setCustomerFinaceIncDecProfitPre(String customerFinaceIncDecProfitPre) {
        this.customerFinaceIncDecProfitPre = customerFinaceIncDecProfitPre;
    }

    public String getCustomerFinaceIncDecConsumCur() {
        return customerFinaceIncDecConsumCur;
    }

    public void setCustomerFinaceIncDecConsumCur(String customerFinaceIncDecConsumCur) {
        this.customerFinaceIncDecConsumCur = customerFinaceIncDecConsumCur;
    }

    public String getCustomerFinaceIncDecConsumPre() {
        return customerFinaceIncDecConsumPre;
    }

    public void setCustomerFinaceIncDecConsumPre(String customerFinaceIncDecConsumPre) {
        this.customerFinaceIncDecConsumPre = customerFinaceIncDecConsumPre;
    }

    public String getCustomerFinaceAssetsAccountNeedCur() {
        return customerFinaceAssetsAccountNeedCur;
    }

    public void setCustomerFinaceAssetsAccountNeedCur(String customerFinaceAssetsAccountNeedCur) {
        this.customerFinaceAssetsAccountNeedCur = customerFinaceAssetsAccountNeedCur;
    }

    public String getCustomerFinaceAssetsAccountNeedPre() {
        return customerFinaceAssetsAccountNeedPre;
    }

    public void setCustomerFinaceAssetsAccountNeedPre(String customerFinaceAssetsAccountNeedPre) {
        this.customerFinaceAssetsAccountNeedPre = customerFinaceAssetsAccountNeedPre;
    }

    public String getCustomerFinaceAssetsInventoryCur() {
        return customerFinaceAssetsInventoryCur;
    }

    public void setCustomerFinaceAssetsInventoryCur(String customerFinaceAssetsInventoryCur) {
        this.customerFinaceAssetsInventoryCur = customerFinaceAssetsInventoryCur;
    }

    public String getCustomerFinaceAssetsInventoryPre() {
        return customerFinaceAssetsInventoryPre;
    }

    public void setCustomerFinaceAssetsInventoryPre(String customerFinaceAssetsInventoryPre) {
        this.customerFinaceAssetsInventoryPre = customerFinaceAssetsInventoryPre;
    }

    public String getCustomerFinaceDebtAccountNeedCur() {
        return customerFinaceDebtAccountNeedCur;
    }

    public void setCustomerFinaceDebtAccountNeedCur(String customerFinaceDebtAccountNeedCur) {
        this.customerFinaceDebtAccountNeedCur = customerFinaceDebtAccountNeedCur;
    }

    public String getCustomerFinaceDebtAccountNeedPre() {
        return customerFinaceDebtAccountNeedPre;
    }

    public void setCustomerFinaceDebtAccountNeedPre(String customerFinaceDebtAccountNeedPre) {
        this.customerFinaceDebtAccountNeedPre = customerFinaceDebtAccountNeedPre;
    }

    public String getCustomerFinaceDebtBorrowingCur() {
        return customerFinaceDebtBorrowingCur;
    }

    public void setCustomerFinaceDebtBorrowingCur(String customerFinaceDebtBorrowingCur) {
        this.customerFinaceDebtBorrowingCur = customerFinaceDebtBorrowingCur;
    }

    public String getCustomerFinaceDebtBorrowingPre() {
        return customerFinaceDebtBorrowingPre;
    }

    public void setCustomerFinaceDebtBorrowingPre(String customerFinaceDebtBorrowingPre) {
        this.customerFinaceDebtBorrowingPre = customerFinaceDebtBorrowingPre;
    }

    public String getCustomerFinaceDebtGuarantyCur() {
        return customerFinaceDebtGuarantyCur;
    }

    public void setCustomerFinaceDebtGuarantyCur(String customerFinaceDebtGuarantyCur) {
        this.customerFinaceDebtGuarantyCur = customerFinaceDebtGuarantyCur;
    }

    public String getCustomerFinaceDebtGuarantyPre() {
        return customerFinaceDebtGuarantyPre;
    }

    public void setCustomerFinaceDebtGuarantyPre(String customerFinaceDebtGuarantyPre) {
        this.customerFinaceDebtGuarantyPre = customerFinaceDebtGuarantyPre;
    }

    public String getCustomerFinaceRatioAssetsLiabilitiesCur() {
        return customerFinaceRatioAssetsLiabilitiesCur;
    }

    public void setCustomerFinaceRatioAssetsLiabilitiesCur(String customerFinaceRatioAssetsLiabilitiesCur) {
        this.customerFinaceRatioAssetsLiabilitiesCur = customerFinaceRatioAssetsLiabilitiesCur;
    }

    public String getCustomerFinaceRatioAssetsLiabilitiesPre() {
        return customerFinaceRatioAssetsLiabilitiesPre;
    }

    public void setCustomerFinaceRatioAssetsLiabilitiesPre(String customerFinaceRatioAssetsLiabilitiesPre) {
        this.customerFinaceRatioAssetsLiabilitiesPre = customerFinaceRatioAssetsLiabilitiesPre;
    }

    public String getCustomerFinaceRatioCurrentRatioCur() {
        return customerFinaceRatioCurrentRatioCur;
    }

    public void setCustomerFinaceRatioCurrentRatioCur(String customerFinaceRatioCurrentRatioCur) {
        this.customerFinaceRatioCurrentRatioCur = customerFinaceRatioCurrentRatioCur;
    }

    public String getCustomerFinaceRatioCurrentRatioPre() {
        return customerFinaceRatioCurrentRatioPre;
    }

    public void setCustomerFinaceRatioCurrentRatioPre(String customerFinaceRatioCurrentRatioPre) {
        this.customerFinaceRatioCurrentRatioPre = customerFinaceRatioCurrentRatioPre;
    }

    public String getCustomerFinaceRatioAccountTurnroundRateCur() {
        return customerFinaceRatioAccountTurnroundRateCur;
    }

    public void setCustomerFinaceRatioAccountTurnroundRateCur(String customerFinaceRatioAccountTurnroundRateCur) {
        this.customerFinaceRatioAccountTurnroundRateCur = customerFinaceRatioAccountTurnroundRateCur;
    }

    public String getCustomerFinaceRatioAccountTurnroundRatePre() {
        return customerFinaceRatioAccountTurnroundRatePre;
    }

    public void setCustomerFinaceRatioAccountTurnroundRatePre(String customerFinaceRatioAccountTurnroundRatePre) {
        this.customerFinaceRatioAccountTurnroundRatePre = customerFinaceRatioAccountTurnroundRatePre;
    }

    public String getCustomerFinaceRatioSaleProfitCur() {
        return customerFinaceRatioSaleProfitCur;
    }

    public void setCustomerFinaceRatioSaleProfitCur(String customerFinaceRatioSaleProfitCur) {
        this.customerFinaceRatioSaleProfitCur = customerFinaceRatioSaleProfitCur;
    }

    public String getCustomerFinaceRatioSaleProfitPre() {
        return customerFinaceRatioSaleProfitPre;
    }

    public void setCustomerFinaceRatioSaleProfitPre(String customerFinaceRatioSaleProfitPre) {
        this.customerFinaceRatioSaleProfitPre = customerFinaceRatioSaleProfitPre;
    }

    public String getCustomerFinaceRatioInventoryCur() {
        return customerFinaceRatioInventoryCur;
    }

    public void setCustomerFinaceRatioInventoryCur(String customerFinaceRatioInventoryCur) {
        this.customerFinaceRatioInventoryCur = customerFinaceRatioInventoryCur;
    }

    public String getCustomerFinaceRatioInventoryPre() {
        return customerFinaceRatioInventoryPre;
    }

    public void setCustomerFinaceRatioInventoryPre(String customerFinaceRatioInventoryPre) {
        this.customerFinaceRatioInventoryPre = customerFinaceRatioInventoryPre;
    }

    public String getCustomerFinaceForecastSale() {
        return customerFinaceForecastSale;
    }

    public void setCustomerFinaceForecastSale(String customerFinaceForecastSale) {
        this.customerFinaceForecastSale = customerFinaceForecastSale;
    }

    public String getCustomerFinaceForecastProfit() {
        return customerFinaceForecastProfit;
    }

    public void setCustomerFinaceForecastProfit(String customerFinaceForecastProfit) {
        this.customerFinaceForecastProfit = customerFinaceForecastProfit;
    }

    public String getCustomerFinaceForecastCash() {
        return customerFinaceForecastCash;
    }

    public void setCustomerFinaceForecastCash(String customerFinaceForecastCash) {
        this.customerFinaceForecastCash = customerFinaceForecastCash;
    }

    public String getCustomerFinaceForecastBusinessCash() {
        return customerFinaceForecastBusinessCash;
    }

    public void setCustomerFinaceForecastBusinessCash(String customerFinaceForecastBusinessCash) {
        this.customerFinaceForecastBusinessCash = customerFinaceForecastBusinessCash;
    }

    public String getCustomerFinaceForecastRemark() {
        return customerFinaceForecastRemark;
    }

    public void setCustomerFinaceForecastRemark(String customerFinaceForecastRemark) {
        this.customerFinaceForecastRemark = customerFinaceForecastRemark;
    }

    public String getCustomerEffectFinanceWaring() {
        return customerEffectFinanceWaring;
    }

    public void setCustomerEffectFinanceWaring(String customerEffectFinanceWaring) {
        this.customerEffectFinanceWaring = customerEffectFinanceWaring;
    }

    public String getCustomerEffectPayMoney() {
        return customerEffectPayMoney;
    }

    public void setCustomerEffectPayMoney(String customerEffectPayMoney) {
        this.customerEffectPayMoney = customerEffectPayMoney;
    }

    public String getCustomerEffectPayMoneyAmount() {
        return customerEffectPayMoneyAmount;
    }

    public void setCustomerEffectPayMoneyAmount(String customerEffectPayMoneyAmount) {
        this.customerEffectPayMoneyAmount = customerEffectPayMoneyAmount;
    }

    public String getCustomerEffectPayProfit() {
        return customerEffectPayProfit;
    }

    public void setCustomerEffectPayProfit(String customerEffectPayProfit) {
        this.customerEffectPayProfit = customerEffectPayProfit;
    }

    public String getCustomerEffectPayProfitRemark() {
        return customerEffectPayProfitRemark;
    }

    public void setCustomerEffectPayProfitRemark(String customerEffectPayProfitRemark) {
        this.customerEffectPayProfitRemark = customerEffectPayProfitRemark;
    }

    public String getCustomerFinancialImgs() {
        return customerFinancialImgs;
    }

    public void setCustomerFinancialImgs(String customerFinancialImgs) {
        this.customerFinancialImgs = customerFinancialImgs;
    }

    public String getReportCustomerFinancialOther() {
        return reportCustomerFinancialOther;
    }

    public void setReportCustomerFinancialOther(String reportCustomerFinancialOther) {
        this.reportCustomerFinancialOther = reportCustomerFinancialOther;
    }
}
