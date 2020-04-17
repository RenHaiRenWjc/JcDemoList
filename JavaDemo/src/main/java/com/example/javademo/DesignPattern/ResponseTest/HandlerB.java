package com.example.javademo.DesignPattern.ResponseTest;

/**
 * ClassName: com.example.javademo.DesignPattern.ResponseTest
 * Description:
 * JcChen on 2020.04.17.14:46
 */
public class HandlerB extends Handler {
  @Override
  public void handle() {
    boolean hanldle = false;
    // todo
    if (!hanldle && successor != null) {
      successor.handle();
    }
  }
}
