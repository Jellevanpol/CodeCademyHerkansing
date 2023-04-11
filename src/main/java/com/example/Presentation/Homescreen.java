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
        Button top3 = new Button("Top 3 webcasts");
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
        stage.setTitle("Jelle van Pol (2203205) & Kenan van der Heijden (2197280)");
        stage.setScene(scene);
        stage.show();

        // logic for buttons
        module.setOnAction(e -> {
            try {
                ModuleScreen moduleScreen = new ModuleScreen();
                moduleScreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        student.setOnAction(e -> {
            try {
                StudentScreen studentScreen = new StudentScreen();
                studentScreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        top3.setOnAction(e ->{
            try{
                top3 top3Screen = new top3();
                top3Screen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        completed.setOnAction(e ->{
            try{
                Completed completedScreen = new Completed();
                completedScreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(Homescreen.class);
    }

}
