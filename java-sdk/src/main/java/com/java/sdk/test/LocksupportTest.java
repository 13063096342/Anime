package com.java.sdk.test;

import java.util.concurrent.locks.LockSupport;

/**
 * 交替打印A1B2C3.....
 *
 * @author chenfh
 * @date 2021-07-26 10:01
 */
public class LocksupportTest {
    public static Thread thread1 = null;
    public static Thread thread2 = null;
    public static Thread thread3 = null;

    public static char[] LETTER = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

    public static void main(String[] args) {
        thread1 = new Thread(() -> {
            for (int i = 1; i <= 7; i++) {
                System.out.println("t1 print:" + "I ");
                LockSupport.unpark(thread2);
                LockSupport.park();
            }
        });

        thread2 = new Thread(() -> {
            while(true){
                LockSupport.park();
                System.out.println("t2 print:" + " A");
                LockSupport.unpark(thread3);
            }
        });

        thread3 = new Thread(() -> {
            while(true){
                LockSupport.park();
                System.out.println("t3 print:" + "L");
                LockSupport.unpark(thread1);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }


}
