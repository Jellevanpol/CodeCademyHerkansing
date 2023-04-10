package com.example.Presentation;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class top3 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        ComboBox<String> dropdown = new ComboBox<>();
        dropdown.getItems().addAll("Option 1", "Option 2", "Option 3");
        dropdown.getSelectionModel().selectFirst();

        Text nr1 = new Text("Webcast nr 1");
        Text nr2 = new Text("Webcast nr 2");
        Text nr3 = new Text("Webcast nr 3");

        VBox vboxText = new VBox(nr1, nr2, nr3);
        vboxText.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, dropdown, vboxText);
        vbox.setAlignment(Pos.CENTER);

        back.setOnAction(e -> {
            try {
                Homescreen homescreen = new Homescreen();
                homescreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_LEFT);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Homescreen.class);
    }

}
