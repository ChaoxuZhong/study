package com.chaoxuzhong.study.pattern.creational.factory.simple;

public class ConcreteProductOne implements Product{

    private final String DESC = "this is product one ";

    @Override
    public String getDescription() {
        return DESC;
    }
}
