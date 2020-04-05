package com.example.javademo.DesignPattern.DIContainerTest;

/**
 * ClassName: com.example.javademo.DesignPattern.DIContainerTest
 * Description:依赖 RedisCounter ，通过配置文件来创建该类
 * JcChen on 2020.04.05.21:09
 */
public class RateLimiter {
  private RedisCounter redisCounter;

  public RateLimiter(RedisCounter redisCounter) {
    this.redisCounter = redisCounter;
  }

  public void test() {
    System.out.println("Hello World!");
  }
}



