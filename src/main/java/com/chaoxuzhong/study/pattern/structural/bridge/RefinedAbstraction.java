package com.chaoxuzhong.study.pattern.structural.bridge;

public class RefinedAbstraction extends Abstraction{

    public RefinedAbstraction(Implementor implementor) {
        super(implementor);
    }

    @Override
    public void operate() {
        implementor.operate();
        System.out.println("operate in sub absraction");
    }
}
