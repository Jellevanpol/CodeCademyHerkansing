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
    List<String> cursistEmails;

    public Enroll() throws SQLException {
        databaseConnection = new DatabaseConnection();
        inschrijvingDAO = new InschrijvingDAOImpl(databaseConnection);
        cursusDAO = new CursusDAOImpl(databaseConnection);
        cursistDAO = new CursistDAOImpl(databaseConnection);
        cursistNames = new ArrayList<>();
        cursistEmails = new ArrayList<>(cursistDAO.getAllEmails());
        cursists = new ArrayList<>(cursistDAO.getAllCursisten());
    }

    @Override
    public void start(Stage stage) throws SQLException {

        BorderPane borderPain = new BorderPane();
        Text error = new Text("");

        // for (Cursist c : cursists) {
        // cursistNames.add(c.getNaam());
        // }

        Text kiesText = new Text("Kies een cursist:");

        Text kiesCursus = new Text("Kies een cursus:");

        ComboBox<String> comboBox1 = new ComboBox<>();
        comboBox1.getItems().addAll(cursistEmails);

        ComboBox<String> comboBox2 = new ComboBox<>();
        comboBox2.setDisable(true);
        comboBox1.valueProperty().addListener((obs, oldVal, newVal) -> {
            comboBox2.getItems().clear();
            comboBox2.setDisable(false);
            cursistNames = cursistDAO.getNotEnrolledInCursussen(newVal);
            comboBox2.getItems().addAll(cursistNames);
        });

        // De inschrijvingsknop wordt aangemaakt en uitgezet
        Button button = new Button("Enroll in course");
        button.setDisable(true);

        // De inschrijvingsknop wordt aangezet, zodra er in de eerste comboBox een
        // waarde zit
        comboBox2.valueProperty().addListener((obs, oldVal, newVal) -> {
            button.setDisable(false);
        });

        button.setOnAction(e -> {
            inschrijvingDAO.addInschrijving(cursistDAO.getCursistIdFromEmail(comboBox1.getValue()),
                    cursusDAO.getCursusIdFromName(comboBox2.getValue()));
        });

        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(kiesText, comboBox1, kiesCursus, comboBox2, button, error);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        borderPain.setCenter(vBox);
        borderPain.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_LEFT);

        Scene scene = new Scene(borderPain, 800, 600);

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
    public static void main(String[] args) {
        launch(Homescreen.class);
    }
}
