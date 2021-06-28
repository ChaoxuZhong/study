package com.chaoxuzhong.study.pattern.creational.factory.method;

public class App {
    public static void main(String[] args) {
        Factory factory = FactoryType.one.getFactory();
        System.out.printf(factory.createProduct().getDescription());;
    }
}
