package com.chaoxuzhong.study.pattern.creational.factory.abstractFac;

public class App {
    public static void main(String[] args) {
        Factory factory1 = new Factory1();
        System.out.println(factory1.getProductA().getDescription());
        System.out.println(factory1.getProductB().getDescription());

        Factory factory2 = new Factory2();
        System.out.println(factory2.getProductA().getDescription());
        System.out.println(factory2.getProductB().getDescription());
    }
}
