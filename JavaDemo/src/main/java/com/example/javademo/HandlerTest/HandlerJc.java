package com.example.javademo.HandlerTest;

/**
 * ClassName:com.example.javademo.HandlerTest
 * Description:
 * JcChen on 2019/11/6 21:47
 */
public class HandlerJc {

    private final LooperJc mLooper;
    final MessageQueueJc mQueue;

    public HandlerJc() {
        mLooper = LooperJc.myLooper();
        mQueue = mLooper.mQueue;
    }

    // 接收消息
    public void sendMessage(MessageJc messageJc) {
        enqueueMessage(messageJc);
    }

    private void enqueueMessage(MessageJc messageJc) {
        messageJc.target = this;
        mQueue.enqueueMessage(messageJc);
    }

    // 分发消息
    public void dispatchMessage(MessageJc messageJc) {
        handleMessage(messageJc);
    }

    // 最少知识原则
    public void handleMessage(MessageJc messageJc) {
    }


}
