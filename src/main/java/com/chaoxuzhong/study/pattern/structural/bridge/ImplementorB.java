package com.chaoxuzhong.study.pattern.structural.bridge;

public class ImplementorB implements Implementor{
    @Override
    public void operate() {
        System.out.println("operate in ImplementorB");

    }
}
