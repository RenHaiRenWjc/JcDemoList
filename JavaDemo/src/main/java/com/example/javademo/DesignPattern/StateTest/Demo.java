package com.example.javademo.DesignPattern.StateTest;

/**
 * ClassName: com.example.javademo.DesignPattern.StateTest
 * Description:
 * JcChen on 2020.04.26.15:34
 */
public class Demo {
  public static void main(String[] args) {
    MarioStateMachine mario = new MarioStateMachine();
    mario.obtainMushRoom();
    int score = mario.getScore();
    State state = mario.getCurrentState();
    System.out.println("mario score: " + score + "; state: " + state);
  }
}
