package com.nightcat.other;

import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    public AtomicInteger inc = new AtomicInteger();

    public void increase() {
        inc.getAndIncrement();
    }

    public static void main(String[] args) throws InterruptedException {
        final Test test = new Test();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++)
                    test.increase();
            }).start();
        }
        Thread.sleep(5000);
        System.out.println("线程启动完成");
        System.out.println("activeCount: " + Thread.activeCount());
//        while (Thread.activeCount() > 1)  //保证前面的线程都执行完
//            Thread.yield();
        System.out.println(test.inc);
    }
}