package com.chaoxuzhong.study.pdf.company.config;

import cn.hutool.core.map.MapBuilder;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoDbNameConstant;
import com.chaoxuzhong.study.pdf.company.pojo.FieldTypeEnums;
import com.chaoxuzhong.study.pdf.company.service.PdfTemplateNameConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;


@Slf4j
public class PdfCompanyFieldNameDaoConfig {

    private static final String COLON = ":";
    private static final FieldTypeEnums CBOX = FieldTypeEnums.CHECKBOX;
    private static final FieldTypeEnums SIGN = FieldTypeEnums.BUTTON;
    private static final FieldTypeEnums TEXT = FieldTypeEnums.TEXT;

    /**
     * 所有文件的数据库层配置 key为文件模板名称，value为对应文件模板map
     * 文件模板map key为pdf域名，value PdfDaoConfig配置了pdf从dao层取值的字段及PDF中展示方式
     */
    public static final HashMap<String, HashMap<String, PdfDaoConfig>> ALL_CONFIG = new HashMap<>();


    static {
        HashMap<String, PdfDaoConfig> CUST_INFO_01 = new HashMap<>();
        new MapBuilder<>(CUST_INFO_01)
                .put("memberNo", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INF0, "memberNo"))
                .put("accountType01", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "accountType"))
                .put("accountType02", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "accountType"))
                .put("accountType03", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "accountType"))
                .put("accountService01", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "accountService"))
                .put("accountService02", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "accountService"))
                .put("realName", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INF0, "realName"))
                .put("surName", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "reasurNamelName"))
                .put("business", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "business"))
                .put("businessAddress", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "businessAddress"))
                .put("address", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "address"))
                .put("companyAddress",dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "companyAddress"))
                .build();

        ALL_CONFIG.put(PdfTemplateNameConstant.ADEN_AC_OPENING_FORM_CORPORATE_01, CUST_INFO_01);
    }

    static {
        HashMap<String, PdfDaoConfig> ADEN_AC_OPENING_FORM_CORPORATE_02_C0NFIG = new HashMap<>();
//        ADEN_AC_OPENING_FORM_CORPORATE_02_C0NFIG.put("memberNo", new PdfDaoConfig(FieldTypeEnums.TEXT, PdfDaoDbNameConstant.MEMBER_INF0+COLON+"memberNo"));
        ALL_CONFIG.put(PdfTemplateNameConstant.ADEN_AC_OPENING_FORM_CORPORATE_02, ADEN_AC_OPENING_FORM_CORPORATE_02_C0NFIG);
    }

    /**
     * 获取配置文件
     *
     * @param templateFileName 文件模板名称
     * @return
     */
    public static HashMap<String, PdfDaoConfig> getConfig(String templateFileName) {
        HashMap<String, PdfDaoConfig> result = ALL_CONFIG.get(templateFileName);
        if (result == null) {
            String error = String.format("ERROR ==> %s:pdf文件未配置 FieldNameDaoConfig", templateFileName);
            log.error(error);
            throw new NullPointerException(error);
        }
        return result;
    }

    /**
     * 提升可读性封装dao层展示方式
     *
     * @param enums
     * @param dbName
     * @param column
     */
    private static final PdfDaoConfig dao(FieldTypeEnums enums, String dbName, String column) {
        return new PdfDaoConfig(enums, dbName + COLON + column);
    }

}
