package org.dobrynin.foldersizer;

public class Folder {

  private String name;
  private Long size;

  public Folder(String name, Long size) {
    this.name = name;
    this.size = size;
  }

  public Folder() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }
}