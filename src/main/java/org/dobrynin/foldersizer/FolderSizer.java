package org.dobrynin.foldersizer;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FolderSizer extends Application {


  @Override
  public void start(Stage stage) {

    TableView<Folder> tblItems = new TableView<>();
    tblItems.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    TableColumn<Folder, String> columnName = new TableColumn<>("Name");
    TableColumn<Folder, String> columnSize = new TableColumn<>("Size MB");
    columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    columnSize.setCellValueFactory(new PropertyValueFactory<>("Size"));
    tblItems.getColumns().addAll(columnName, columnSize);

    TextField textField = new TextField();
    textField.setPromptText("Put a path here");
    textField.setMinWidth(400);
    Button button = new Button("Submit");
    button.setOnAction(action -> {
      String path = textField.getText();
      if (!path.isEmpty()) {
        tblItems.getItems().clear();
        Path folder = Paths.get(path);
        List<Folder> folderList = new ArrayList<>();
        try {
          Files.walk(folder, 1)
                  .filter(p -> p.toFile().isDirectory())
                  .filter(p -> !p.toFile().getName().equals(folder.getFileName().toString()))
                  .forEach(p -> {
                    Long dirSize = FileUtils.sizeOfDirectory(p.toFile()) / 1024 / 1024;
                    String dirName = p.toFile().getName();
                    folderList.add(new Folder(dirName, dirSize));
                  });
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        tblItems.getItems().addAll(folderList);
      }
    });

    HBox hbox = new HBox(textField, button);

    VBox.setVgrow(tblItems, Priority.ALWAYS);
    VBox vbox = new VBox(hbox, tblItems);
    vbox.setPadding(new Insets(10));
    vbox.setSpacing(10);

    Scene scene = new Scene(vbox);
    stage.setWidth(720);
    stage.setHeight(480);
    stage.setTitle("Folder size");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}