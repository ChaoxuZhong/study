package com.chaoxuzhong.study.pdf.company.mockdao;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
public class MemberCoPersonnel implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 客户编号
     */
    private String memberNo;

    /**
     * 名称
     */
    private String realName;

    /**
     * 英文名
     */
    private String enName;

    /**
     * 证件号
     */
    private String identityNo;

    /**
     * 证件类型
     */
    private String identityType;

    /**
     * 证件签发地
     */
    private String identityPlace;

    /**
     * 性别
     */
    private String gender;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 股东家属
     */
    private String shareFamily;

    /**
     * 股权比例
     */
    private BigDecimal shareRatio;

    /**
     * 所估控制权
     */
    private BigDecimal estimatedControl;

    /**
     * 人员类型：director-董事，beneficial-权益拥有人，shareholder-股东，legalagent-法定程序代理人，control-控权人，controlAccount-控权人实体账户持有人
     */
    private String type;

    private Date createTime;

    /**
     * 地区
     */
    private String region;

    /**
     * 司法管辖区
     */
    private String jurisdiction;

    /**
     * 税务编号
     */
    private String taxNo;

    /**
     * 无税务编号理由选择(字典:co_control_tax_cause)
     */
    private String taxCauseSelect;

    /**
     * 无税务编号原因
     */
    private String taxDesc;

    /**
     * 是否需要kyc审核：Y/N
     */
    private String needKyc;

    /**
     * kyc状态
     */
    private String kycStatus;

    /**
     * 实体名
     */
    private String entityName;

    /**
     * 控权人类型(字典：co_control_type)
     */
    private String controlType;

    /**
     * 控权人类型对应字典（由control_type决定）
     * co_control_legal
     * co_control_trust
     * co_control_other
     */
    private String controlTypeExtend;

    /**
     * 父级序列号
     */
    private String parentSeqNo;

    /**
     * 序列号
     */
    private String seqNo;

    /**
     * 国籍
     */
    private String nationality;

    private String extend1;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 婚姻状况
     */
    private String maritalStatus;

    /**
     * 住宅电话
     */
    private String homePhone;

    /**
     * 出生国家
     */
    private String birthCountry;

    /**
     * 现居住国家
     */
    private String nowCountry;

    /**
     * 现居住地
     */
    private String nowAddress;

    /**
     * 永久居住地址
     */
    private String perpetualAddress;

    /**
     * 微信号
     */
    private String wechat;

    /**
     * 授权项目
     */
    private String authorizedProject;

    private List<MemberJurisdiction> jurisdictions;

}
