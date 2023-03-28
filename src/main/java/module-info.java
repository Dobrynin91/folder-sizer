module org.dobrynin.foldersizer {
  requires javafx.controls;
  requires javafx.fxml;
  requires org.apache.commons.io;
  requires static lombok;


  opens org.dobrynin.foldersizer to javafx.fxml;
  exports org.dobrynin.foldersizer;
}