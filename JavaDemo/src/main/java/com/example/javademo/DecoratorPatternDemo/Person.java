package com.example.javademo.DecoratorPatternDemo;

/**
 * ClassName:com.example.javademo.DecoratorPatternDemo
 * Description: 组件对象接口
 * JcChen on 2019/8/6 0:48
 */
public abstract class Person {
    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    abstract void show();
}
