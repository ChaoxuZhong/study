package com.chaoxuzhong.study.pdf.company.config;

import cn.hutool.core.map.MapBuilder;
import com.chaoxuzhong.study.pdf.company.dao.SequenceStr;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoDbNameConstant;
import com.chaoxuzhong.study.pdf.company.mockdao.CoPersonnelType;
import com.chaoxuzhong.study.pdf.company.mockdao.MemberCoPersonnel;
import com.chaoxuzhong.study.pdf.company.pojo.FieldTypeEnums;
import com.chaoxuzhong.study.pdf.company.service.PdfTemplateNameConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

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
                // 1.账户类别
                .put("memberNo", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INF0, "memberNo"))
                .putAll(checkBoxConf("accountType", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "accountType"), 3))
                .putAll(checkBoxConf("accountService", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "accountService"), 2))
                // 2.公司资料
                .put("realName", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INF0, "realName"))
                .put("surName", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "reasurNamelName"))
                .put("business", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "business"))
                .put("businessAddress", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "businessAddress"))
                .put("address", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "address"))
                .put("companyAddress", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "companyAddress"))
                .putAll(checkBoxConf("coType", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "coType"), 5))
                .put("coTypeExtend", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "coTypeExtend"))
                .put("fdFundSourceHK", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "fdFundSource"))
                .put("fdFundSourceCHN", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "fdFundSource"))
                .put("fdFundSourceUS", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "fdFundSource"))
                .put("fdFundSourceOTH", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "fdFundSource"))
                .put("fdFundSourceExtend", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "fdFundSourceExtend"))
                .put("companyTel", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "companyTel"))
                .put("birthCountry", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "birthCountry"))
                .put("birthDate", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "birthDate"))
                .put("idCard", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "idCard"))
                .put("faxNo", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "faxNo"))
                .put("registrationNo", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "registrationNo"))
                .put("email", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INF0, "email"))
                // 3. 董事
                .putAll(daoListMap("directorName", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.director.getKey(), "realName", 8))
                .putAll(daoListMap("directorIdCard", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.director.getKey(), "identityNo", 8))
                .putAll(daoListMap("directorBirthDate", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.director.getKey(), "birthDate", 8))
                .putAll(daoListMap("directorAddress", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.director.getKey(), "address", 8))
                .putAll(daoListMap("directorNationality", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.director.getKey(), "nationality", 8))
                .putAll(daoListMap("directorTelNo", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.director.getKey(), "phone", 8))
                // 4.股东
                .putAll(daoListMap("shareholderName", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.shareholder.getKey(), "realName", 8))
                .putAll(daoListMap("shareholderIdCard", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.shareholder.getKey(), "identityNo", 8))
                .putAll(daoListMap("shareholderBirthDate", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.shareholder.getKey(), "birthDate", 8))
                .putAll(daoListMap("shareholderAddress", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.shareholder.getKey(), "address", 8))
                .putAll(daoListMap("shareholderNationnality", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.shareholder.getKey(), "nationality", 8))
                .putAll(daoListMap("shareholderTelNo", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.shareholder.getKey(), "phone", 8))
                .putAll(daoListMap("shareholderRaito", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.shareholder.getKey(), "shareRatio", 8))
                .putAll(daoListMap("shareholderRelativeUsNo", CBOX, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.shareholder.getKey(), "shareholderRelativeUsNo", 8))
                .putAll(daoListMap("shareholderFamilyNo", CBOX, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.shareholder.getKey(), "shareholderFamilyNo", 8))
                .putAll(daoListMap("shareholderFamilyYes", CBOX, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.shareholder.getKey(), "shareholderFamilyYes", 8))
                .putAll(daoListMap("shareFamily", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.shareholder.getKey(), "shareFamily", 8))
                // 5.最终实际利益拥有人
                .put("beneficiaryYes", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "beneficiaryYes"))
                .put("beneficiaryNo", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "beneficiaryNo"))
                .put("beneficiaryC", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "beneficiary"))
                .put("beneficiaryD", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "beneficiary"))
                .put("beneficiaryE", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "beneficiary"))
                .put("sex0", dao(CBOX, PdfDaoDbNameConstant.MEMBER_INF0, "sex"))
                .put("sex1", dao(CBOX, PdfDaoDbNameConstant.MEMBER_INF0, "sex"))
                .put("sex2", dao(CBOX, PdfDaoDbNameConstant.MEMBER_INF0, "sex"))
                .put("enName", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "enName"))
                .put("chName", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "realName"))
                .put("passportNo", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "passportNo"))
                .put("passportPlace", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "passportPlace"))
                .put("idCard", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "idCard"))
                .put("idcardPlace", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "idcardPlace"))
                .put("birthCountry", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "birthCountry"))
                .put("nationality", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "nationality"))
                .put("maritalStatus01", dao(CBOX, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "maritalStatus"))
                .put("maritalStatus02", dao(CBOX, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "maritalStatus"))
                .put("nowAddress", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "nowAddress"))
                .put("nowCountry", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "nowCountry"))
                .put("perpetualAddress", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "perpetualAddress"))
                .put("perpetualCountry", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "nationality"))
                .put("homePhone", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "homePhone"))
                .put("region", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "region"))
                .put("phone", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "phone"))
                .put("benificalUsNo", defaultCheck())
                .put("benificialEmail", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "phone"))
                .put("benificialRatio", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "benificialRatio"))
                .put("estimatedControl", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "estimatedControl"))
                // 6.股本
                .put("equityLegal", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "equityLegal"))
                .put("equityParValue", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "equityParValue"))
                .put("equityIssued", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "equityIssued"))
                .put("equityIssuedParValue", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "equityIssuedParValue"))
                // 7.财务状况
                .put("fdProfitAfterTaxLately", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "fdProfitAfterTaxLately"))
                .put("fdProfitAfterTaxPrevious", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "fdProfitAfterTaxPrevious"))
                .put("assetWorth", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "assetWorth"))
                .put("fdCurrentAssets", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "fdCurrentAssets"))
                .putAll(daoListMap("fdCapitalSource", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "fdCapitalSource", 5))
                .put("fdCapitalSourceExtend", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "fdCapitalSourceExtend"))
                .putAll(daoListMap("fdAnnualNetProfit", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "fdAnnualNetProfit", 7))
                .put("fdAnnualNetProfitExtend", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "fdAnnualNetProfitExtend"))
                .put("fdFundSourceHK", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "fdFundSource"))
                .put("fdFundSourceCHN", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "fdFundSource"))
                .put("fdFundSourceUS", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "fdFundSource"))
                .put("fdFundSourceOTH", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "fdFundSource"))
                .put("fdFundSourceExtend", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "fdFundSourceExtend"))
                .putAll(daoListMap("fdFundSource", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "fdFundSource", 4))
                .putAll(daoListMap("ivStock", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "ivStock", 5))
                // 8.投资经验
                .build();


        ALL_CONFIG.put(PdfTemplateNameConstant.ADEN_AC_OPENING_FORM_CORPORATE_01, CUST_INFO_01);
    }

    // 复选框配置
    private static HashMap<String, PdfDaoConfig> checkBoxConf(String voPrefix, PdfDaoConfig daoConfig, int boxsNum) {
        HashMap<String, PdfDaoConfig> result = new HashMap<>();
        SequenceStr sequenceStr = SequenceStr.init();
        for (int i = 0; i < boxsNum; i++) {
            sequenceStr.add();
            result.put(voPrefix + sequenceStr.getSequence(), daoConfig);
        }
        return result;
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
     * @param enums PDF域类型
     * @param dbName 数据库名称
     * @param column 需要展示的列
     */
    private static final PdfDaoConfig dao(FieldTypeEnums enums, String dbName, String column) {
        return new PdfDaoConfig(enums, addSurfix(dbName, column));
    }

    /**
     * 列表
     * @param mapKeyPrefix pdf域前缀
     * @param enums PDF域类型
     * @param dbName 数据库名称
     * @param detailTypeName 同一数据库查出多种类型数据时，需要填写具体数据类型名称 比如{@link MemberCoPersonnel}中有多种type数据 。  且每种类型有多条数据，均需要展示
     * @param column 数据库列名称
     * @param listSize 列表大小
     * @return
     */
    private static final HashMap<String, PdfDaoConfig> daoListMap(String mapKeyPrefix, FieldTypeEnums enums, String dbName, String detailTypeName, String column, int listSize) {
        HashMap<String, PdfDaoConfig> result = new HashMap<>();
        SequenceStr seq = SequenceStr.init();
        String seqStr = "";

        for (int i = 0; i < listSize; i++) {
            seq.add();
            seqStr = seq.getSequence();
            if (StringUtils.isEmpty(detailTypeName)) {
                result.put(mapKeyPrefix+seqStr, new PdfDaoConfig(enums, addSurfix(dbName, column)));
            }else{
                result.put(mapKeyPrefix+seqStr, new PdfDaoConfig(enums, addSurfix(addSurfix(addSurfix(dbName, detailTypeName), column), seqStr)));
            }
        }
        return result;
    }

    private static final PdfDaoConfig dao(FieldTypeEnums enums, String dbName, String detailTypeName, String column) {
        return new PdfDaoConfig(enums, addSurfix(dbName, addSurfix(detailTypeName, column)));
    }

    /**
     * 默认勾选的字段配置
     * @return
     */
    private static final PdfDaoConfig defaultCheck() {
        return new PdfDaoConfig(CBOX, true);
    }

    // 增加前缀
    private static String addPrefix(String sourceStr, String prefix) {
        return prefix.concat(COLON).concat(sourceStr);
    }

    // 增加后缀
    private static String addSurfix(String sourceStr, String surfix) {
        return sourceStr.concat(COLON).concat(surfix);
    }

}
