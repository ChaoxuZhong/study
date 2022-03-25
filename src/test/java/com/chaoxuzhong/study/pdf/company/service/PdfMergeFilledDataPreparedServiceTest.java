package com.chaoxuzhong.study.pdf.company.service;

import com.chaoxuzhong.BaseAppTest;
import com.chaoxuzhong.study.pdf.company.config.FileVoName;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PdfMergeFilledDataPreparedServiceTest extends BaseAppTest {

    @Autowired
    private PdfMergeFilledDataPreparedService pdfMergeFilledDataPreparedService;

    @Autowired
    private PdfMergeFilledService pdfMergeFilledService;

    @Test
    public void fillPdfAndSave() {
//        pdfMergeFilledService.fillPdfAndSave(FileVoName.NONSIGN_ADEN_CUST_INFO, "anyMemberNo");
//        pdfMergeFilledService.fillPdfAndSave(FileVoName.NONSIGN_AML_QUESTIONNAIRE, "anyMemberNo");
        pdfMergeFilledService.fillPdfAndSave(FileVoName.NONSING_FW8BEN, "anyMemberNo");
    }
}