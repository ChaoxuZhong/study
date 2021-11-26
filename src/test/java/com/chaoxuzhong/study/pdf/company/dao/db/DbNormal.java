package com.chaoxuzhong.study.pdf.company.dao.db;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 单层数据结构
 */
@Data
public class DbNormal {
    private String string;

    private BigDecimal bigDecimal;

    private Integer integer;

    private Long id;

    private String nullString;
}
