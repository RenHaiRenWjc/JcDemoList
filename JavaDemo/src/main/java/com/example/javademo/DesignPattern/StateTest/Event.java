package com.example.javademo.DesignPattern.StateTest;

/**
 * ClassName: com.example.javademo.DesignPattern.StateTest
 * Description:
 * JcChen on 2020.04.26.15:38
 */
public enum Event {
  GOT_MUSHROOM(0),
  GOT_CAPE(1),
  GOT_FIRE(2),
  MET_MONSTER(3);

  private int value;

  private Event(int value) {
    this.value = value;
  }

  public int getValue() {
    return this.value;
  }
}
