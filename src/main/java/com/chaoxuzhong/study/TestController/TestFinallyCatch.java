package com.chaoxuzhong.study.TestController;

public class TestFinallyCatch {
    public static void main(String[] args) {
        try{
            throw new RuntimeException("1");
        }catch (Exception e){
            throw new RuntimeException("2");
        }finally {
            System.out.printf("123");
        }
    }
}
