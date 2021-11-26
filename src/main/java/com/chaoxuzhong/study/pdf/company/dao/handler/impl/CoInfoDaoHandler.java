package com.chaoxuzhong.study.pdf.company.dao.handler.impl;

import com.chaoxuzhong.study.pdf.company.dao.handler.DefaultPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.IPdfDaoHandler;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoDbNameConstant;
import com.chaoxuzhong.study.pdf.company.dao.handler.PdfDaoHandlerNameConstant;
import com.chaoxuzhong.study.pdf.company.mockdao.TestMockDaoSerivce;
import com.chaoxuzhong.study.pdf.company.pojo.DaoDictConvertDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service(PdfDaoHandlerNameConstant.CO_INFO)
public class CoInfoDaoHandler extends DefaultPdfDaoHandler implements IPdfDaoHandler {

    /**
     * 需要转换的字典值
     */
    private static final List<DaoDictConvertDto> dictToConvertList = Arrays.asList(
            new DaoDictConvertDto("business", "m_business_nature")
    );


    // TODO PDF 替换数据源
    @Autowired
    private TestMockDaoSerivce testMockDaoSerivce;

    @Override
    protected String getDbName() {
        return PdfDaoDbNameConstant.CO_INFO;
    }

    @Override
    protected Object getDataFromDb(String memberNo) {
        return testMockDaoSerivce.getMemberCoInfo();
    }

}
