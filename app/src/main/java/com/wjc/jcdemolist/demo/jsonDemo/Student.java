package com.wjc.jcdemolist.demo.jsonDemo;

/**
 * ClassName:com.wjc.jcdemolist.demo.jsonDemo
 * Description:
 * JcChen on 2019/7/31 22:48
 */
public class Student {
    private String name;
    private String sex;
    private int age;
    private Course course;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
