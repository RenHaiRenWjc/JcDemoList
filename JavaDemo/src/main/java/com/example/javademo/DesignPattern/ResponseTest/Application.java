package com.example.javademo.DesignPattern.ResponseTest;


/**
 * ClassName: com.example.javademo.DesignPattern.ResponseTest
 * Description:
 * JcChen on 2020.04.17.14:51
 */
public class Application {
  public static void main(String[] args) {
    HandleChain handlerChain = new HandleChain();
    handlerChain.addHandler(new HandlerA());
    handlerChain.addHandler(new HandlerB());
    handlerChain.handle();
  }
}
