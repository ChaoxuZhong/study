package com.chaoxuzhong.study.pdf.company.service;

import com.chaoxuzhong.study.pdf.company.dao.PdfDaoHandlerChain;
import com.chaoxuzhong.study.pdf.company.dao.SimplePdfDaoFactory;

/**
 * pdf 模板名称,除了不填充数据的模板
 * 每个模板名称可通过{@link SimplePdfDaoFactory}生产一个{@link PdfDaoHandlerChain} 用于获取所需数据
 */
public class PdfTemplateNameConstant {
    // 特殊模板
    public static final String ALL_DB_DATA = "ALL_DB_DATA";
    public static final String TEST_DB_DATA = "test";

    // 与PDF 一一 映射的模板
    public static final String ADEN_AC_OPENING_FORM_CORPORATE_01 = "Aden_AC_opening_form_Corporate01.pdf";
    public static final String ADEN_AC_OPENING_FORM_CORPORATE_02 = "Aden_AC_opening_form_Corporate02.pdf";
}
