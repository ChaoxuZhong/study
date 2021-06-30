package com.chaoxuzhong.study.TestController;

import com.chaoxuzhong.study.Service.async.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Async")
@Slf4j
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    AsyncService asyncService;

    @RequestMapping("/doTask")
    public String doTask() {
        System.out.println("doTask_____________________");
//        try {
//            Future<String> future01 = asyncService.doTask01();
//
//            Future<String> future02 = asyncService.doTask02();
//
//            Future<String> future03 = asyncService.doTask03();
//            while (true) {
//                if (future01.isDone() && future02.isDone() && future03.isDone()) {
//                    return "done";com.chaoxuzhong.study.JvmSandBox
//                }
//            }
//
//        } catch (Exception e) {
//            logger.error("doTask Exception ==>{}", e);
//            return "error" + e.getStackTrace();
//        } finally {
//            return "finally";
//        }

//        new Thread(() -> {
            try {
                asyncService.doTask02();
            } catch (Exception e) {
                e.printStackTrace();
            }
//        }).start();
        return "finish";
    }


}
