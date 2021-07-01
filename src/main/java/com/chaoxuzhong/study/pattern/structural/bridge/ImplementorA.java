package com.chaoxuzhong.study.pattern.structural.bridge;

public class ImplementorA implements Implementor {

    @Override
    public void operate() {
        System.out.printf("operate in implementorA");

    }
}
