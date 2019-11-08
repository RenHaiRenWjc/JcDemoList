package com.example.javademo.HandlerTest;

import java.awt.SystemColor;

/**
 * ClassName:com.example.javademo.HandlerTest
 * Description:
 * JcChen on 2019/11/7 7:39
 */
public class MainTest {
    public static void main(String[] args) {
        LooperJc.prepare();
        HandlerJc handlerJc = new HandlerJc() {
            @Override
            public void handleMessage(MessageJc messageJc) {
                super.handleMessage(messageJc);
                System.out.println("Thread id=" + Thread.currentThread().getName() + ",obj=" + messageJc.obj);
            }
        };

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                while (i < 10) {
//                    i++;
//                    MessageJc messageJc = new MessageJc("task:" + i);
//                    System.out.println("---Thread id=" + Thread.currentThread().getName() + ",task=" + i);
//                    handlerJc.sendMessage(messageJc);
//                    try {
//                        Thread.sleep(0);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();

        for (int j = 0; j < 5; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = 0;
                    while (i < 10) {
                        i++;
                        MessageJc messageJc = new MessageJc("task:" + i);
                        System.out.println("---Thread id=" + Thread.currentThread().getName() + ",task=" + i);
                        handlerJc.sendMessage(messageJc);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        LooperJc.loop();
    }


}
