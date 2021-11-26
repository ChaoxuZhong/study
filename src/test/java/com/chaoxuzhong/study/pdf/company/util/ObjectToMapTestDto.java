package com.chaoxuzhong.study.pdf.company.util;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class ObjectToMapTestDto {

    private String string;

    private BigDecimal bigDecimal;

    private Integer integer;

    private Long id;

    private String notToMap;

}
