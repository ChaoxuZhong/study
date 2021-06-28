package com.chaoxuzhong.study.pattern.creational.factory.simple;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Supplier;

@AllArgsConstructor
@Getter

public enum ProductType {
    one(ConcreteProductOne::new),
    two(ConcreteProductTwo::new);

    private Supplier<Product> constructor;

}
