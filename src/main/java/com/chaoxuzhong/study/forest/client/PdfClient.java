package com.chaoxuzhong.study.forest.client;

import com.dtflys.forest.Forest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PdfClient {
    private static PdfClient instance;

    private PdfClient() {

    }

    @Value("${proxy.pdf-url:unknown}")
    private String preurl;

    @PostConstruct
    public void init() {
        instance = this;
    }


    /**
     * 调用消息服务
     *
     * @param body         请求体
     * @param interfaceUrl 项目接口路径
     * @return
     */
    public String invoke(Object body, String interfaceUrl) {
        return (String) Forest
                .post(instance.preurl + interfaceUrl)
                .contentTypeJson()
                .addBody(body)
                .execute();
    }


}
