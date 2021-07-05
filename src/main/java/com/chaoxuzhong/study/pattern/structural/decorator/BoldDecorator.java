package com.chaoxuzhong.study.pattern.structural.decorator;

public class BoldDecorator extends TextFilterDecorator {
    public BoldDecorator(TextNode node) {
        super(node);
    }

    @Override
    public String getTest() {
        return "<b>" + node.getTest() + "</b>";
    }


}
