package com.chaoxuzhong.study.pattern.structural.decorator;

public class ItalicDecorator extends  TextFilterDecorator{
    public ItalicDecorator(TextNode node) {
        super(node);
    }

    @Override
    public String getTest() {
        return "<i>" + node.getTest() + "</i>";
    }


}
