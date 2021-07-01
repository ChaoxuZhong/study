package com.chaoxuzhong.study.pattern.structural.bridge;

public class BridgeApp {
    public static void main(String[] args) {
        RefinedAbstraction refinedAbstraction = new RefinedAbstraction(new ImplementorA());
        refinedAbstraction.operate();

    }
}
