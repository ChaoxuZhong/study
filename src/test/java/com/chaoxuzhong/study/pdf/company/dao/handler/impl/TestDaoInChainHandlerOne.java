package com.chaoxuzhong.study.pdf.company.dao.handler.impl;

import com.chaoxuzhong.study.pdf.company.TestConstantForCompare;
import com.chaoxuzhong.study.pdf.company.dao.db.ForTestDbService;
import com.chaoxuzhong.study.pdf.company.dao.handler.DefaultPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.IPdfDaoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(TestConstantForCompare.TESTDAO_IN_CHAIN_ONE_HANDLER)
public class TestDaoInChainHandlerOne extends DefaultPdfDaoHandler implements IPdfDaoHandler {
    @Autowired
    private ForTestDbService forTestDbService;
    @Override
    protected String getDbName() {
        return TestConstantForCompare.TESTDAO_IN_CHAIN_ONE_DBNAME;
    }

    @Override
    protected Object getDataFromDb(String memberNo) {
        return forTestDbService.getDbOneInChain();
    }
}
