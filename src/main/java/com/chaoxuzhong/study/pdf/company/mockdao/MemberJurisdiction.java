package com.chaoxuzhong.study.pdf.company.mockdao;

import lombok.Data;

import java.util.Date;

@Data
public class MemberJurisdiction {

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
     * 人员列表序号
     */
    private String personnelSeqNo;

    /**
     * 司法管辖区
     */
    private String jurisdiction;

    /**
     * 税务编号
     */
    private String taxNo;

    /**
     * 无税务编号理由选择
     */
    private String taxCauseSelect;

    /**
     * 无税务编号原因
     */
    private String taxDesc;

    /**
     * 类型：cust-客户本身，control-控权人
     */
    private String type;

    private Date createTime;

}
