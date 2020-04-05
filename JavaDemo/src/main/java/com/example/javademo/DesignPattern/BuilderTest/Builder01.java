package com.example.javademo.DesignPattern.BuilderTest;

/**
 * ClassName: com.example.javademo.DesignPattern.BuilderTest
 * Description: 抽象builder类
 * JcChen on 2020.04.06.01:56
 */
public abstract class Builder01 {
  public abstract void buildCpu(String cpu);
  public abstract void buildMainboard(String mainboard);
  public abstract void buildRam(String ram);
  public abstract Computer02 create();

}
