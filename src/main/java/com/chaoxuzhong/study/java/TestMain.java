package com.chaoxuzhong.study.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(TestMain.class);
        try {
            Integer j = 1;
            Object i = j;
            String aaa = (String) i;
        } catch (Exception e) {
            logger.error("1111==>{}",e);
        }

    }
}
