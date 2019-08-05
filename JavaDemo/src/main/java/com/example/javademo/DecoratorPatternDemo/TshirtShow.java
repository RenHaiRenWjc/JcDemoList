package com.example.javademo.DecoratorPatternDemo;

/**
 * ClassName:com.example.javademo.DecoratorPatternDemo
 * Description:
 * JcChen on 2019/8/6 0:50
 */
public class TshirtShow extends DecoratorPerson {


    public TshirtShow(Person person) {
        super(person);
    }

    @Override
    void show() {
        super.show();
        System.out.println("T-shirt show");
    }
}
