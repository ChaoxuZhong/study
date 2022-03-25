package com.chaoxuzhong.study.pdf.company.dao;

import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoHandlerNameConstant;
import com.chaoxuzhong.study.pdf.company.service.PdfTemplateNameConstant;
import com.chaoxuzhong.study.pdf.company.util.SpringBeanUtil;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.Arrays;
import java.util.Collections;
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
                PdfDaoHandlerNameConstant.MEMBER_INFO_DETAIL,
                PdfDaoHandlerNameConstant.CO_PERSONAL,
                PdfDaoHandlerNameConstant.CO_USA
        )));
        map.put(PdfTemplateNameConstant.ADEN_AC_OPENING_FORM_CORPORATE_02, newChain(Collections.singletonList(PdfDaoHandlerNameConstant.MEMBER_INF0)));
//        map.put(PdfTemplateNameConstant.FW8BEN,newChain(Arrays.asList()))

        map.put(PdfTemplateNameConstant.AML_QUESTIONNAIRE, newChain(Collections.singletonList(PdfDaoHandlerNameConstant.CO_AML)));

        map.put(PdfTemplateNameConstant.SELF_SERT_ENTITY, newChain(Arrays.asList(
                PdfDaoHandlerNameConstant.MEMBER_INF0,
                PdfDaoHandlerNameConstant.CO_INFO,
                PdfDaoHandlerNameConstant.JURISDICTION,
                PdfDaoHandlerNameConstant.CO_PERSONAL
        )));

        map.put(PdfTemplateNameConstant.FW8BEN, newChain(Collections.singletonList(PdfDaoHandlerNameConstant.MEMBER_INF0)));
    }

    private static PdfDaoHandlerChain newChain(List<String> daoHandlerNames) {
        return SpringBeanUtil.getBean(PdfDaoHandlerChain.class).addHandlers(daoHandlerNames);
    }

    public static PdfDaoHandlerChain getPdfDaoHandlerChain(String fileName) {
        PdfDaoHandlerChain chain = map.get(fileName);
        if (ObjectUtils.isEmpty(chain)) {
            throw new IllegalStateException("请在SimplePdfDaoFactory中配置对应pdf fileName：" + fileName+" 所需数据层daoChain");
        }
        return map.get(fileName);
    }

}
