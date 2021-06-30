package com.chaoxuzhong.study.Service.async;

import com.alibaba.fastjson.JSON;
import com.chaoxuzhong.study.bean.GreetingInnerObject;
import com.chaoxuzhong.study.bean.GreetingPostBody;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {
    private Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);

    @Async
    @Override
    public Future<String> doTask01() throws InterruptedException {
        logger.info("doTask01 start");
        Thread.sleep(10000);
        logger.info("truely Execute");
        Object greeting = new RestTemplate().getForObject("http://localhost:8080/greeting?name=myTest async"
                , Object.class);
        logger.info("异步调用返回："+JSON.toJSONString(greeting));

        return new AsyncResult <> ("任务一结束");
    }
    @Async
    @Override
    public Future<String> doTask02() throws InterruptedException {
        logger.info("doTask02 start");
        Thread.sleep(10000);
        logger.info("truely Execute  02");

        GreetingPostBody greetingPostBody = new GreetingPostBody();
        greetingPostBody.setGreetingSource("02GreetingSource");
        greetingPostBody.setGreetingTarget("02greetingTarget");
        GreetingInnerObject greetingInnerObject = new GreetingInnerObject();
        greetingInnerObject.setGreetingInnerStr1("innerString01");
        greetingInnerObject.setGreetingInnerStr2("innerString02");
        greetingPostBody.setGreetingInnerObject(greetingInnerObject);

        String postBody = JSON.toJSONString(greetingPostBody);
        logger.info("postBody = {}", postBody);
        Object greeting = new RestTemplate().postForObject("http://localhost:8080/greetingpostbody"
                ,postBody, Object.class);
                logger.info("greetingpostbody异步调用返回：" + JSON.toJSONString(greeting));
        Object greeting1 = new RestTemplate().postForObject("http://localhost:8080/greetingpoststring"
                ,postBody, Object.class);
        logger.info("greetingpoststring异步调用返回：" + JSON.toJSONString(greeting1));
        doTask01();
        return new AsyncResult <> ("任务er结束");
    }

    @Async
    @Override
    public Future<String> doTask03() {
        logger.info("doTask03 start");
        return new AsyncResult <> ("任san结束");
    }
}
