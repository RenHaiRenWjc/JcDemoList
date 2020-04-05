package com.example.javademo.DesignPattern.BuilderTest;

/**
 * ClassName: com.example.javademo.DesignPattern.BuilderTest
 * Description:
 * JcChen on 2020.04.06.02:19
 */
public class Director {
  Builder01 mBuilder = null;

  public Director(Builder01 builder01) {
    mBuilder = builder01;
  }

  public Computer02 createComputer(String cpu, String mainboard, String ram) {
    this.mBuilder.buildCpu(cpu);
    this.mBuilder.buildMainboard(mainboard);
    this.mBuilder.buildRam(ram);
    return mBuilder.create();
  }

}
