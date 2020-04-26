package com.example.javademo.DesignPattern.StateTest;

/**
 * ClassName: com.example.javademo.DesignPattern.StateTest
 * Description:
 * JcChen on 2020.04.26.16:54
 */
public interface IMario {
  State getName(); //以下是定义的事件

  void obtainMushRoom(MarioStateMachine03 stateMachine);

  void obtainCape(MarioStateMachine03 stateMachine);

  void obtainFireFlower(MarioStateMachine03 stateMachine);

  void meetMonster(MarioStateMachine03 stateMachine);
}
