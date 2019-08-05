package com.example.javademo.DecoratorPatternDemo;

/**
 * ClassName:com.example.javademo.DecoratorPatternDemo
 * Description:
 * JcChen on 2019/8/6 0:57
 */
public class main {
    public static void main(String[] args) {
        AlonePerson alonePerson = new AlonePerson("JC");
        ShoeShow shoeShow = new ShoeShow(alonePerson);
        TshirtShow tshirtShow = new TshirtShow(shoeShow);
        tshirtShow.show();
    }
}
