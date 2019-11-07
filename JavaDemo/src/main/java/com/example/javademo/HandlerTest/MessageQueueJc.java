package com.example.javademo.HandlerTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

/**
 * ClassName:com.example.javademo.HandlerTest
 * Description:
 * JcChen on 2019/11/6 22:00
 */
public class MessageQueueJc {

    BlockingQueue<MessageJc> queue = new ArrayBlockingQueue<>(10);

    // 根据时间排序，当队列慢时，阻塞，直到用户通过 next 取出消息
    // 当next 方法被调用，通知 messageQueue 可以进行消息的入队
    public void enqueueMessage(MessageJc messageJc) {
        queue.add(messageJc);
    }

    // 由 Looper.loop() 启动轮询，对 queue 进行轮询
    // 当消息达到执行时间就取出来
    // 当 message queue 为空时，队列阻塞，等消息队列调用 enqueueMessage 时，通知队列，可以取出消息，停止阻塞
    public MessageJc next() {
        try {
            MessageJc msg = queue.take();
            return msg;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
