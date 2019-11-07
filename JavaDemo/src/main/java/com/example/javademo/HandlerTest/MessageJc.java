package com.example.javademo.HandlerTest;

/**
 * ClassName:com.example.javademo.HandlerTest
 * Description:
 * JcChen on 2019/11/6 22:06
 */
public class MessageJc {
    HandlerJc target;
    Object obj;

    MessageJc(Object obj) {
        this.obj = obj;
    }

    public String toString() {
        return obj.toString();
    }
}
