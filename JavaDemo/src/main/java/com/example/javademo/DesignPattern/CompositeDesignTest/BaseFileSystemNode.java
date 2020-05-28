package com.example.javademo.DesignPattern.CompositeDesignTest;

/**
 * ClassName: com.example.javademo.DesignPattern.CompositeDesignTest
 * Description:
 * JcChen on 2020.05.26.15:49
 */
public abstract class BaseFileSystemNode {

  protected String path;

  public BaseFileSystemNode(String path) {
    this.path = path;
  }

  public abstract int countNumOfFiles();

  public abstract long countSizeOfFiles();

  public String getPath() {
    return path;
  }
}
