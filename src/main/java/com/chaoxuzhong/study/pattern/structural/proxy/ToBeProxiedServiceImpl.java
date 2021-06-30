package com.chaoxuzhong.study.pattern.structural.proxy;

public class ToBeProxiedServiceImpl implements ToBeProxiedService {

    @Override
    public void method1() {
        System.out.println("real execute method1 \n");
    }
}
