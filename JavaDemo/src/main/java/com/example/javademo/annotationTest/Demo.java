package com.example.javademo.annotationTest;

/**
 * ClassName: com.example.javademo.annotationTest
 * Description:
 * JcChen on 2020.04.07.15:31
 */
public class Demo {
  public static void main(String[] args) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        AnnotionProcessor mAnnotionPro = new AnnotionProcessor();
        AnnotationTest getValue = mAnnotionPro.create(AnnotationTest.class);
        System.out.println(getValue.getBd());
      }
    }).start();

  }
}
