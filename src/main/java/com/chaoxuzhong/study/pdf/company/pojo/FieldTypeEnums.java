package com.chaoxuzhong.study.pdf.company.pojo;

import com.spire.pdf.widget.PdfButtonWidgetFieldWidget;
import com.spire.pdf.widget.PdfCheckBoxWidgetFieldWidget;
import com.spire.pdf.widget.PdfTextBoxFieldWidget;

public enum FieldTypeEnums {
    /**
     * 文本 - 对应 {@link PdfTextBoxFieldWidget}
     */

    TEXT,

    /**
     * 复选框 - 对应 {@link PdfCheckBoxWidgetFieldWidget}
     */

    CHECKBOX,
    /**
     * 按钮 - 对应 {@link PdfButtonWidgetFieldWidget}
     * 用于填充签名的图片
     */


    BUTTON;

}
