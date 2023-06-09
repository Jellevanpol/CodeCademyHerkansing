package com.example.Presentation;

import java.sql.SQLException;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursistDAO;
import com.example.Database.DAO.CursusDAO;
import com.example.Database.DAO.Implementations.CursistDAOImpl;
import com.example.Database.DAO.Implementations.CursusDAOImpl;
import com.example.Domain.Cursus;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Completed extends Application {

    private int behaald = 0;
    private DatabaseConnection databaseConnection;
    private List<Cursus> cursussen;
    private CursusDAO cursusDAO;
    private CursistDAO cursistDAO;

    // Constructor
    public Completed() throws SQLException {
        databaseConnection = new DatabaseConnection();
        cursusDAO = new CursusDAOImpl(databaseConnection);
        cursistDAO = new CursistDAOImpl(databaseConnection);
        cursussen = cursusDAO.getAllCursussen();
        this.behaald = 0;
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Back button en tekst instatiëren
        Button back = new Button("Back");
        back.setPrefSize(100, 50);
        Text kiesText = new Text("Select a course:");
        Text text = new Text();
        text.setText("Amount of students that have completed ... : xxx");

        // Dropdown van cursussen maken en een listener geven
        ComboBox<String> dropdown = new ComboBox<>();
        cursussen.stream().map(Cursus::getCursusNaam).forEach(dropdown.getItems()::add);
        dropdown.getSelectionModel().selectFirst();
        dropdown.setMaxWidth(Double.MAX_VALUE);
        dropdown.setValue(null);
        dropdown.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (dropdown.getValue() != null) {
                // Aantal behaalde cursisten ophalen en in tekstveld zetten
                behaald = cursistDAO.getCompletedCursisten(dropdown.getValue());
                text.setText("Amount of students that have completed " + dropdown.getValue() + ": " + behaald);
            }
        });

        // VBoxen aanmaken en objecten toewijzen
        VBox vboxText = new VBox(text);
        vboxText.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, kiesText, dropdown, vboxText);
        vbox.setAlignment(Pos.CENTER);

        // Back
        back.setOnAction(e -> {
            try {
                Homescreen homescreen = new Homescreen();
                homescreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Root element aanmaken en elementen toevoegen
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_LEFT);

        // Scene maken en laten zien
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Homescreen.class);
    }
}
