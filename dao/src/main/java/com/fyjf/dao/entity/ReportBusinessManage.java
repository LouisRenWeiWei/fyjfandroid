package com.fyjf.dao.entity;

/**
 * Created by ASUS on 2017/6/25.
 */
/*
* author: renweiwei
* datetime: 
*/
public class ReportBusinessManage {
    private String id;
    private String loanReportId;		// 贷后报告id
    private String mainBusinessChange;		// 主营业务是否发生变化
    private String businessState;		// 生产经营是否正常
    private String enterpriseTrend;		// 企业发展趋势
    private String mainBusinessRemark;		// 主营业务备注
    private String orginaltionChange;		// 管理层是否发生重大变化
    private String orgChangeEffect;		// 管理层重大变化影响情况
    private String orgChangeRemark;		// 管理层重大变化备注
    private String shareholdersChange;		// 主要股东、关联公司与母子公司是否发生重大变化
    private String shareholdersChangeEffect;		// 主要股东、关联公司与母子公司生重大变化影响情况
    private String shareholdersChangeRemark;		// 主要股东、关联公司与母子公司重大变化备注
    private String customerManage;		// 客户内部管理情况
    private String customerManageRemark;		// 客户内部管理情况备注
    private String arbitrationState;		// 是否发生重大诉讼或仲裁、重大事故或重大赔偿
    private String arbitrationStateEffect;		// 是否发生重大诉讼或仲裁、重大事故或重大赔偿影响情况
    private String arbitrationStateRemark;		// 是否发生重大诉讼或仲裁、重大事故或重大赔偿备注
    private String outerManage;		// 外部经营环境是否发生不利经营的重大变化
    private String outerManageMeasures;		// 对竞争变化或外部条件变化是否有应对措施
    private String outerManageRemark;		// 外部经营环境是否发生不利经营的重大变化备注
    private String partnerState;		// 主要合作伙伴是否减少或流失
    private String partnerObtainGoal;		// 期目标实现
    private String partnerStateRemark;		// 主要合作伙伴是否减少或流失备注
    private String badEffectState;		// 是否存在影响该笔贷款偿还的不利因素
    private String badEffectRemark;		// 是否存在影响该笔贷款偿还的不利因素备注
    private String businessManageImgs;		// business_manage_imgs
    private String reportBusinessManageOther;

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

    public String getMainBusinessChange() {
        return mainBusinessChange;
    }

    public void setMainBusinessChange(String mainBusinessChange) {
        this.mainBusinessChange = mainBusinessChange;
    }

    public String getBusinessState() {
        return businessState;
    }

    public void setBusinessState(String businessState) {
        this.businessState = businessState;
    }

    public String getEnterpriseTrend() {
        return enterpriseTrend;
    }

    public void setEnterpriseTrend(String enterpriseTrend) {
        this.enterpriseTrend = enterpriseTrend;
    }

    public String getMainBusinessRemark() {
        return mainBusinessRemark;
    }

    public void setMainBusinessRemark(String mainBusinessRemark) {
        this.mainBusinessRemark = mainBusinessRemark;
    }

    public String getOrginaltionChange() {
        return orginaltionChange;
    }

    public void setOrginaltionChange(String orginaltionChange) {
        this.orginaltionChange = orginaltionChange;
    }

    public String getOrgChangeEffect() {
        return orgChangeEffect;
    }

    public void setOrgChangeEffect(String orgChangeEffect) {
        this.orgChangeEffect = orgChangeEffect;
    }

    public String getOrgChangeRemark() {
        return orgChangeRemark;
    }

    public void setOrgChangeRemark(String orgChangeRemark) {
        this.orgChangeRemark = orgChangeRemark;
    }

    public String getShareholdersChange() {
        return shareholdersChange;
    }

    public void setShareholdersChange(String shareholdersChange) {
        this.shareholdersChange = shareholdersChange;
    }

    public String getShareholdersChangeEffect() {
        return shareholdersChangeEffect;
    }

    public void setShareholdersChangeEffect(String shareholdersChangeEffect) {
        this.shareholdersChangeEffect = shareholdersChangeEffect;
    }

    public String getShareholdersChangeRemark() {
        return shareholdersChangeRemark;
    }

    public void setShareholdersChangeRemark(String shareholdersChangeRemark) {
        this.shareholdersChangeRemark = shareholdersChangeRemark;
    }

    public String getCustomerManage() {
        return customerManage;
    }

    public void setCustomerManage(String customerManage) {
        this.customerManage = customerManage;
    }

    public String getCustomerManageRemark() {
        return customerManageRemark;
    }

    public void setCustomerManageRemark(String customerManageRemark) {
        this.customerManageRemark = customerManageRemark;
    }

    public String getArbitrationState() {
        return arbitrationState;
    }

    public void setArbitrationState(String arbitrationState) {
        this.arbitrationState = arbitrationState;
    }

    public String getArbitrationStateEffect() {
        return arbitrationStateEffect;
    }

    public void setArbitrationStateEffect(String arbitrationStateEffect) {
        this.arbitrationStateEffect = arbitrationStateEffect;
    }

    public String getArbitrationStateRemark() {
        return arbitrationStateRemark;
    }

    public void setArbitrationStateRemark(String arbitrationStateRemark) {
        this.arbitrationStateRemark = arbitrationStateRemark;
    }

    public String getOuterManage() {
        return outerManage;
    }

    public void setOuterManage(String outerManage) {
        this.outerManage = outerManage;
    }

    public String getOuterManageMeasures() {
        return outerManageMeasures;
    }

    public void setOuterManageMeasures(String outerManageMeasures) {
        this.outerManageMeasures = outerManageMeasures;
    }

    public String getOuterManageRemark() {
        return outerManageRemark;
    }

    public void setOuterManageRemark(String outerManageRemark) {
        this.outerManageRemark = outerManageRemark;
    }

    public String getPartnerState() {
        return partnerState;
    }

    public void setPartnerState(String partnerState) {
        this.partnerState = partnerState;
    }

    public String getPartnerObtainGoal() {
        return partnerObtainGoal;
    }

    public void setPartnerObtainGoal(String partnerObtainGoal) {
        this.partnerObtainGoal = partnerObtainGoal;
    }

    public String getPartnerStateRemark() {
        return partnerStateRemark;
    }

    public void setPartnerStateRemark(String partnerStateRemark) {
        this.partnerStateRemark = partnerStateRemark;
    }

    public String getBadEffectState() {
        return badEffectState;
    }

    public void setBadEffectState(String badEffectState) {
        this.badEffectState = badEffectState;
    }

    public String getBadEffectRemark() {
        return badEffectRemark;
    }

    public void setBadEffectRemark(String badEffectRemark) {
        this.badEffectRemark = badEffectRemark;
    }

    public String getBusinessManageImgs() {
        return businessManageImgs;
    }

    public void setBusinessManageImgs(String businessManageImgs) {
        this.businessManageImgs = businessManageImgs;
    }

    public String getReportBusinessManageOther() {
        return reportBusinessManageOther;
    }

    public void setReportBusinessManageOther(String reportBusinessManageOther) {
        this.reportBusinessManageOther = reportBusinessManageOther;
    }
}
