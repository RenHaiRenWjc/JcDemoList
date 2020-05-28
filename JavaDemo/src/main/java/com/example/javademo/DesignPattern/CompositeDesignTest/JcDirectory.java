package com.example.javademo.DesignPattern.CompositeDesignTest;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: com.example.javademo.DesignPattern.CompositeDesignTest
 * Description:
 * JcChen on 2020.05.26.15:50
 */
public class JcDirectory extends BaseFileSystemNode {
  private List<BaseFileSystemNode> subNodes = new ArrayList<>();

  public JcDirectory(String path) {
    super(path);
  }

  @Override
  public int countNumOfFiles() {
    int numOfFiles = 0;
    for (BaseFileSystemNode fileOrDir : subNodes) {
      numOfFiles += fileOrDir.countNumOfFiles();
    }
    return numOfFiles;
  }

  @Override
  public long countSizeOfFiles() {
    long sizeofFiles = 0;
    for (BaseFileSystemNode fileOrDir : subNodes) {
      sizeofFiles += fileOrDir.countSizeOfFiles();
    }
    return sizeofFiles;
  }

  public void addSubNode(BaseFileSystemNode fileOrDir) {
    subNodes.add(fileOrDir);
  }

  public void removeSubNode(BaseFileSystemNode fileOrDir) {
    int size = subNodes.size();
    int i = 0;
    for (; i < size; ++i) {
      if (subNodes.get(i).getPath().equalsIgnoreCase(fileOrDir.getPath())) {
        break;
      }
    }
    if (i < size) {
      subNodes.remove(i);
    }
  }
}
