package com.chaoxuzhong.study.pattern.creational.factory.abstractFac;

public class Factory1 implements Factory{
    @Override
    public ProductB getProductB() {
        return new ProductB1();
    }

    @Override
    public ProductA getProductA() {
        return new ProductA1();
    }

}
