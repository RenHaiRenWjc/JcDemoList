package com.example.javademo.DesignPattern.ResponseTest;

/**
 * ClassName: com.example.javademo.DesignPattern.ResponseTest
 * Description:
 * JcChen on 2020.04.17.14:48
 */
public class HandleChain {
  private Handler head = null;
  private Handler tail = null;

  public void addHandler(Handler handler) {
    handler.setSuccessor(null);//应该每次进来都默认为null的
    if (head == null) {
      head = handler;
      tail = handler;
      return;
    }
    tail.setSuccessor(handler);//其实，这里就是head持有下一个handler
    tail = handler;
  }

  public void handle() {
    if (head != null) {
      head.handle();
    }
  }


}
