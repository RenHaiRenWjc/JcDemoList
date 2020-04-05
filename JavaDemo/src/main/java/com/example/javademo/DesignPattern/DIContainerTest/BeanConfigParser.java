package com.example.javademo.DesignPattern.DIContainerTest;

import java.io.InputStream;
import java.util.List;

/**
 * ClassName: com.example.javademo.DesignPattern.DIContainerTest
 * Description:
 * JcChen on 2020.04.05.21:37
 */
public interface BeanConfigParser {
  List parse(InputStream inputStream);

  List parse(String configContent);
}
