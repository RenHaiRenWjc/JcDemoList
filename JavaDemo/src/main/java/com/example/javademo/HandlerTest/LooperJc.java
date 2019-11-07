package com.example.javademo.HandlerTest;

/**
 * ClassName:com.example.javademo.HandlerTest
 * Description:
 * JcChen on 2019/11/6 21:57
 */
public class LooperJc {

    final MessageQueueJc mQueue;
    private static ThreadLocal<LooperJc> sThreadLocal = new ThreadLocal<>();

    private LooperJc() {
        this.mQueue = new MessageQueueJc();
    }

    public static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new LooperJc());
    }

    public static LooperJc myLooper() {
        return sThreadLocal.get();
    }

    public static void loop() {
        // 保证 当前线程的looper唯一，从而保证 queue 唯一，message 唯一
        LooperJc looperJc = myLooper();
        MessageQueueJc messageQueueJc = looperJc.mQueue;


        for (; ; ) {
            MessageJc messageJc = messageQueueJc.next();
            if (messageJc != null) {
                messageJc.target.dispatchMessage(messageJc);
            }
        }
    }
}
