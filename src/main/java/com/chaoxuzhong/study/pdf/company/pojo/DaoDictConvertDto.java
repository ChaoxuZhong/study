package com.chaoxuzhong.study.pdf.company.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 字典处理传输类
 */
@Data
@AllArgsConstructor
public class DaoDictConvertDto {
    /**
     * 转换的字段
     */
    private String daoFieldName;
    /**
     * 对应的枚举type
     */
    private String dictType;
}
