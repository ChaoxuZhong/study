package com.chaoxuzhong.study.Service.Bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class FastJsonBean {
    private int age;
    private String name;
    private String gender;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date date;


    public FastJsonBean(int age, String name, String gender, Date date) {
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.date = date;
    }
}
