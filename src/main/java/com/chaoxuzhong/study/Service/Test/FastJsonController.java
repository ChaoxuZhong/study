package com.chaoxuzhong.study.Service.Test;

import com.alibaba.fastjson.support.spring.FastJsonViewResponseBodyAdvice;
import com.chaoxuzhong.study.Service.Bean.FastJsonBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/fastJson")
public class FastJsonController {

    @RequestMapping("/getJson")
    public FastJsonBean getFastJson() {
        return new FastJsonBean(10,"name","man",new Date());


    }
}
