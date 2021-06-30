package com.chaoxuzhong.study.pattern.structural.proxy;

/**
 * 代理附加功能类
 */
public class FeaturesAdd {
    String beforeProxyMethod() {
        System.out.printf("do something before proxy method \n");
        return "do something before proxy method \n";
    }

    String afterProxyMethod() {
        System.out.printf("do something after proxy method \n");
        return "do something after proxy method \n";
    }

}
