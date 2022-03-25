package com.chaoxuzhong.study.forest.client;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;


@RestController
@RequestMapping("/pdf")
@Slf4j
public class PdfLoadClient {

    @Autowired
    private PdfClient pdfClient;


    @GetMapping("/preview")
    public void preview(HttpServletResponse response) {
        try (
                // 用于接收pdf转换的jpg流
                OutputStream outputStream = response.getOutputStream();
                // 用于接收生成的pdf流
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ) {
            response.setBufferSize(10 * 1024 * 1024);
            File file = new File("/Users/chaoxu/Documents/Code/Git/study/src/main/resources/pdf/template/AML_Questionnaire.pdf");
            byte[] pdfByteArray = FileUtil.readBytes(file);
            String base64 = Base64.encode(pdfByteArray);
            String picOutput = pdfClient.invoke(base64, "/pdf/pdf-to-image");
            outputStream.write(Base64.decode(picOutput));
            response.setContentType("image/jpeg;charset=utf-8");
        } catch (Exception e) {
            log.error("preview ERROR==>", e);
        }
        return;
    }

}
