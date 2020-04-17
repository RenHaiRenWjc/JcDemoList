package com.example.javademo.DesignPattern.ResponseTest;

/**
 * ClassName: com.example.javademo.DesignPattern.ResponseTest
 * Description:
 * JcChen on 2020.04.17.14:44
 */
public abstract class Handler {
  protected Handler successor = null;

  public void setSuccessor(Handler successor) {
    this.successor = successor;
  }

  public final void handle() {
    boolean handle = doHandle();
    if (!handle && successor != null) {
      successor.handle();
    }
  }

  public abstract boolean doHandle();
}
