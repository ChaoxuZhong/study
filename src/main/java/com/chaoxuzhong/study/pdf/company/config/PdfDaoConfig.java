package com.chaoxuzhong.study.pdf.company.config;

import com.chaoxuzhong.study.pdf.company.pojo.FieldTypeEnums;
import lombok.Getter;

/**
 * PDF域（数据库来源的）
 */
// 初始化好以后不可变，不提供set，用作模板配置
@Getter
public class PdfDaoConfig {
    /**
     * dao层返回的field名称，
     */
    private String daoFieldName;

    /**
     * 填充类型 FieldType
     */
    private FieldTypeEnums type;


    /**
     * type == FieldType.CHECKBOX 时，复选框是否默认勾选
     * true 默认勾选
     * false 不勾选
     */
    private Boolean checkBoxDefault;

    // 暂时无无参构造，至少指定daoFieldName 和 type
    public PdfDaoConfig(FieldTypeEnums type, String daoFieldName) {
        this.daoFieldName = daoFieldName;
        this.type = type;
        this.checkBoxDefault = false;
    }

    public PdfDaoConfig(FieldTypeEnums type, Boolean checkBoxDefault) {
        if (FieldTypeEnums.CHECKBOX != type) {
            throw new IllegalStateException("only checkbox use this Constructor");
        }
        this.type = type;
        this.checkBoxDefault = checkBoxDefault;
    }

}
