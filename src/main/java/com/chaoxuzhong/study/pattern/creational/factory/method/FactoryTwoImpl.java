package com.chaoxuzhong.study.pattern.creational.factory.method;

public class FactoryTwoImpl implements Factory{
    @Override
    public Product createProduct() {
        return new ProductTwo();
    }

}
