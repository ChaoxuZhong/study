package com.chaoxuzhong.study.pdf.company.dao.handler.impl;

import com.chaoxuzhong.study.pdf.company.dao.handler.DefaultPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.IPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoDbNameConstant;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoHandlerNameConstant;
import org.springframework.stereotype.Service;

@Service(PdfDaoHandlerNameConstant.JURISDICTION)
public class JurisdictionDaoHandler extends DefaultPdfDaoHandler implements IPdfDaoHandler {

    @Override
    protected String getDbName() {
        return PdfDaoDbNameConstant.JURISDICTION;
    }

    @Override
    protected Object getDataFromDb(String memberNo) {
        return null;
    }

}
