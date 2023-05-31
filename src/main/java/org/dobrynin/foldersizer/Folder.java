package org.dobrynin.foldersizer;

public class Folder {

  private String name;
  private Long size;

  private String type;

  public Folder(String name, Long size, String folderType) {
    this.name = name;
    this.size = size;
    this.type = folderType;
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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}