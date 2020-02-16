package com.example.javademo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyClass {


  public static void main(String[] args) {
   List list= new ArrayList<>();
   list.add(1);
   list.add(12);
   list.add(13);
    test03(list);
  }

  public static void test01(List<Integer> list){
    for (int i=0;i<list.size();i++){
      list.remove(i);
      System.out.println("000");
    }
  }
  public static void test02(List<Integer> list){
    for (Integer i:list){
      list.remove(i);
      System.out.println("000");
    }
  }

  public static void test03(List<Integer> list){
    Iterator<Integer> iterable = list.iterator();
    while (iterable.hasNext()){
      iterable.next();
      iterable.remove();
      System.out.println("000");
    }
  }
}
