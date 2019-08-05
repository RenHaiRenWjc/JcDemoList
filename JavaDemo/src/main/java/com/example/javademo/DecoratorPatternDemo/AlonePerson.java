package com.example.javademo.DecoratorPatternDemo;

/**
 * ClassName:com.example.javademo.DecoratorPatternDemo
 * Description: 具体的组件对象，实现组件对象接口，通常就是被装饰的原始对象。就对这个对象添加功能。
 * JcChen on 2019/8/6 1:00
 */
public class AlonePerson extends Person {
    private String name;

    public AlonePerson(String name) {
        super(name);
    }


    @Override
    void show() {
        System.out.println("---Alone Person");
    }
}
