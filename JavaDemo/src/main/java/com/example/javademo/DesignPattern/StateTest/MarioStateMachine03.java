package com.example.javademo.DesignPattern.StateTest;

/**
 * ClassName: com.example.javademo.DesignPattern.StateTest
 * Description: 状态模式
 * JcChen on 2020.04.26.17:01
 */
public class MarioStateMachine03 {
  private int score;
  private IMario currentState; // 当前的状态

  public MarioStateMachine03() {
    this.score = 0;
    this.currentState = SmallMario.getInstance();
  }

  public void obtainMushRoom() {
    this.currentState.obtainMushRoom(this);
  }

  public void obtainCape() {
    this.currentState.obtainCape(this);
  }

  public void obtainFireFlower() {
    this.currentState.obtainFireFlower(this);
  }

  public void meetMonster() {
    this.currentState.meetMonster(this);
  }

  public int getScore() {
    return this.score;
  }

  public State getCurrentState() {
    return this.currentState.getName();
  }

  public void setScore(int score) {
    this.score = score;
  }

  public void setCurrentState(IMario currentState) {  //更新当前的状态
    this.currentState = currentState;
  }
}
