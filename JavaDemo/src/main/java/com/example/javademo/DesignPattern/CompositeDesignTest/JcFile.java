package com.example.javademo.DesignPattern.CompositeDesignTest;

/**
 * ClassName: com.example.javademo.DesignPattern.CompositeDesignTest
 * Description:
 * JcChen on 2020.05.26.15:49
 */
public class JcFile extends BaseFileSystemNode {
  public JcFile(String path) {
    super(path);
  }

  @Override
  public int countNumOfFiles() {
    return 1;
  }

  @Override
  public long countSizeOfFiles() {
    java.io.File file = new java.io.File(path);
    if (!file.exists()) return 0;
    return file.length();
  }
}

