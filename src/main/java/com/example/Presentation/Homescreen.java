package com.example.Presentation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Homescreen extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Initializing elements
        HBox hbox = new HBox();
        Button module = new Button("Progress module");
        Button student = new Button("Progress student");
        Button top3 = new Button("Top 3 modules");
        Button completed = new Button("Completed modules");

        Insets insetsButtons = new Insets(10);

        // Styling module
        module.setPrefSize(150, 150);
        module.setStyle("-fx-font-weight: bold");

        // Styling student
        student.setPrefSize(150, 150);
        student.setStyle("-fx-font-weight: bold");

        // Styling top3
        top3.setPrefSize(150, 150);
        top3.setStyle("-fx-font-weight: bold");

        // Styling completed
        completed.setPrefSize(150, 150);
        completed.setStyle("-fx-font-weight: bold");

        // Set margin for buttons
        HBox.setMargin(module, insetsButtons);
        HBox.setMargin(student, insetsButtons);
        HBox.setMargin(top3, insetsButtons);
        HBox.setMargin(completed, insetsButtons);

        // Add elements
        hbox.getChildren().addAll(module, student, top3, completed);
        hbox.setAlignment(Pos.CENTER);

        // Set scene
        Scene scene = new Scene(hbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Homescreen.class);
    }

}
