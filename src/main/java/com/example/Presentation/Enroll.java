package com.example.Presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursistDAO;
import com.example.Database.DAO.CursusDAO;
import com.example.Database.DAO.InschrijvingDAO;
import com.example.Database.DAO.Implementations.CursistDAOImpl;
import com.example.Database.DAO.Implementations.CursusDAOImpl;
import com.example.Database.DAO.Implementations.InschrijvingDAOImpl;
import com.example.Domain.Cursist;
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

public class Enroll extends Application {

    private DatabaseConnection databaseConnection;
    private InschrijvingDAO inschrijvingDAO;
    private CursusDAO cursusDAO;
    private CursistDAO cursistDAO;
    List<String> cursistNames;
    List<Cursist> cursists;

    public Enroll() throws SQLException {
        databaseConnection = new DatabaseConnection();
        inschrijvingDAO = new InschrijvingDAOImpl(databaseConnection);
        cursusDAO = new CursusDAOImpl(databaseConnection);
        cursistDAO = new CursistDAOImpl(databaseConnection);
        cursistNames = new ArrayList<>();
        cursists = new ArrayList<>(cursistDAO.getAllCursisten());
    }

    @Override
    public void start(Stage stage) throws SQLException {

        BorderPane borderPain = new BorderPane();
        Text error = new Text("");

        for (Cursist c : cursists) {
            cursistNames.add(c.getNaam());
        }

        // Create the first combobox
        ComboBox<String> comboBox1 = new ComboBox<>();
        comboBox1.getItems().addAll(cursistNames);

        // Create the second combobox
        ComboBox<String> comboBox2 = new ComboBox<>();
        comboBox2.setDisable(true);
        comboBox1.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBox2.setDisable(false);
            populateComboBoxCourse(comboBox2, comboBox1);
        });

        // Create the button
        Button button = new Button("Enroll in course");
        button.setDisable(true);
        comboBox2.valueProperty().addListener((obs, oldVal, newVal) -> {
            button.setDisable(false);
            inschrijvingDAO.addInschrijving(cursistDAO.getCursistIdFromName(comboBox1.getValue()),
                    cursusDAO.getCursusIdFromName(comboBox2.getValue()));
            error.setText("");

        });

        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        // Create a VBox layout and add the components
        VBox vBox = new VBox();
        vBox.getChildren().addAll(comboBox1, comboBox2, button, error);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        borderPain.setCenter(vBox);
        borderPain.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_LEFT);

        // Create the scene and add the layout
        Scene scene = new Scene(borderPain, 800, 600);

        // Set the stage and show the window
        stage.setScene(scene);
        stage.show();

        back.setOnAction(e -> {
            try {
                Homescreen homeScreen = new Homescreen();
                homeScreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void populateComboBoxCourse(ComboBox<String> comboBox, ComboBox<String> comboBox2) {
        List<Cursus> cursussen = new ArrayList<>(cursusDAO.getAllCursussen());
        List<String> cursusNames = new ArrayList<>();
        for (Cursus c : cursussen) {
            cursusNames.add(c.getCursusNaam());
        }
        List<Cursus> cursussenFromCursist = new ArrayList<>(cursusDAO.getAllCursussenFromCursist(comboBox2.getValue()));
        List<String> cursusNames2 = new ArrayList<>();

        for (Cursus c : cursussenFromCursist) {
            cursusNames2.add(c.getCursusNaam());
        }
        cursusNames.removeAll(cursusNames2);

        for (Cursus c : cursussen) {
            comboBox.getItems().addAll(cursusNames);
        }
    }
}
