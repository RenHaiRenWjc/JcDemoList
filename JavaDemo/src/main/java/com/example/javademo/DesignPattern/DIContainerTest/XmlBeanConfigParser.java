package com.example.javademo.DesignPattern.DIContainerTest;

import java.beans.BeanDescriptor;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: com.example.javademo.DesignPattern.DIContainerTest
 * Description:这个类是把配置文件解析成 BeanDefinitaion类
 * JcChen on 2020.04.05.21:37
 */
class XmlBeanConfigParser implements BeanConfigParser {
  @Override
  public List<BeanDefinition> parse(InputStream inputStream) {
    String content = null;
    return parse(content);
  }

  @Override
  public List<BeanDefinition> parse(String configContent) {
    List<BeanDefinition> beanDefinitions = new ArrayList<>();
    // TODO:...
    return beanDefinitions;
  }
}
