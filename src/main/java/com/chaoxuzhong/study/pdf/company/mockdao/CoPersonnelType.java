package com.chaoxuzhong.study.pdf.company.mockdao;

import org.apache.commons.codec.binary.StringUtils;

import java.util.Arrays;
import java.util.List;

public enum CoPersonnelType {

    director("director","董事"),//董事

    beneficial("beneficial","收益人"),//权益拥有人

    shareholder("shareholder","股东"),//股东

    legalagent("legalagent","法定程序代理人"),//法定程序代理人

    control("control","控权人"),//控权人

    grant("grant","获授权人"),//获授权人

    controlAccount("controlAccount","控权人实体账户持有人");//控权人实体账户持有人

    CoPersonnelType(String key, String val) {
        this.key = key;
        this.val = val;
    }

    private String key;

    private String val;

    public String getKey() {
        return key;
    }

    public String getVal() {
        return val;
    }

    public static CoPersonnelType getByKey(String key) {
        for (CoPersonnelType value : CoPersonnelType.values()) {
            if (StringUtils.equals(value.getKey(), key)) {
                return value;
            }
        }
        return null;
    }

    // 公司人员
    static List<String> coPersonnels = Arrays.asList(
            CoPersonnelType.director.getKey(),
            CoPersonnelType.beneficial.getKey(),
            CoPersonnelType.shareholder.getKey(),
            CoPersonnelType.legalagent.getKey()
    );

    // 是公司人员
    public static boolean isCoPersonnels(String type) {
        return coPersonnels.contains(type);
    }

}
