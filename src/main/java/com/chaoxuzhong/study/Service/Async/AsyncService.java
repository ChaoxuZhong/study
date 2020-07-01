package com.chaoxuzhong.study.Service.Async;


import java.util.concurrent.Future;


public interface AsyncService {
    Future<String> doTask01() throws Exception;
    Future<String> doTask02() throws Exception;
    Future<String> doTask03() throws Exception;
}
