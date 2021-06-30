package com.chaoxuzhong.study.pattern.structural.proxy;

public class App {
    public static void main(String[] args) {
        AdditionalFeatureProxy featureProxy = new AdditionalFeatureProxy(new FeaturesAdd());
        ToBeProxiedService toBeProxiedService = (ToBeProxiedService) featureProxy.createProxy(new ToBeProxiedServiceImpl());
        toBeProxiedService.method1();

    }
}
