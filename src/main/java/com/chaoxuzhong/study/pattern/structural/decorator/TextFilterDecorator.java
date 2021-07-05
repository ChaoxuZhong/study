package com.chaoxuzhong.study.pattern.structural.decorator;

public class TextFilterDecorator implements TextNode{

    protected final TextNode node;

    public TextFilterDecorator(TextNode node) {
        this.node = node;
    }

    @Override
    public String getTest() {
        return node.getTest();
    }

    @Override
    public void setTest(String text) {
        node.setTest(text);
    }


}
