package com.example.javademo.DesignPattern.DIContainerTest;

/**
 * ClassName: com.example.javademo.DesignPattern.DIContainerTest
 * Description:通过配置文件来创建该类
 * JcChen on 2020.04.05.21:09
 */
public class RedisCounter {
  private String ipAddress;
  private int port;

  public RedisCounter(String ipAddress, int port) {
    this.ipAddress = ipAddress;
    this.port = port;
  }
}
