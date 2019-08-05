package com.example.javademo.DecoratorPatternDemo;

/**
 * ClassName:com.example.javademo.DecoratorPatternDemo
 * Description:所有装饰器的抽象父类，需要定义一个与组件接口一致的接口，内部持有一个 person 对象，就是持有一个被装饰的对象
 * JcChen on 2019/8/6 0:51
 */
public class DecoratorPerson extends Person {
    Person person;

    public DecoratorPerson(Person person) {
        this.person = person;
    }

    @Override
    void show() {
        person.show();
    }
}
