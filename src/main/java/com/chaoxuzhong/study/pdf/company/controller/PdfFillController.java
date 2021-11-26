package com.chaoxuzhong.study.pdf.company.controller;

import com.chaoxuzhong.study.pdf.company.config.FileVoName;
import com.chaoxuzhong.study.pdf.company.service.PdfMergeFilledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/pdf")
public class PdfFillController {

    @Autowired
    private PdfMergeFilledService pdfMergeFilledService;

    @GetMapping("/fill")
    public void fillPdf(@RequestParam("memberNo") String memberNo, @RequestParam("fileName") String fileName) {
        try {
            pdfMergeFilledService.fillPdf(FileVoName.NONSIGN_ADEN_CUST_INFO, "anyMemberNo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
