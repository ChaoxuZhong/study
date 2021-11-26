package com.chaoxuzhong.study.pdf.company.dao;

import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoHandlerNameConstant;
import com.chaoxuzhong.study.pdf.company.service.PdfTemplateNameConstant;
import com.chaoxuzhong.study.pdf.company.util.SpringBeanUtil;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * PdfDaoHandlerChain工厂
 */
@NoArgsConstructor
public class SimplePdfDaoFactory {

    private static HashMap<String, PdfDaoHandlerChain> map = new HashMap<>();
    static{
        // 特殊handlerChain
        map.put(PdfTemplateNameConstant.TEST_DB_DATA, newChain(Arrays.asList(PdfDaoHandlerNameConstant.CO_USA)));
        map.put(PdfTemplateNameConstant.ALL_DB_DATA, newChain(PdfDaoHandlerNameConstant.getAllName()));

        // 与PDF模板一一对应的handlerChain
        map.put(PdfTemplateNameConstant.ADEN_AC_OPENING_FORM_CORPORATE_01, newChain(Arrays.asList(
                PdfDaoHandlerNameConstant.MEMBER_INF0,
                PdfDaoHandlerNameConstant.CO_INFO,
                PdfDaoHandlerNameConstant.MEMBER_INFO_DETAIL
        )));
        map.put(PdfTemplateNameConstant.ADEN_AC_OPENING_FORM_CORPORATE_02, newChain(Arrays.asList(PdfDaoHandlerNameConstant.MEMBER_INF0)));
    }

    private static PdfDaoHandlerChain newChain(List<String> daoHandlerNames) {
        return SpringBeanUtil.getBean(PdfDaoHandlerChain.class).addHandlers(daoHandlerNames);
    }

    public static PdfDaoHandlerChain getPdfDaoHandlerChain(String fileName) {
        return map.get(fileName);
    }

}
