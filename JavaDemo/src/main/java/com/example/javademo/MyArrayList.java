package com.example.javademo;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class MyArrayList<E> {

  private static final int DEFAULT_CAPACITY = 10;

  private int theSize;
  private E[] theItems;

  public MyArrayList() {
    doClear();
  }

  public boolean add(E e) {
    add(size(), e);
    return true;
  }

  public void add(int position, E e) {
    if (theItems.length == size()) {
      ensureCapacity(size() * 2 + 1);
    }
    for (int i = theSize; i > position; i--) {
      theItems[i] = theItems[i - 1];
    }
    theItems[position] = e;
    theSize++;
  }

  public E remove(int position) {
    E removedItem = theItems[position];
    for (int i = position; i < size() - 1; i++) {
      theItems[i] = theItems[i + 1];
    }
    theSize--;
    return removedItem;
  }


  public E get(int position) {
    if (position < 0 || position >= size()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return theItems[position];
  }

  public E set(int position, E newValue) {
    if (position < 0 || position >= size()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    E old = theItems[position];
    theItems[position] = newValue;
    return old;
  }

  public void clear() {
    doClear();
  }


  public void doClear() {
    theSize = 0;
    ensureCapacity(DEFAULT_CAPACITY);
  }

  // 容量扩充
  private void ensureCapacity(int newCapacity) {
    if (newCapacity < theSize) {
      return;
    }
    E[] old = theItems;
    theItems = (E[]) new Object[newCapacity];
    for (int i = 0; i < size(); i++) {
      theItems[i] = old[i];
    }
  }

  public int size() {
    return theSize;
  }

  public boolean isEmpty() {
    return size() == 0;

  }

  public void trimToSize() {
    ensureCapacity(size());
  }

  public java.util.Iterator<E> iterator() {
    return new ArrayListIterator();
  }

  private class ArrayListIterator implements Iterator<E> { // 内部类、添加static后变为嵌套类
    private int current = 0;

    @Override
    public boolean hasNext() {
      return current < size();
    }

    @Override
    public E next() {
      if (!hasNext()) throw new NoSuchElementException();
      return theItems[current++];
    }

    @Override
    public void remove() {
      MyArrayList.this.remove(--current);
    }
  }


}
