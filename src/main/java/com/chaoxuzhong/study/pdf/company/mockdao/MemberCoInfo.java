package com.chaoxuzhong.study.pdf.company.mockdao;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class MemberCoInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 客户编号
     */
    private String memberNo;

    /**
     * 知晓途径(字典:m_ways_know)
     */
    private String waysKnow;

    /**
     * 知晓途径扩展
     */
    private String waysKnowExtend;

    /**
     * 账户类型(字典:co_account_type)
     */
    private String accountType;

    /**
     * 户口服务(枚举：co_account_service)
     */
    private String accountService;

    /**
     * 客户信息-公司类别(字典:co_type)
     */
    private String coType;

    /**
     * 客户信息-其他公司类别(co_type为其他时填写)
     */
    private String coTypeExtend;

    /**
     * 业务范围（字典：m_business_nature）
     */
    private String business;

    /**
     * 经营地址
     */
    private String businessAddress;

    /**
     * 商业登记号码
     */
    private String registrationNo;

    /**
     * 电邮地址
     */
    private String postalEmailAddress;

    /**
     * 通讯方式(字典：co_posta_type)
     */
    private String postalType;

    /**
     * 其它通讯地址
     */
    private String postalOtherAddress;

    /**
     * 收款银行所在国家
     */
    private String bankCountry;

    /**
     * 自我证明-公司法定名称
     */
    private String proofLegalName;

    /**
     * 自我证明-公司或实体的成立注册的税务管辖区
     */
    private String proofJurisdiction;

    /**
     * 自我证明-公司注册或商业登记号码
     */
    private String proofRegisterNo;

    /**
     * 自我证明-现时营业地址
     */
    private String proofBusinessAddress;

    /**
     * 自我证明-现时营业国家及城市(国家-省-市)
     */
    private String proofBusinessArea;

    /**
     * 自我证明-邮政编码
     */
    private String proofPostalCode;

    /**
     * 自我证明-通讯地址
     */
    private String proofPostalAddress;

    /**
     * 自我证明-(通讯地址)邮政编码
     */
    private String proffPostalExtend;

    /**
     * 身份披露-是否亲属关系
     */
    private String idsRelativesWhether;

    /**
     * 身份披露-是否亲属关系(名称)
     */
    private String idsRelativesName;

    /**
     * 身份披露-是否亲属关系(关系)
     */
    private String idsRelativesRelation;

    /**
     * 身份披露-成员是否有亚丁客户
     */
    private String idsAdenCustWhether;

    /**
     * 身份披露-成员是否有亚丁客户(名称)
     */
    private String idsAdenCustName;

    /**
     * 身份披露-成员是否有亚丁客户账户号码)
     */
    private String idsAdenCustAccount;

    /**
     * 身份披露-控制35%以上的表决权
     */
    private String idsControlVoteWhether;

    /**
     * 身份披露-控制35%以上的表决权（名称）
     */
    private String idsControlVoteName;

    /**
     * 身份披露-控制35%以上的表决权（账户号）
     */
    private String idsControlVoteAccount;

    /**
     * 贵公司集团旗下公司成员有否在本公司开设帐户（Y/N）
     */
    private String idsOpenAccountWhether;

    /**
     * 贵公司集团旗下公司成员有否在本公司开设帐户（名称）
     */
    private String idsOpenAccountName;

    /**
     * 贵公司集团旗下公司成员有否在本公司开设帐户（账号）
     */
    private String idsOpenAccountNo;

    /**
     * 身份披露-成员是否有持牌机构董事
     */
    private String idsOrganDirectorWhether;

    /**
     * 身份披露-成员是否有持牌机构董事(名称)
     */
    private String idsOrganDirectorName;

    /**
     * 身份披露-成员是否有持牌机构董事(账号)
     */
    private String idsOrganDirectorAccount;

    /**
     * 披露-党政干事（Y/N）
     */
    private String idsPartyWhether;

    /**
     * 披露-党政干事-头衔
     */
    private String idsPartyTitle;

    /**
     * 披露-党政干事-名称
     */
    private String idsPartyName;

    /**
     * 最终实际利益拥有人(co_beneficiary_type)
     */
    private String beneficiary;

    /**
     * 法定股本
     */
    private String equityLegal;

    /**
     * 法定股本每股面值
     */
    private String equityParValue;

    /**
     * 已发行股本
     */
    private String equityIssued;

    /**
     * 已发行股本每股面值
     */
    private String equityIssuedParValue;

    /**
     * 最近年度税后纯利
     */
    private BigDecimal fdProfitAfterTaxLately;

    /**
     * 前一年度税后纯利
     */
    private BigDecimal fdProfitAfterTaxPrevious;

    /**
     * 流动资产
     */
    private BigDecimal fdCurrentAssets;

    /**
     * 资本来源（字典:co_capital_source）
     */
    private String fdCapitalSource;

    /**
     * 资本来源(capital_source为其它时填写)
     */
    private String fdCapitalSourceExtend;

    /**
     * 全年净利润（字典:co_annual_net_profit）
     */
    private String fdAnnualNetProfit;

    /**
     * 全年净利润（annual_net_profit其它时填写）
     */
    private String fdAnnualNetProfitExtend;

    /**
     * 资金来源地（枚举：co_fund_source）
     */
    private String fdFundSource;

    /**
     * 资金来源地（fund_source为其它时填写）
     */
    private String fdFundSourceExtend;

    /**
     * 投资经验-证券（字典:co_iv_type）
     */
    private String ivStock;

    /**
     * 投资经验-期货及期权合约（字典:co_iv_type）
     */
    private String ivFutures;

    /**
     * 投资经验-衍生产品 (认股 证/牛熊证)（字典:co_iv_type）
     */
    private String ivDerivative;

    /**
     * 投资经验-单位信託/互惠基金（字典:co_iv_type）
     */
    private String ivTrust;

    /**
     * 投资经验-交易所买卖基金（字典:co_iv_type）
     */
    private String ivEtf;

    /**
     * 投资经验-债券（字典:co_iv_type）
     */
    private String ivBond;

    /**
     * 投资经验-外币（字典:co_iv_type）
     */
    private String ivForeignCurrency;

    /**
     * 其他投资经验
     */
    private String ivOther;

    /**
     * 投资目的(字典：co_objective)
     */
    private String objective;

    /**
     * 估计投资金额(字典：co_investment)
     */
    private String objectiveInvestment;

    /**
     * 投资年期(字典：co_investment_period)
     */
    private String objectiveInvestmentPeriod;

    /**
     * 可承受风险(字典：co_risk)
     */
    private String objectiveRisk;

    /**
     * 公司简称
     */
    private String abbreviation;


}
