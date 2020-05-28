package com.example.javademo.DesignPattern.CompositeDesignTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: com.example.javademo.DesignPattern.CompositeDesignTest
 * Description:设计一个类来表示文件系统中的目录,实现如下功能：
 * <p>
 * 动态地添加、删除某个目录下的子目录或文件；
 * 统计指定目录下的文件个数；
 * 统计指定目录下的文件总大小
 * JcChen on 2020.05.26.15:23
 */


public class FileSystemNode {
  private String path;
  private boolean isFile;
  private List<FileSystemNode> subNodes = new ArrayList<>();

  public FileSystemNode(String path, boolean isFile) {
    this.path = path;
    this.isFile = isFile;
  }

  // 文件个数
  public int countNumOfFiles() {
    if (isFile) {
      return 1;
    }
    int numOfFiles = 0;
    for (FileSystemNode fileOrDir : subNodes) {
      numOfFiles += fileOrDir.countNumOfFiles();
    }
    return numOfFiles;
  }

  // 文件总大小
  public long countSizeOfFiles() {
    if (isFile) {
      File file = new File(path);
      if (!file.exists()) return 0;
      return file.length();
    }
    long sizeofFiles = 0;
    for (FileSystemNode fileOrDir : subNodes) {
      sizeofFiles += fileOrDir.countSizeOfFiles();
    }
    return sizeofFiles;
  }

  public String getPath() {
    return path;
  }

  // 添加文件
  public void addSubNode(FileSystemNode fileOrDir) {
    subNodes.add(fileOrDir);
  }

  // 删除文件
  public void removeSubNode(FileSystemNode fileOrDir) {
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

