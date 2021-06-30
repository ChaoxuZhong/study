package com.chaoxuzhong.study.pattern.creational.builder;

public class App {

    public static void main(String[] args) {
        BuilderClass builderClass = null;
        try {
            builderClass = new BuilderClass.Builder().setOdd(2).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            builderClass = new BuilderClass.Builder().setOdd(1).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            builderClass = new BuilderClass.Builder().setOdd(1).setBDependsOnA("B").build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            builderClass = new BuilderClass.Builder().setOdd(1).setBDependsOnA("B").setA("A").build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            builderClass = new BuilderClass.Builder().setOdd(1).setBDependsOnA("B").setA("A").setRequired("required").build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(builderClass);
    }
}
