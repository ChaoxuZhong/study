package com.chaoxuzhong.study.pdf.company.dao.handler.impl;


import com.chaoxuzhong.study.pdf.company.dao.handler.DefaultPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.IPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoDbNameConstant;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoHandlerNameConstant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(PdfDaoHandlerNameConstant.CO_FILE_SIN)
public class CoFileSignDaoHandler extends DefaultPdfDaoHandler implements IPdfDaoHandler {

    @Override
    protected String getDbName() {
        return PdfDaoDbNameConstant.CO_FILE_SIN;
    }

    @Override
    protected Object getDataFromDb(String memberNo) {
        return null;
    }

}
