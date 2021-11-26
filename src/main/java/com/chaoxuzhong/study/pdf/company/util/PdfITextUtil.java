package com.chaoxuzhong.study.pdf.company.util;

import cn.hutool.core.io.FileUtil;
import com.chaoxuzhong.study.pdf.company.pojo.PdfTemplate;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class PdfITextUtil {
    public static void mergePdf(List<String> toMergePdfPath, OutputStream outputPdfStream) throws IOException, DocumentException, InterruptedException {
//        OutputStream outputPdfStream = new FileOutputStream(outPath);
        Document document = new Document();
        PdfCopy copy = new PdfCopy(document, outputPdfStream);
        copy.setMergeFields();
        document.open();
        List<PdfReader> rs = new ArrayList<>();
        List<InputStream> iss = new ArrayList<>();
        for (String f : toMergePdfPath) {
            InputStream inputStream;
            // 模板文件读取
            if (f.startsWith(PdfTemplate.TEMPLATE_FILE_PATH_ROOT)) {
                ClassPathResource resource = new ClassPathResource(f);
                inputStream = resource.getInputStream();
            } else {
                // 临时文件读取
                inputStream = FileUtil.getInputStream(new File(f));
            }
            PdfReader r = new PdfReader(inputStream);
            rs.add(r);
            copy.addDocument(r);
        }
        copy.close();
        for (PdfReader r : rs) {
            r.close();
        }
        for (InputStream inputStream : iss) {
            inputStream.close();
        }
    }

}
