package com.chaoxuzhong.study.pattern.creational.factory.simple;

public class ConcreteProductTwo implements Product {
    private final String DESC = "this is product two";

    @Override
    public String getDescription() {
        return DESC;
    }
}
