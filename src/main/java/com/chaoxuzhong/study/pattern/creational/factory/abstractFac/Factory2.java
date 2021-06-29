package com.chaoxuzhong.study.pattern.creational.factory.abstractFac;

public class Factory2 implements Factory{
    @Override
    public ProductB getProductB() {
        return new ProductB2();
    }

    @Override
    public ProductA getProductA() {
        return new ProductA2();
    }
}
