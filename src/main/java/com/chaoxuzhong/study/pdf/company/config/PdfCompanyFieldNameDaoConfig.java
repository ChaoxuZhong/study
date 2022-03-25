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
    // 具体类型为此时，对应的值需要拼接后缀：01，02，03等
    private static final String DETAIL_TYPE_SEQ_ADD = "DETAIL_TYPE_SEQ_ADD";

    /**
     * 所有文件的数据库层配置 key为文件模板名称，value为对应文件模板map
     * 文件模板map key为pdf域名，value PdfDaoConfig配置了pdf从dao层取值的字段及PDF中展示方式
     */
    public static final HashMap<String, HashMap<String, PdfDaoConfig>> ALL_CONFIG = new HashMap<>();

    // 客户信息 part1
    static {
        HashMap<String, PdfDaoConfig> CUST_INFO_01 = new HashMap<>();
        new MapBuilder<>(CUST_INFO_01)
                // 1.账户类别
                .put("memberNo", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INF0, "memberNo"))
                .putAll(checkBoxConf("accountType", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "accountType"), 3))
                .putAll(checkBoxConf("accountService", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "accountService"), 2))
                // 2.公司资料
                .put("realName", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INF0, "realname"))
                .put("surName", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, "surname"))
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
                .put("benificialIdCard", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "identityNo"))
                .put("idCardPlace", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "idCardPlace"))
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
                .put("benificialEmail", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "email"))
                .put("benificialRatio", dao(TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.beneficial.getKey(), "shareRatio"))
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
                // 8.投资经验
                .putAll(daoListMap("ivStock", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "ivStock", 5))
                .putAll(daoListMap("ivDerivative", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "ivDerivative", 5))
                .putAll(daoListMap("ivTrust", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "ivTrust", 5))
                .putAll(daoListMap("ivEtf", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "ivEtf", 5))
                .putAll(daoListMap("ivBond", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "ivBond", 5))
                .putAll(daoListMap("ivForeignCurrency", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "ivForeignCurrency", 5))
                .putAll(daoListMap("ivFutures", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "ivFutures", 5))
                .put("ivOther", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "ivOther"))
                // 9.投资目的
                .putAll(daoListMap("objectives", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "objective", 8))
                .putAll(daoListMap("objectiveInvestment", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "objectiveInvestment", 5))
                .putAll(daoListMap("objectiveInvestmentPeriod", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "objectiveInvestmentPeriod", 3))
                .putAll(daoListMap("objectiveRisk", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "objectiveRisk", 3))
//                .put("objectivesOther", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "objectivesOther"))
                // 10.客户对衍生品的认识
                .putAll(daoListMap("derivative", CBOX, PdfDaoDbNameConstant.MEMBER_INFO_DETAIL, null, "derivative", 4))
                // 12,通讯方式
                .putAll(daoListMap("postalType", CBOX, PdfDaoDbNameConstant.CO_INFO, null, "postalType", 4))
                .put("postalOtherAddress", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "postalOtherAddress"))
                // 13.授权人
                .putAll(daoListMap("grantName", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.grant.getKey(), "realName", 3))
                .putAll(daoListMap("grantBirthDate", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.grant.getKey(), "birthDate", 3))
                .putAll(daoListMap("grantIdentityNo", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.grant.getKey(), "identityNo", 3))
                .putAll(daoListMap("grantNationality", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.grant.getKey(), "nationality", 3))
                .putAll(daoListMap("grantTelNo", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.grant.getKey(), "phone", 3))
                .putAll(daoListMap("grantWechat", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.grant.getKey(), "wechat", 3))
                .putAll(daoListMap("grntuthorizedProjectA", CBOX, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.grant.getKey(), "authorizedProject", 3))
                .putAll(daoListMap("grntuthorizedProjectB", CBOX, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.grant.getKey(), "authorizedProject", 3))
                .putAll(daoListMap("grntuthorizedProjectC", CBOX, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.grant.getKey(), "authorizedProject", 3))
                // 14.披露关联账户
                .put("idsRelativesWhetherY", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "idsRelativesWhether"))
                .put("idsRelativesWhetherN", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "idsRelativesWhether"))
                .put("idsRelativesName", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "idsRelativesName"))
                .put("idsAdenCustAccount", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "idsAdenCustAccount"))
                .put("idsControlVoteWhetherN", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "idsControlVoteWhether"))
                .put("idsControlVoteWhetherY", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "idsControlVoteWhether"))
                .put("idsControlVoteName", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "idsControlVoteName"))
                .put("idsControlVoteAccount", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "idsControlVoteAccount"))
                .put("idsOpenAccountWhetherN", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "idsOpenAccountWhether"))
                .put("idsOpenAccountWhetherY", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "idsOpenAccountWhether"))
                .put("idsOrganDirectorWhetherN", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "idsOrganDirectorWhether"))
                .put("idsOrganDirectorWhetherY", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "idsOrganDirectorWhether"))
                .put("idsPartyWhetherN", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "idsPartyWhether"))
                .put("idsPartyWhetherY", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "idsPartyWhether"))
                .put("idsOrganDirectorName", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "idsOrganDirectorName"))
                .put("idsOrganDirectorAccount", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "idsOrganDirectorAccount"))
                .put("idsAdenCustName", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "idsAdenCustName"))
                .put("idsAdenCustWhetherY", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "idsAdenCustWhether"))
                .put("idsAdenCustWhetherN", dao(CBOX, PdfDaoDbNameConstant.CO_INFO, "idsAdenCustWhether"))
                .put("idsRelativesRelation", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "idsRelativesRelation"))
                // 16.美国人身份
                .put("usaWhetherN", dao(CBOX, PdfDaoDbNameConstant.CO_USA, "usaWhether"))
                .put("usaWhetherY", dao(CBOX, PdfDaoDbNameConstant.CO_USA, "usaWhether"))
                .put("usaTaxpayerNo", dao(TEXT, PdfDaoDbNameConstant.CO_USA, "usaTaxpayerNo"))
                .putAll(daoListMap("usaMeetCondition", CBOX, PdfDaoDbNameConstant.CO_USA, null, "usaMeetCondition", 6))
                // 17.外国账号税务合规法案
                .put("usaControlPowerN", dao(CBOX, PdfDaoDbNameConstant.CO_USA, "usaControlPower"))
                .put("usaControlPowerY", dao(CBOX, PdfDaoDbNameConstant.CO_USA, "usaControlPower"))
                .put("usaYesOverseas", dao(CBOX, PdfDaoDbNameConstant.CO_USA, "usaNoOverseas"))
                .put("usaNoOverseas", dao(CBOX, PdfDaoDbNameConstant.CO_USA, "usaNoOverseas"))
                .putAll(daoListMap("usaOverseasIdentity", CBOX, PdfDaoDbNameConstant.CO_USA, null, "usaOverseasIdentity", 4))
                .put("usaTax", dao(TEXT, PdfDaoDbNameConstant.CO_USA, "usaTax"))
                .put("usaMiddleman", dao(TEXT, PdfDaoDbNameConstant.CO_USA, "usaMiddleman"))
                // 18.自行认证
                .put("usaSelfAppointY", dao(CBOX, PdfDaoDbNameConstant.CO_USA, "usaSelfAppoint"))
                .put("usaSelfAppointN", dao(CBOX, PdfDaoDbNameConstant.CO_USA, "usaSelfAppoint"))
                .put("usaSelfFinancialY", dao(CBOX, PdfDaoDbNameConstant.CO_USA, "usaSelfFinancial"))
                .put("usaSelfFinancialN", dao(CBOX, PdfDaoDbNameConstant.CO_USA, "usaSelfFinancial"))
                .put("usaSelfTax", dao(TEXT, PdfDaoDbNameConstant.CO_USA, "usaSelfTax"))

                .put("protectCheck", defaultCheck())
                .put("riskCheck", defaultCheck())
                .build();

        ALL_CONFIG.put(PdfTemplateNameConstant.ADEN_AC_OPENING_FORM_CORPORATE_01, CUST_INFO_01);
    }

    // 风险问卷
    static {
        HashMap<String, PdfDaoConfig> AML_QUESTIONNAIRE = new HashMap<>();
        new MapBuilder<>(AML_QUESTIONNAIRE)
                .putAll(templateDaoListMap("amlPolicy%sY", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlPolicyY", 6))
                .putAll(templateDaoListMap("amlPolicy%sN", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlPolicyN", 6))
                .putAll(templateDaoListMap("amlKycPolicy%sY", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlKycPolicyY", 4))
                .putAll(templateDaoListMap("amlKycPolicy%sN", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlKycPolicyN", 4))
                .putAll(templateDaoListMap("amlMonit%sY", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlMonitY", 3))
                .putAll(templateDaoListMap("amlMonit%sN", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlMonitN", 3))
                .putAll(templateDaoListMap("amlProtection%sY", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlProtectionY", 3))
                .putAll(templateDaoListMap("amlProtection%sN", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlProtectionN", 3))
                .putAll(templateDaoListMap("amlTradeSuitability%sY", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlTradeSuitabilityY", 7))
                .putAll(templateDaoListMap("amlTradeSuitability%sN", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlTradeSuitabilityN", 7))
                .putAll(templateDaoListMap("amlDiscretionary%sY", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlDiscretionaryY", 4))
                .putAll(templateDaoListMap("amlDiscretionary%sN", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlDiscretionaryN", 4))
                .putAll(templateDaoListMap("amlLaw%sY", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlLawY", 2))
                .putAll(templateDaoListMap("amlLaw%sN", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlLawN", 2))
                .putAll(templateDaoListMap("amlFatca%sY", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlFatcaY", 5))
                .putAll(templateDaoListMap("amlFatca%sN", CBOX, PdfDaoDbNameConstant.CO_AML, null, "amlFatcaN", 5))
                .build();
        ALL_CONFIG.put(PdfTemplateNameConstant.AML_QUESTIONNAIRE, AML_QUESTIONNAIRE);
    }

    // 自我证明表格
    static {
        HashMap<String, PdfDaoConfig> SELF_SERT_ENTITY = new HashMap<>();
        new MapBuilder<>(SELF_SERT_ENTITY)
                .put("realName01", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INF0, "realname"))
                .put("memberNo", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INF0, "memberNo"))
                .put("proofLegalName", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "proofLegalName"))
                .put("proofJurisdiction", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "proofJurisdiction"))
                .put("proofRegisterNo", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "proofRegisterNo"))
                .put("proofBusinessAddress", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "proofBusinessAddress"))
                .put("proofBusinessArea", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "proofBusinessArea"))
                .put("proofPostalAddress", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "proofPostalAddress"))
                .put("proffPostalExtend", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "proffPostalExtend"))
                .put("proofPostalCode", dao(TEXT, PdfDaoDbNameConstant.CO_INFO, "proofPostalCode"))
                .putAll(daoListMap("jurisdiction", TEXT, PdfDaoDbNameConstant.JURISDICTION, DETAIL_TYPE_SEQ_ADD, "jurisdiction", 5))
                .putAll(daoListMap("taxNo", TEXT, PdfDaoDbNameConstant.JURISDICTION, DETAIL_TYPE_SEQ_ADD, "taxNo", 5))
                .putAll(daoListMap("taxCauseSelect", TEXT, PdfDaoDbNameConstant.JURISDICTION, DETAIL_TYPE_SEQ_ADD, "taxCauseSelect", 5))
                .putAll(daoListMap("taxDesc", TEXT, PdfDaoDbNameConstant.JURISDICTION, DETAIL_TYPE_SEQ_ADD, "taxDesc", 5))
                .putAll(daoListMap("controller", TEXT, PdfDaoDbNameConstant.CO_PERSONAL, CoPersonnelType.control.getKey(), "realName", 8))
                .build();

        ALL_CONFIG.put(PdfTemplateNameConstant.SELF_SERT_ENTITY, SELF_SERT_ENTITY);
    }

    // fw8ben
    static {
        HashMap<String, PdfDaoConfig> FW8BEN = new HashMap<>();
        new MapBuilder<>(FW8BEN)
                .put("realName", dao(TEXT, PdfDaoDbNameConstant.MEMBER_INF0, "realname"))
                .build();

        ALL_CONFIG.put(PdfTemplateNameConstant.FW8BEN, FW8BEN);
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
     * @param enums  PDF域类型
     * @param dbName 数据库名称
     * @param column 需要展示的列
     */
    private static final PdfDaoConfig dao(FieldTypeEnums enums, String dbName, String column) {
        return new PdfDaoConfig(enums, addSurfix(dbName, column));
    }

    /**
     * 列表
     *
     * @param mapKeyPrefix   pdf域前缀
     * @param enums          PDF域类型
     * @param dbName         数据库名称
     * @param detailTypeName 同一数据库查出多种类型数据时，需要填写具体数据类型名称 比如{@link MemberCoPersonnel}中有多种type数据 。  且每种类型有多条数据，均需要展示
     * @param column         数据库列名称
     * @param listSize       列表大小
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
                result.put(mapKeyPrefix + seqStr, new PdfDaoConfig(enums, addSurfix(dbName, column)));
            } else if (DETAIL_TYPE_SEQ_ADD.equals(detailTypeName)) {
                result.put(mapKeyPrefix + seqStr, new PdfDaoConfig(enums, addSurfix(addSurfix(dbName, column), seqStr)));
            } else {
                result.put(mapKeyPrefix + seqStr, new PdfDaoConfig(enums, addSurfix(addSurfix(addSurfix(dbName, detailTypeName), column), seqStr)));
            }
        }
        return result;
    }

    private static final HashMap<String, PdfDaoConfig> templateDaoListMap(String templateKey, FieldTypeEnums enums, String dbName, String detailTypeName, String column, int listSize) {
        HashMap<String, PdfDaoConfig> result = new HashMap<>();
        SequenceStr seq = SequenceStr.init();
        String seqStr = "";

        for (int i = 0; i < listSize; i++) {
            seq.add();
            seqStr = seq.getSequence();
            if (StringUtils.isEmpty(detailTypeName)) {
                result.put(String.format(templateKey, seqStr), new PdfDaoConfig(enums, addSurfix(dbName, column)));
            } else if (DETAIL_TYPE_SEQ_ADD.equals(detailTypeName)) {
                result.put(String.format(templateKey, seqStr), new PdfDaoConfig(enums, addSurfix(addSurfix(dbName, column), seqStr)));
            } else {
                result.put(String.format(templateKey, seqStr), new PdfDaoConfig(enums, addSurfix(addSurfix(addSurfix(dbName, detailTypeName), column), seqStr)));
            }

        }
        return result;
    }


    private static final PdfDaoConfig dao(FieldTypeEnums enums, String dbName, String detailTypeName, String column) {
        return new PdfDaoConfig(enums, addSurfix(dbName, addSurfix(detailTypeName, column)));
    }

    /**
     * 默认勾选的字段配置
     *
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
