package com.example.javademo.DesignPattern.ResponseTest;

/**
 * ClassName: com.example.javademo.DesignPattern.ResponseTest
 * Description:
 * JcChen on 2020.04.17.14:46
 */
public class HandlerA extends Handler {
  @Override
  public void handle() {
    boolean handle = false;
    // todo
    if (!handle && successor != null) {
      successor.handle();
    }
  }
}
