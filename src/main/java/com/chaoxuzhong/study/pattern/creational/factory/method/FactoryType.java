package com.chaoxuzhong.study.pattern.creational.factory.method;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FactoryType {
    one(new FactoryOneImpl()),
    two(new FactoryTwoImpl());

    private Factory factory;

}
