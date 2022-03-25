package com.chaoxuzhong.study.forest.server;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/pdf")
@Slf4j
public class PdfController {

    @Autowired
    private RemotePdfDubboService pdfDubboService;

    @PostMapping("/pdf-to-image")
    public String pdfToImage(@RequestBody String body) throws IOException {

        log.info("开始pdf转图片");
        byte[] resBytes = pdfDubboService.pdfToImage(Base64.decode(body));
        return Base64.encode(resBytes);
    }

}
