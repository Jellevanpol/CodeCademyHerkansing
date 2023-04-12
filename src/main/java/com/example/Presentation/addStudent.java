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

public class addStudent extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Text name = new Text("Naam");
        Text geboorteDatum = new Text("Geboorte datum");
        Text adres = new Text("Adres");
        Text woonplaats = new Text("Woonplaats");
        Text land = new Text("Land");
        Text emailAdres = new Text("Email adres (LET OP: gebruik een correct format!)");
        Text geslacht = new Text("Man, vrouw of anders");

        TextField inputName = new TextField();
        TextField inputDatum = new TextField();
        TextField inputAdres = new TextField();
        TextField inputWoonplaats = new TextField();
        TextField inputLand = new TextField();
        TextField inputEmail = new TextField();
        TextField inputGeslacht = new TextField();

        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        Button add = new Button("Add student");
        add.setPrefSize(100, 40);
        add.setDisable(true);
        add.setPadding(new Insets(10, 10, 10, 10));
        

        back.setOnAction(e -> {
            try {
                Homescreen homescreen = new Homescreen();
                homescreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox vbox = new VBox(7, name, inputName, geboorteDatum, inputDatum, adres, inputAdres, woonplaats,
                inputWoonplaats, land, inputLand, emailAdres, inputEmail, geslacht, inputGeslacht, add);
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
        launch(addStudent.class);
    }

}
