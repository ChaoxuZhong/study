package com.chaoxuzhong.study.concurrent;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

    public static final LockSupportTest blocker = new LockSupportTest();

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.printf("A COME IN \n");
            LockSupport.park();
            System.out.printf("A WAKE UP IN \n");
        }).start();


        new Thread(() -> {
            System.out.printf("B COME IN \n");
            LockSupport.unpark(Thread.currentThread());
            System.out.printf("B WAKE UP IN \n");
        }).start();
    }


}
