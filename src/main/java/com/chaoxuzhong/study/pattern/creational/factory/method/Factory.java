package com.chaoxuzhong.study.pattern.creational.factory.method;

public interface Factory {

    default Factory getInstance(FactoryType type){
        return type.getFactory();
    }

    Product createProduct();
}
