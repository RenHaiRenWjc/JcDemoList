package com.example.javademo.DesignPattern.FactoryTest;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: com.example.javademo.DesignPattern.FactoryTest
 * Description: 工厂类
 * JcChen on 2020.04.05.17:03
 */
public class ComputerFactory {

  // 第一种方法创建
  public static Computer createComputerFactory01(String type) {
    if ("hp".equals(type)) {
      return new HpComputer();
    } else if ("mac".equals(type)) {
      return new MacComputer();
    }
    return null;

  }

  // 第二种方式创建，复用 computer ，接受内存和创建对象时间
  private static Map<String, Computer> cacheComputer = new HashMap<>();

  static {
    cacheComputer.put("hp", new HpComputer());
    cacheComputer.put("mac", new MacComputer());
  }

  public static Computer createComputerFactory02(String type) {
    Computer computer = cacheComputer.get(type);
    return computer;
  }

}
