package com.chaoxuzhong.study.TestController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalExceptionController {
    @RequestMapping("/exception")
    public void generateException(){
        int a = 5 / 0;
    }
}
