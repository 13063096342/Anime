package com.java.sdk.test;

import java.util.concurrent.locks.LockSupport;

/**
 * @author chenfh
 * @date 2021-07-14 17:52
 */
public class NumTest {
    static Thread thread1 = null;
    static Thread thread2 = null;

    public static void main(String[] args) {

        thread1 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                System.out.println("线程1打印：" + i * 2);
                //
                LockSupport.unpark(thread2);
                LockSupport.park(thread2);
            }
        });

        thread2 = new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                //线程被阻塞时是被谁阻塞的  thread2被thread1阻塞了
                LockSupport.park(thread1);
                System.out.println("线程2打印：" + (i * 2 + 1));
                //唤起thread1
                LockSupport.unpark(thread1);
            }
        });

        thread2.start();
        thread1.start();

    }
}
