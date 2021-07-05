package com.chaoxuzhong.study.pattern.structural.decorator;

import com.google.gson.Gson;

public class DecoratorApp {
    public static void main(String[] args) {
        TextNode node = new BoldDecorator(new ItalicDecorator(new SpanNode()));
        node.setTest("aaa");

        System.out.println(new Gson().toJson(node));
        System.out.printf(node.getTest());

    }
}
