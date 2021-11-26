package com.chaoxuzhong.study.pdf.company.dao.handler;

import com.chaoxuzhong.BaseAppTest;
import com.chaoxuzhong.study.pdf.company.TestConstantForCompare;
import com.chaoxuzhong.study.pdf.company.dao.db.ForTestDbService;
import com.chaoxuzhong.study.pdf.company.dao.handler.impl.TestDaoNormalHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.impl.TestDaoReturnListHandler;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * 本测试类 GIVEN数据来源均配置在{@link TestConstantForCompare}中
 * 通过{@link ForTestDbService}获取
 */
public class DefaultPdfDaoHandlerTest extends BaseAppTest {

    @Autowired
    private TestDaoNormalHandler testDaoNormalHandler;

    @Autowired
    private TestDaoReturnListHandler testDaoReturnListHandler;


    @Test
    public void dbNormalHandlerTest_returnConvertToMap() {
        HashMap dbMap = new HashMap();
        testDaoNormalHandler.handle("anyMemberNo", dbMap);
        Assert.assertTrue(TestConstantForCompare.DBNORMAL_STRING == dbMap.get(TestConstantForCompare.TESTDAO_NORMAL_DBNAME + ":" + "string"));
        Assert.assertTrue(TestConstantForCompare.DBNORMAL_BIGDECIMAL == dbMap.get(TestConstantForCompare.TESTDAO_NORMAL_DBNAME + ":" + "bigDecimal"));
        Assert.assertTrue(TestConstantForCompare.DBNORMAL_INTEGER == dbMap.get(TestConstantForCompare.TESTDAO_NORMAL_DBNAME + ":" + "integer"));
        Assert.assertNull(dbMap.get(TestConstantForCompare.TESTDAO_NORMAL_DBNAME + ":" + "nullString"));
    }

    @Test
    public void dbNormalListHandlerTest_returnConvertListToMap() {
        HashMap dbMap = new HashMap();
        testDaoReturnListHandler.handle("anyMemberNo", dbMap);
        Assert.assertTrue(TestConstantForCompare.DBNORMAL_STRING_ITEM_ONE == dbMap.get(TestConstantForCompare.TESTDAO_RETURNLIST_DBNAME + ":" + "string:01"));
        Assert.assertTrue(TestConstantForCompare.DBNORMAL_BIGDECIMAL_ITEM_ONE == dbMap.get(TestConstantForCompare.TESTDAO_RETURNLIST_DBNAME + ":" + "bigDecimal:01"));
        Assert.assertTrue(TestConstantForCompare.DBNORMAL_INTEGER_ITEM_ONE == dbMap.get(TestConstantForCompare.TESTDAO_RETURNLIST_DBNAME + ":" + "integer:01"));

        Assert.assertTrue(TestConstantForCompare.DBNORMAL_STRING_ITEM_TWO == dbMap.get(TestConstantForCompare.TESTDAO_RETURNLIST_DBNAME + ":" + "string:02"));
        Assert.assertTrue(TestConstantForCompare.DBNORMAL_BIGDECIMAL_ITEM_TWO == dbMap.get(TestConstantForCompare.TESTDAO_RETURNLIST_DBNAME + ":" + "bigDecimal:02"));
        Assert.assertTrue(TestConstantForCompare.DBNORMAL_INTEGER_ITEM_TWO == dbMap.get(TestConstantForCompare.TESTDAO_RETURNLIST_DBNAME + ":" + "integer:02"));
    }
}