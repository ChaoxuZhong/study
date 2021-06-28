package com.chaoxuzhong.study.pattern.creational.factory.simple;

public class App {
    public static void main(String[] args) {
        System.out.printf(ProductSimpleFactory.getProduct(ProductType.one).getDescription());
        System.out.printf(ProductSimpleFactory.getProduct(ProductType.two).getDescription());
    }
}
