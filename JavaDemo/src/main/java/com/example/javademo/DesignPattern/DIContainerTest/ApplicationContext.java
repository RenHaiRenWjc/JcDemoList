package com.example.javademo.DesignPattern.DIContainerTest;

/**
 * ClassName: com.example.javademo.DesignPattern.DIContainerTest
 * Description:
 * JcChen on 2020.04.05.21:28
 */
public interface ApplicationContext {
  Object getBean(String beanId);
}
