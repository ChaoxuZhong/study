package com.chaoxuzhong.study.pattern.creational.factory.simple;

public class ProductSimpleFactory {

    public static Product getProduct(ProductType type) {
        return type.getConstructor().get();
    }

}
