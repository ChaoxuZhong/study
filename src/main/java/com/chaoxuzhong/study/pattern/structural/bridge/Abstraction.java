package com.chaoxuzhong.study.pattern.structural.bridge;

public abstract class Abstraction {
    protected Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operate();
}
