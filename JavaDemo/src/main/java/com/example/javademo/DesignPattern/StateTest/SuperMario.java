package com.example.javademo.DesignPattern.StateTest;

/**
 * ClassName: com.example.javademo.DesignPattern.StateTest
 * Description:
 * JcChen on 2020.04.26.16:57
 */
public class SuperMario implements IMario {
  private MarioStateMachine03 stateMachine;

  public SuperMario(MarioStateMachine03 stateMachine) {
    this.stateMachine = stateMachine;
  }

  @Override
  public State getName() {
    return State.SUPER;
  }

  @Override
  public void obtainMushRoom(MarioStateMachine03 stateMachine) {

  }

  @Override
  public void obtainCape(MarioStateMachine03 stateMachine) {

  }

  @Override
  public void obtainFireFlower(MarioStateMachine03 stateMachine) {

  }

  @Override
  public void meetMonster(MarioStateMachine03 stateMachine) {
    stateMachine.setCurrentState(SmallMario.getInstance());
    stateMachine.setScore(stateMachine.getScore() - 100);
  }
}
