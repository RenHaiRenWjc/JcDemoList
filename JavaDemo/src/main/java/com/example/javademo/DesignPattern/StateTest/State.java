package com.example.javademo.DesignPattern.StateTest;

/**
 * ClassName: com.example.javademo.DesignPattern.StateTest
 * Description:
 * JcChen on 2020.04.26.15:30
 */
public enum State {
  SMALL(0), SUPER(1), FIRE(2), CAPE(3);
  private int value;

  private State(int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }


}
