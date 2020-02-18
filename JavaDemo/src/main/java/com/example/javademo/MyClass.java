package com.example.javademo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MyClass {


  public static void main(String[] args) {
   /*List list= new ArrayList<>();
   list.add(1);
   list.add(12);
   list.add(13);
    test03(list);*/
   test05();
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

  public static void test04(){
    MyArrayList list= new MyArrayList<Integer>();
    list.add(1);
    list.add(12);
    list.add(13);

    Iterator<Integer> iterable = list.iterator();
    while (iterable.hasNext()){
      iterable.next();
      iterable.remove();
      System.out.println("000");
    }
  }

  public static void test05(){
    MyLinkedList list= new MyLinkedList<Integer>();
    list.add(1);
    list.add(12);
    list.add(13);
    list.add(3,123);

//    System.out.println("===:"+list.remove(2));

    Iterator<Integer> iterable = list.iterator();
    while (iterable.hasNext()){
      System.out.println("--:"+iterable.next());
//      iterable.remove();
    }
  }
}
