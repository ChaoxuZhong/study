package com.chaoxuzhong.study.pdf.company.mockdao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class MemberInfo {

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
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 性别
     */
    private String sex;

    /**
     * 状态
     */
    private String status;

    /**
     * 纸质审核状态
     */
    private String paperStatus;

    /**
     * 恒生客户号
     */
    private String hsAccount;

    /**
     * 港股帐号
     */
    private String hkSharesAccount;

    /**
     * A股帐号
     */
    private String aSharesAccount;

    /**
     * 入金状态
     */
    private String depositStatus;

    /**
     * 归档状态
     */
    private String fileAwayStatus;

    /**
     * 开户邮件发送状态
     */
    private String emailStatus;

    /**
     * 账户申请类型
     */
    private String applyStatus;

    /**
     * kyc文件信息
     */
    private String kycPdfFiles;

    /**
     * 客户号开通状态
     */
    private String hsAccountStatus;

    /**
     * 港股开通状态
     */
    private String hkAccountStatus;
    /**
     * A股开通状态
     */
    private String aAccountStatus;

    private String openId;

    private String propeStatus;

    private String pdfFileId;

    //ca认证后的pdf
    private String caPdfFileId;

    private String kycAuditStatus;

    private List<String> filterStatus;

    private String claim;

    private BigDecimal totalDepositAmount;//累计入金金额

    /**
     * Y-已签，N-超过申明时间未签, W-等待签字
     */
    private String statement;


    private String ocrFlag;

    private String userType;

    /**
     * 执行步骤
     */
    private String executeStep;

    /**
     * 开户时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date openTime;
    /**
     * 推送恒生时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pushHsTime;

    /**
     * 是否自动审核（Y/N）
     */
    private String autoAudit;

    /**
     * 地区码
     */
    private String areaCode;

    /**
     * kyc获取方式：API,REPTILE
     */
    private String kycGainType;

    /**
     * 个人申明状态
     */
    private String statementStatus;

    private String memberType;

}
