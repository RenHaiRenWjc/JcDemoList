package com.example.javademo.DesignPattern.BuilderTest;

/**
 * ClassName: com.example.javademo.DesignPattern.BuilderTest
 * Description:
 * JcChen on 2020.04.06.01:49
 */
public class Demo {
  public static void main(String[] args) {
    new Director(new JcComputerBuilder())
      .createComputer("i10-990", "A12", "jc DDR4");

    new ResourcePoolConfig.Builder()
      .setName("jc")
      .setMaxIdle(999)
      .setMaxTotal(8888)
      .setMinIdle(-11)// 报异常
      .build();
  }

}
