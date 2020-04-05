package com.example.javademo.DesignPattern.BuilderTest;

/**
 * ClassName: com.example.javademo.DesignPattern.BuilderTest
 * Description:产品类
 * JcChen on 2020.04.06.01:54
 */
public class Computer02 {
  private String mCpu;
  private String mMainboard;
  private String mRam;

  public void setmCpu(String mCpu) {
    this.mCpu = mCpu;
  }

  public void setmMainboard(String mMainboard) {
    this.mMainboard = mMainboard;
  }

  public void setmRam(String mRam) {
    this.mRam = mRam;
  }
}
