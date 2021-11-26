package com.chaoxuzhong.study.pdf.company.mockdao;

/**
 * 客户基本信息详情对象 member_info_detail
 *
 * @author aden
 * @date 2020-09-15
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class MemberInfoDetail {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 客户编号
     */
    private String memberNo;

    /**
     * 姓名
     */

    private String realname;

    /**
     * 英文名 （英文/中文姓）
     */

    private String surname;

    /**
     * 英文（英文/中文）名
     */

    private String firstName;

    /**
     * 身份证号
     */

    private String idCard;

    /**
     * 性别
     */
    private String sex;

    /**
     * 身份证到期日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expireDate;

    /**
     * 国籍
     */
    private String nationality;

    /**
     * 住宅地址
     */
    private String address;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    /**
     * 出生国家
     */
    private String birthCountry;

    /**
     * 住宅电话
     */
    private String tel;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 账户名
     */
    private String bankAccountName;

    /**
     * 账户号
     */
    private String bankAccountNo;

    /**
     * 币种
     */
    private String currency;

    /**
     * 职业类型
     */
    private String jobType;

    /**
     * 职位
     */
    private String position;

    /**
     * 行业类型
     */
    private String industry;

    /**
     * 雇主名字
     */
    private String employerName;

    /**
     * 受雇年限
     */
    private Long employmentPeriod;

    /**
     * 公司电话
     */
    private String companyTel;

    /**
     * 公司地址
     */
    private String companyAddress;

    /**
     * 传真号
     */
    private String faxNo;

    /**
     * 收入来源
     */
    private String incomeSource;

    /**
     * 年收入
     */
    private String incomeYear;

    /**
     * 资产项目
     */

    private String assetItems;

    /**
     * 资产净值
     */

    private String assetWorth;

    /**
     * 股票经验
     */

    private String sharesPeriod;

    /**
     * 牛熊市经验
     */
    private String bbmPeriod;

    /**
     * 衍生权证
     */
    private String derivativeWarrant;

    /**
     * 期货
     */
    private String futures;

    /**
     * 期权
     */
    private String invesOption;

    /**
     * 投资目的
     */
    private String objective;

    /**
     * 估计投资金额
     */
    private String predictAmount;

    /**
     * 投资年限
     */
    private String hciPeriod;

    /**
     * 可承受的风险等级
     */
    private String risksGrade;

    /**
     * 衍生产品的认同
     */
    private String derivative;

    /**
     * 是否有亲属关系
     */
    private String kinship;

    /**
     * 是否有保证金账户
     */
    private String bondAccount;

    /**
     * 是否有表决权利
     */
    private String vote;

    /**
     * 是否中介人
     */
    private String agent;

    /**
     * 是否证券员工
     */
    private String bondStaff;

    /**
     * 政府职员关系
     */
    private String governmentStaff;

    /**
     * 是否授权
     */
    private String grantAuth;

    /**
     * 税号
     */
    private String taxNo;

    /**
     * 税务地区
     */
    private String taxRegion;

    /**
     * 描述无税号原因
     */
    private String taxDesc;

    /**
     * 无税号理由
     */
    private String taxReason;

    /**
     * 签名
     */
    private String signature;

    /**
     * 身份证正面
     */
    private String idCardFrontImg;

    /**
     * 身份证反面
     */
    private String idCardBackImg;

    /**
     * 银行卡图片
     */
    private String bankImg;

    /**
     * 学历
     */
    private String education;

    /**
     * 其它投资经验
     */
    private String otherInvest;

    /**
     * 其它投资描述
     */
    private String otherInvestDesc;

    /**
     * 身份证类别
     */
    private String idCardType;

    /**
     * 城市
     */
    private String city;


    /**
     * 银行国籍代码
     */
    private String swiftCode;

    /**
     * 财富收益
     */
    private String wealthSource;

    /**
     * 财产来源
     */
    private String propertySource;

    /**
     * 手持证件照
     */
    private String holdCert;

    /**
     * 地址证明
     */
    private String proofAddress;

    private String derivativeExtend;

}

