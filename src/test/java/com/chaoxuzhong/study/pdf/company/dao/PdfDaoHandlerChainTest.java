package com.chaoxuzhong.study.pdf.company.dao;

import com.chaoxuzhong.BaseAppTest;
import com.chaoxuzhong.study.pdf.company.TestConstantForCompare;
import com.chaoxuzhong.study.pdf.company.util.SpringBeanUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PdfDaoHandlerChainTest extends BaseAppTest {

    /**
     * 测试各handler能形成链，拼接输出map
     */
    @Test
    public void chainTest() {
        PdfDaoHandlerChain pdfDaoHandlerChain = SpringBeanUtil.getBean(PdfDaoHandlerChain.class);
        pdfDaoHandlerChain.addHandlers(Arrays.asList(
                TestConstantForCompare.TESTDAO_IN_CHAIN_ONE_HANDLER,
                TestConstantForCompare.TESTDAO_IN_CHAIN_TWO_HANDLER
        ));
        HashMap<String, Object> map = pdfDaoHandlerChain.handle("anyMemberNo");
        Assert.assertTrue(map.get(TestConstantForCompare.TESTDAO_IN_CHAIN_ONE_DBNAME + ":" + "chainStr") == TestConstantForCompare.DBINCHAIN_STRING_ONE);
        Assert.assertTrue(map.get(TestConstantForCompare.TESTDAO_IN_CHAIN_TWO_DBNAME + ":" + "chainStr") == TestConstantForCompare.DBINCHAIN_STRING_TWO);
    }

    /**
     * 测试形成不同的handler
     */
    @Test
    public void chainScopePrototypeTest() {
        List<PdfDaoHandlerChain> pdfDaoHandlerChainList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            PdfDaoHandlerChain pdfDaoHandlerChain = SpringBeanUtil.getBean(PdfDaoHandlerChain.class);
            pdfDaoHandlerChainList.add(pdfDaoHandlerChain);
        }
        for (int i = 0; i < 50; i++) {
            for (int j = i+1; j < 50; j++) {
                Assert.assertTrue(pdfDaoHandlerChainList.get(i) != pdfDaoHandlerChainList.get(j));
            }
        }
    }

}