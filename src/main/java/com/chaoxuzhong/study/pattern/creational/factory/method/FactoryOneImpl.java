package com.chaoxuzhong.study.pattern.creational.factory.method;

public class FactoryOneImpl implements Factory{

    @Override
    public Product createProduct() {
        return new ProductOne();
    }

}
