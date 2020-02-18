package com.example.javademo;


import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<E> {
  private int theSize;
  private int modCount = 0;
  private Node<E> beginMarker;
  private Node<E> endMarker;

  public MyLinkedList() {
    clear();
  }


  public boolean add(E e) {
    return add(size(), e);
  }

  public boolean add(int position, E e) {
    System.out.println("---:" + position);
    addBefore(getNode(position), e);
    return true;
  }

  public Node<E> get(int position) {
    return getNode(position);
  }

  public E set(int position, E data) {
    Node<E> p = getNode(position);
    E oldValue = p.data;
    p.data = data;
    return oldValue;
  }

  public E remove(int position) {
    return remove(getNode(position));
  }

  public E remove(Node<E> p) {
    p.prev.next = p.next;
    p.next.prev = p.prev;
    theSize--;
    modCount++;
    return p.data;
  }

  public Node<E> getNode(int position) {
    return getNode(position, 0, size() );
  }

  public Node<E> getNode(int position, int lower, int upper) {
    Node<E> p;
    if ((position < lower || position > upper)) {
      throw new IndexOutOfBoundsException();
    }
    if (position > size() >> 2) {
      p = beginMarker.next;
      for (int i = 0; i < position; i++) {
        p = p.next;
      }
    } else {
      p = endMarker;
      for (int i = size(); i > position; i--) {
        p = p.prev;
      }
    }
    return p;
  }

  private void addBefore(Node p, E data) { // 把data放入节点p前面
    Node<E> newNode = new Node<>(data, p.prev, p);
    newNode.prev.next = newNode;
    p.prev = newNode;
    theSize++;
    modCount++;
  }

  public int size() {
    return theSize;
  }

  public boolean isEmpty() {
    return theSize == 0;
  }

  public void clear() {
    doClear();
  }

  public void doClear() {
    beginMarker = new Node<>(null, null, null);
    endMarker = new Node<>(null, beginMarker, null);
    beginMarker.next = endMarker;
    theSize = 0;
    modCount++;
  }

  public Iterator iterator() {
    return new LinkedListIterator();
  }

  private class LinkedListIterator implements Iterator<E> {
    private Node<E> current = beginMarker.next;
    private int expectedModCount = modCount;
    private boolean okToRemove = false;

    @Override
    public boolean hasNext() {
      return current != endMarker;
    }

    @Override
    public E next() {
      if (modCount != expectedModCount) throw new ConcurrentModificationException();
      if (!hasNext()) throw new NoSuchElementException();
      E nextItem = current.data;
      current = current.next;
      okToRemove = true;
      return nextItem;
    }

    @Override
    public void remove() {
      if (modCount != expectedModCount) throw new ConcurrentModificationException();
      if (!okToRemove) throw new IllegalStateException();
      MyLinkedList.this.remove(current.prev);
      expectedModCount++;
      okToRemove = false;
    }
  }

  private static class Node<E> {
    public E data;
    public Node prev, next;

    public Node(E data, Node<E> prev, Node<E> next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }
  }
}
