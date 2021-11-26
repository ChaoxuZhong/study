package com.chaoxuzhong.study.pdf.company.service;

import cn.hutool.core.lang.Pair;
import com.chaoxuzhong.study.pdf.company.config.FileVoName;
import com.chaoxuzhong.study.pdf.company.pojo.TmpPdfPath;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

@Service
public class PdfMergeFilledService {

    @Autowired
    private PdfMergeFilledDataPreparedService preparedFilledService;

    private static final HashMap EMPTY_HASH_MAP = new HashMap();

    /**
     * 填充PDF并上传
     *
     * @param voEnums
     * @param memberNo
     * @return
     */
    public Pair<Boolean, String> fillPdfAndSave(FileVoName voEnums, String memberNo) {
        return preparedFilledService.fillPdfAndSave(voEnums, memberNo, EMPTY_HASH_MAP);
    }


    /**
     * 填充PDF 生成pdf流
     *
     * @param voEnums
     * @param custNo
     * @param outputStream
     * @return
     * @throws IOException
     * @throws DocumentException
     * @throws InterruptedException
     */
    public TmpPdfPath fillAndMergePdf(FileVoName voEnums, String custNo, OutputStream outputStream) throws IOException, DocumentException, InterruptedException {
        return preparedFilledService.fillAndMergePdf(voEnums, custNo, EMPTY_HASH_MAP, outputStream);
    }


    public TmpPdfPath fillPdf(FileVoName voEnums, String memberNo) throws IOException {
        return preparedFilledService.fillPdf(voEnums, memberNo, EMPTY_HASH_MAP);
    }

}
