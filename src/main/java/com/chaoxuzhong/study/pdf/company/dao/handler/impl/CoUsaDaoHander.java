package com.chaoxuzhong.study.pdf.company.dao.handler.impl;

import com.chaoxuzhong.study.pdf.company.dao.handler.DefaultPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.IPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoDbNameConstant;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoHandlerNameConstant;
import com.chaoxuzhong.study.pdf.company.mockdao.TestMockDaoSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(PdfDaoHandlerNameConstant.CO_USA)
public class CoUsaDaoHander extends DefaultPdfDaoHandler implements IPdfDaoHandler {

    @Autowired
    private TestMockDaoSerivce testMockDaoSerivce;

    @Override
    protected String getDbName() {
        return PdfDaoDbNameConstant.CO_USA;
    }

    @Override
    protected Object getDataFromDb(String memberNo) {
        return testMockDaoSerivce.getMemberUsa();
    }


}
