package com.example.javademo.DesignPattern.BuilderTest;

import com.example.javademo.DesignPattern.FactoryTest.Computer;

/**
 * ClassName: com.example.javademo.DesignPattern.BuilderTest
 * Description: 实现了抽象 builder类
 * JcChen on 2020.04.06.01:58
 */
public class JcComputerBuilder extends Builder01{

private Computer02 mComputor=new Computer02();

  @Override
  public void buildCpu(String cpu) {
    mComputor.setmCpu(cpu);
  }

  @Override
  public void buildMainboard(String mainboard) {
    mComputor.setmMainboard(mainboard);
  }

  @Override
  public void buildRam(String ram) {
    mComputor.setmRam(ram);
  }

  @Override
  public Computer02 create() {
    return mComputor;
  }
}
