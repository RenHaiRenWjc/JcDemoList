package com.example.javademo.mathDemo;

/**
 * ClassName:com.example.javademo.mathDemo
 * Description:
 * author:wjc on 2019/7/31 11:40
 */

public class MathDemo {

    public static void main(String[] args) {
        testMathFloor("85.99999");
        testMathFloor("85.60009");

        testMathRound("-89.55999");
        testMathRound("85.10009");

        testMathCeil("85.999999");
        testMathCeil("85.60009");
    }

    //取小于或等于 value 最大整数
    private static void testMathFloor(String value) {
        float v = (float) (Math.floor(Float.parseFloat(value)));
        System.out.println("testMathFloor v=" + v);
    }

    // 只关注小数点后第一位小数值
    // 大于或等于五全部加，小于五全不加
    private static void testMathRound(String value) {
        float v = (float) (Math.round(Float.parseFloat(value)));
        System.out.println("testMathRound v=" + v);
    }

    //取大于或等于value的最小整数
    private static void testMathCeil(String value) {
        float v = (float) (Math.ceil(Float.parseFloat(value)));
        System.out.println("testMathCeil v=" + v);
    }
}
