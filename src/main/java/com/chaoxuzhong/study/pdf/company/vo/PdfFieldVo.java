package com.chaoxuzhong.study.pdf.company.vo;

/**
 * PDF域（需要填充展示）的值
 * 根据此类填充PDF域，类中参数最接近填充PDF时所需数据
 */

import com.chaoxuzhong.study.pdf.company.pojo.FieldTypeEnums;
import lombok.Data;
@Data
public class PdfFieldVo {
    /**
     * 填充值
     * 如果type == FieldType.TEXT, v是文本内容
     * 如果type == FieldType.CHECKBOX, v是 "true"/"false" 字符串
     * 如果type == FieldType.BUTTON v是图片对应imgFilePath
     */
    private String v;
    /**
     * 填充类型 FieldType
     */
    private FieldTypeEnums type;


    public PdfFieldVo(){}

    public PdfFieldVo(FieldTypeEnums type, String value) {
        this.v = value;
        this.type = type;
    }
}
