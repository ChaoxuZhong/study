package com.chaoxuzhong.study.pdf.company.dao;

import cn.hutool.core.util.ReflectUtil;
import com.chaoxuzhong.BaseAppTest;
import com.chaoxuzhong.study.pdf.company.dao.handler.IPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoHandlerNameConstant;
import com.chaoxuzhong.study.pdf.company.service.PdfTemplateNameConstant;
import com.chaoxuzhong.study.pdf.company.util.SpringBeanUtil;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

public class SimplePdfDaoFactoryTest extends BaseAppTest {

    @Test
    public void test1() throws IllegalAccessException {
        // GIVEN SimplePdfDaoFactory 中 PdfTemplateNameConstant.TEST_DB_DATA 为 key的map中。因是 private 变量，通过Spring容器启动：这样mock方便
        // WHEN
        PdfDaoHandlerChain chain = SimplePdfDaoFactory.getPdfDaoHandlerChain(PdfTemplateNameConstant.TEST_DB_DATA);
        // THEN daoHandler 都是单例，所以应当能在bean factory中获取到 且与ApplicationContext中获取到相等
        Field pdfDaoHandlers1 = ReflectUtil.getField(chain.getClass(), "pdfDaoHandlers");
        pdfDaoHandlers1.setAccessible(true);
        List<IPdfDaoHandler> handlers= (List<IPdfDaoHandler>) pdfDaoHandlers1.get(chain);
        Assert.assertTrue(handlers.get(0) == SpringBeanUtil.getBean(PdfDaoHandlerNameConstant.CO_USA));
    }

}