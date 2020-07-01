package com.chaoxuzhong.study.Bean;

public class GreetingPostBody {
    private String greetingSource;
    private String greetingTarget;
    private GreetingInnerObject greetingInnerObject;

    public String getGreetingSource() {
        return greetingSource;
    }

    public void setGreetingSource(String greetingSource) {
        this.greetingSource = greetingSource;
    }

    public String getGreetingTarget() {
        return greetingTarget;
    }

    public void setGreetingTarget(String greetingTarget) {
        this.greetingTarget = greetingTarget;
    }

    public GreetingInnerObject getGreetingInnerObject() {
        return greetingInnerObject;
    }

    public void setGreetingInnerObject(GreetingInnerObject greetingInnerObject) {
        this.greetingInnerObject = greetingInnerObject;
    }
}
