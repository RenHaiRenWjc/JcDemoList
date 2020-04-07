package com.example.javademo.annotationTest;

/**
 * ClassName:com.example.javademo.annotationTest
 * Description:
 * JcChen on 2019/7/6 17:47
 */
public interface AnnotationTest {
  @Get(value = "http://baidu.com")
  public String getBd();
}
