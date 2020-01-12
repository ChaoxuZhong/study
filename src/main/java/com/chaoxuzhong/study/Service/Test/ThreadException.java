package com.chaoxuzhong.study.Service.Test;

import com.chaoxuzhong.study.Service.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class ThreadException {

    private static Logger logger = LoggerFactory.getLogger(ThreadException.class);

    public static void main(String[] args) {
        ThreadService ts = new ThreadService();
        new Thread(() -> {
            try {
                ts.createException("线程1");
            } catch (Exception e) {
                logger.error("错误信息1：", e);
                e.printStackTrace();
            }

        }).start();
        new Thread(() -> {
            try {
                ts.createException("线程2");
            } catch (Exception e) {
                logger.error("错误信息2：", e);
                e.printStackTrace();
            }

        }).start();
    }


}
