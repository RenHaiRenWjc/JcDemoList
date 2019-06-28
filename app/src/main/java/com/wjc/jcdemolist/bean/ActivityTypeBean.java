package com.wjc.jcdemolist.bean;

/**
 * ClassName:com.example.wjc.myapplication
 * Description:
 * JcChen on 2019/6/16 10:55
 */
public class ActivityTypeBean {
    private String name;
    private Class<?> intentClass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<?> getIntentClass() {
        return intentClass;
    }

    public void setIntentClass(Class<?> intentClass) {
        this.intentClass = intentClass;
    }

    public ActivityTypeBean(String name, Class<?> tClass) {
        this.name = name;
        this.intentClass = tClass;
    }
}
