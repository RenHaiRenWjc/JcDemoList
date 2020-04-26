package com.example.javademo.DesignPattern.StateTest;

/**
 * ClassName: com.example.javademo.DesignPattern.StateTest
 * Description:
 * JcChen on 2020.04.26.16:55
 */
public class SmallMario implements IMario {
  private SmallMario() {
  }

  private static class SingleHolder {
    private static final SmallMario sInstance = new SmallMario();
  }

  public static SmallMario getInstance() {
    return SingleHolder.sInstance;
  }

  @Override
  public State getName() {
    return State.SMALL;
  }

  @Override
  public void obtainMushRoom(MarioStateMachine03 stateMachine) {
    stateMachine.setCurrentState(new SuperMario(stateMachine));
    stateMachine.setScore(stateMachine.getScore() + 100);
  }

  @Override
  public void obtainCape(MarioStateMachine03 stateMachine) {

  }

  @Override
  public void obtainFireFlower(MarioStateMachine03 stateMachine) {

  }

  @Override
  public void meetMonster(MarioStateMachine03 stateMachine) {

  }
}
