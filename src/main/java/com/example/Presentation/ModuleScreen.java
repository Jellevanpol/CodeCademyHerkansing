package com.example.Presentation;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModuleScreen extends Application {
    private TableView<Module> tableView;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the dropdown menu
        ComboBox<String> dropdown = new ComboBox<>();
        dropdown.getItems().addAll("Option 1", "Option 2", "Option 3");
        dropdown.getSelectionModel().selectFirst();
        dropdown.setMaxWidth(Double.MAX_VALUE);

        // Create the table view
        TableColumn<Module, String> moduleColumn = new TableColumn<>("Module");
        moduleColumn.setCellValueFactory(new PropertyValueFactory<>("module"));

        TableColumn<Module, String> cursusColumn = new TableColumn<>("Cursus");
        cursusColumn.setCellValueFactory(new PropertyValueFactory<>("cursus"));

        tableView = new TableView<>();
        tableView.getColumns().addAll(moduleColumn, cursusColumn);
        tableView.setMaxWidth(300);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Create a VBox to hold the dropdown and the table view
        VBox vBox = new VBox(10, dropdown, tableView);
        vBox.setAlignment(Pos.CENTER);

        // Create a BorderPane and put the VBox in the center
        BorderPane root = new BorderPane();
        root.setCenter(vBox);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
