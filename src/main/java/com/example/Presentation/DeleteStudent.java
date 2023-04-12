package com.example.Presentation;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DeleteStudent extends Application{
    @Override
    public void start(Stage stage) throws Exception {
        Text name = new Text("Naam");

        TextField inputName = new TextField();

        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        Button add = new Button("Delete student");
        add.setPrefSize(120, 40);
        add.setDisable(true);
        add.setPadding(new Insets(10, 10, 10, 10));
        

        back.setOnAction(e -> {
            try {
                StudentScreen studentscreen = new StudentScreen();
                studentscreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox vbox = new VBox(7, name, inputName, add);
        vbox.setMaxWidth(300);
        vbox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_LEFT);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(AddStudent.class);
    }
}