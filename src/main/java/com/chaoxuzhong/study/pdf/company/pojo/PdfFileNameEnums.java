package com.chaoxuzhong.study.pdf.company.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PdfFileNameEnums {

    ADEN_ACOPENING_FORM_CORPORATE("Aden AC opening form Corporate 20210705", "开户基础信息表格"),
    FW8BENE("fw8bene", "在美国课税与申报目的下之最终受益人身分证明(法人)"),
    ADEN_FINANCIAL_AML_LETTER("Aden Financial - AML Letter", "反洗钱确认书"),
    AML_QUESTIONNAIRE("AML Questionnaire", "反洗钱调查表"),
    PROFESSIONAL_INVESTOR_DELCARATION_FORM("PROFESSIONAL INVESTOR DELCARATION FORM", "专业投资者评估表"),
    SELF_CERTIFICATION_ENTITY_FORM("Self-Certification Form ENTITY", "自我证明表格-实体"),
    SELF_CERTIFICATION_CONTROLLER_FORM("Self-Certification Form CONTROLLER", "自我证明表格-控权人"),
    IDSA("ISDA", "ISDA协议"),
    A45260787_V4_ADENFIN_IMA("A45260787 V4.0 ADENFIN_IMA", "投资管理协议"),
    A45399227_V3_ADENFIN_RISK_DISCLOSURE_STATEMENT("A45399227 v3.0 Adenfin_Risk Disclosure Statement", "风险披露申明");

    private String enVoName;
    private String chVoName;


}
