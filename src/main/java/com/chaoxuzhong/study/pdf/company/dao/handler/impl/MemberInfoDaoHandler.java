package com.chaoxuzhong.study.pdf.company.dao.handler.impl;


import com.chaoxuzhong.study.pdf.company.dao.handler.DefaultPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.IPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoDbNameConstant;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoHandlerNameConstant;
import com.chaoxuzhong.study.pdf.company.mockdao.TestMockDaoSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(PdfDaoHandlerNameConstant.MEMBER_INF0)
public class MemberInfoDaoHandler extends DefaultPdfDaoHandler implements IPdfDaoHandler {

    @Autowired
    private TestMockDaoSerivce testMockDaoSerivce;

    @Override
    protected String getDbName() {
        return PdfDaoDbNameConstant.MEMBER_INF0;
    }

    @Override
    protected Object getDataFromDb(String memberNo) {
        return testMockDaoSerivce.getMemberInfo();
    }

}
