package com.chaoxuzhong.study.pdf.company.mockdao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public class MemberCoUsa implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 客户编号
     */
    private String memberNo;

    /**
     * 美国身份-是否美国人
     */
    private String usaWhether;

    /**
     * 美国身份-美国联邦纳税人识别号码
     */
    private String usaTaxpayerNo;

    /**
     * 美国身份-满足条件（字典：co_usa_meet_condition）
     */
    private String usaMeetCondition;

    /**
     * 美国身份-客户就此确认没有美国人对客户作重大控制权(百分之十或以上控制权)（Y/N）
     */
    private String usaControlPower;

    /**
     * 美国身份-根据外国帐户税务合规法案定义, 客户是否被定义为”不参与之海外金融机构”(Y/N)
     */
    private String usaNoOverseas;

    /**
     * 美国身份-海外帐户税收合规法案定的身份(字典：co_usa_overseas_identity)
     */
    private String usaOverseasIdentity;

    /**
     * 美国身份-税务识别号码
     */
    private String usaTax;

    /**
     * 美国身份-全球中介人识别码
     */
    private String usaMiddleman;

    /**
     * 美国身份-自行认证-贵公司是否”指定美国人士（Y/N）
     */
    private String usaSelfAppoint;

    /**
     * 美国身份-自行认证-税务身份号码
     */
    private String usaSelfTax;

    /**
     * 美国身份-自行认证-海外金融机构（Y/N）
     */
    private String usaSelfFinancial;

    /**
     * 美国身份-自行认证-选择（枚举：co_usa_self_financial）
     */
    private String usaSelfFinancialExtend1;

    /**
     * 美国身份-自行认证-全球中介人身份号码
     */
    private String usaSelfGlobalMedium;

    /**
     * 美国身份-自行认证-海外金融机构类型
     */
    private String usaSelfFinancialType;

    /**
     * 美国身份-自行认证-请选择非金融海外实体类型（实体：co_non_financial_type）
     */
    private String usaSelfNonFinancialType;

    /**
     * 美国身份-自行认证-是否存在美国公民或居民控制人
     */
    private String usaSelfControl;

    /**
     * 美国身份-自行认证-是否存在美国公民或居民控制人-姓名
     */
    private String usaSelfControlName;

    /**
     * 美国身份-自行认证-是否存在美国公民或居民控制人-税务身份证号码
     */
    private String usaSelfControlTax;

    /**
     * 美国身份-自行认证-是否存在美国公民或居民控制人-地址
     */
    private String usaSelfControlAddress;

    private Date createTime;

}
