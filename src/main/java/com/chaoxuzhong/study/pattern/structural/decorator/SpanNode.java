package com.chaoxuzhong.study.pattern.structural.decorator;

public class SpanNode implements TextNode {

    private String text;



    @Override
    public String getTest() {
        return "<span>" + text + "<span>";
    }

    @Override
    public void setTest(String text) {
        this.text = text;
    }


}
