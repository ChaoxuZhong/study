package com.chaoxuzhong.study.TestController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自动取值控制层
 */
@RestController
@EnableConfigurationProperties
public class ValueController {
    @Value("${value.value1}")
    private String value1;
    @Value("${value.value2}")
    private String value2;

    @RequestMapping("/showValue")
    public String showValue(){
        return value1.concat(value2).concat("我是新加的尾巴的的的");
    }
}
