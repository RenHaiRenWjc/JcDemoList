package com.example.javademo.DecoratorPatternDemo;

/**
 * ClassName:com.example.javademo.DecoratorPatternDemo
 * Description:
 * JcChen on 2019/8/6 0:57
 */
public class ShoeShow extends DecoratorPerson {
    public ShoeShow(Person person) {
        super(person);
    }

    @Override
    void show() {
        super.show();
        System.out.println("shoe show");
    }
}
