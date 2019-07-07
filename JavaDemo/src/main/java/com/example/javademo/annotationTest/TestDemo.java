package com.example.javademo.annotationTest;

/**
 * ClassName:com.example.javademo.annotationTest
 * Description:
 * JcChen on 2019/7/6 17:47
 */
@Test(id = 9, msg = "hello world!")
public class TestDemo {

    public static void main(String[] args) {
        boolean hasAnnotation = TestDemo.class.isAnnotationPresent(Test.class);
        if (hasAnnotation) {
            Test testAnnotation = TestDemo.class.getAnnotation(Test.class);
            System.out.println("id=" + testAnnotation.id());
        }
    }
}
