package com.chaoxuzhong.study.pdf.company.mockdao;

import cn.hutool.json.JSONArray;
import lombok.Data;

import java.util.Date;

@Data
public class MemberCoAml {
    private static final long serialVersionUID = 1L;
    private Long id;

    /**
     * 客户编号
     */
    private String memberNo;

    /**
     * 反洗钱-一般反洗钱政策和程序(枚举：co_aml_policy)，数据格式：[{"index":"1","val":"Y/N","desc":"原因:val为N时填写"}]
     */
    private JSONArray amlPolicy;

    /**
     * 反洗钱-客户（KYC）政策(枚举：co_aml_kyc_policy)，数据格式：[{"index":"1","val":"Y/N","desc":"原因:val为N时填写"}]
     */
    private JSONArray amlKycPolicy;

    /**
     * 反洗钱-事务监视(枚举：co_aml_monit)，数据格式：[{"index":"1","val":"Y/N","desc":"原因:val为N时填写"}]
     */
    private JSONArray amlMonit;

    /**
     * 反洗钱-客户资产保护(枚举：co_aml_protection)，数据格式：[{"index":"1","val":"Y/N","desc":"原因:val为N时填写"}]
     */
    private JSONArray amlProtection;

    /**
     * 反洗钱-交易适宜性(枚举：co_aml_trade_suitability)，数据格式：[{"index":"1","val":"Y/N","desc":"原因:val为N时填写"}]
     */
    private JSONArray amlTradeSuitability;

    /**
     * 反洗钱-全权委托账户(枚举：co_aml_discretionary)，数据格式：[{"index":"1","val":"Y/N","desc":"原因:val为N时填写"}]
     */
    private JSONArray amlDiscretionary;

    /**
     * 反洗钱-法律和纪律记录(枚举：co_aml_law)，数据格式：[{"index":"1","val":"Y/N","desc":"原因:val为N时填写"}]
     */
    private JSONArray amlLaw;

    /**
     * 反洗钱-外国账户税务合规法案（FATCA）(枚举：co_aml_fatca)，数据格式：[{"index":"1","val":"Y/N","desc":"原因:val为N时填写"}]
     */
    private JSONArray amlFatca;

    private Date createTime;

}
