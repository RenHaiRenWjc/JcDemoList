package com.example.javademo.InflationDemo;

/**
 * ClassName: com.example.javademo.InflationDemo
 * Description:
 * JcChen on 2020.04.07.21:54
 */
public class SonClass extends FatherClass {
  private String mSonName;
  protected int mSonAge;
  public String mSonBirthday;

  public void printSonMsg() {
    System.out.println("Son Msg - name : "
      + mSonName + "; age : " + mSonAge);
  }

  private void setSonName(String name) {
    mSonName = name;
  }

  private void setSonAge(int age) {
    mSonAge = age;
  }

  private int getSonAge() {
    return mSonAge;
  }

  private String getSonName() {
    return mSonName;
  }


}
