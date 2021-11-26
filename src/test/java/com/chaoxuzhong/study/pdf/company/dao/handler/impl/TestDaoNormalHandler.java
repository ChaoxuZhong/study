package com.chaoxuzhong.study.pdf.company.dao.handler.impl;

import com.chaoxuzhong.study.pdf.company.TestConstantForCompare;
import com.chaoxuzhong.study.pdf.company.dao.db.ForTestDbService;
import com.chaoxuzhong.study.pdf.company.dao.handler.DefaultPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.IPdfDaoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 返回单层数据结构，没有需要特殊处理的数据访问层
 */
@Service(TestConstantForCompare.TESTDAO_NORMAL_HANDLER)
public class TestDaoNormalHandler extends DefaultPdfDaoHandler implements IPdfDaoHandler {

    @Autowired
    private ForTestDbService forTestDbService;

    @Override
    protected String getDbName() {
        return TestConstantForCompare.TESTDAO_NORMAL_DBNAME;
    }

    @Override
    protected Object getDataFromDb(String memberNo) {
        return forTestDbService.getNormalDb();
    }
}
