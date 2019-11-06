package com.example.javademo.ThreadDemo;

/**
 * ClassName:com.example.javademo
 * Description:实例锁
 * author:wjc on 2019/10/25 15:10
 */
public class ThreadTest implements Runnable {
    private int count;

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        ThreadTest threadTest2 = new ThreadTest();
        Thread thread1 = new Thread(threadTest, "test01");
        Thread thread2 = new Thread(threadTest2, "test02");
        thread1.start();
        thread2.start();
    }


    @Override
    public synchronized void run() {
        for (int i = 0; i < 5; i++) {
            count++;
            System.out.println("name=" + Thread.currentThread().getName() + ",count=" + count);
        }
    }
}
