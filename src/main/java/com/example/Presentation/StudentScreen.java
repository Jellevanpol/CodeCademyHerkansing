package com.example.Presentation;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StudentScreen extends Application {
    private TableView<Module> tableView;

    @Override
    public void start(Stage stage) throws Exception {
        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        // String -> Cursist
        ComboBox<String> comboStudent = new ComboBox<>();
        comboStudent.getItems().addAll("Option 1", "Option 2", "Option 3");

        // String -> Cursus
        ComboBox<String> comboCourse = new ComboBox<>();
        comboCourse.getItems().addAll("Option 1", "Option 2", "Option 3");

        TableColumn<Module, String> moduleColumn = new TableColumn<>("Module");
        moduleColumn.setCellValueFactory(new PropertyValueFactory<>("module"));

        TableColumn<Module, String> cursusColumn = new TableColumn<>("Cursus");
        cursusColumn.setCellValueFactory(new PropertyValueFactory<>("cursus"));

        List<TableColumn<Module, ?>> columns = new ArrayList<>();
        columns.add(moduleColumn);
        columns.add(cursusColumn);

        tableView = new TableView<>();
        tableView.getColumns().addAll(columns);
        tableView.setMaxWidth(300);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        VBox vBox = new VBox(10, comboStudent, comboCourse, tableView);
        vBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_LEFT);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        // logic Buttons
        back.setOnAction(e -> {
            try {
                Homescreen homescreen = new Homescreen();
                homescreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

}
