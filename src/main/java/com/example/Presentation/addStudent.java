package com.example.Presentation;

import java.sql.SQLException;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.AdresDAO;
import com.example.Database.DAO.CursistDAO;
import com.example.Database.DAO.Implementations.AdresDAOImpl;
import com.example.Database.DAO.Implementations.CursistDAOImpl;
import com.example.Logic.DateCheck;
import com.example.Logic.EmailCheck;

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
    private DatabaseConnection databaseConnection;
    private CursistDAO cursistDAO;
    private EmailCheck emailCheck;
    private AdresDAO adresDAO;
    private DateCheck datumCheck;

    // Constructor
    public addStudent() throws SQLException {
        databaseConnection = new DatabaseConnection();
        adresDAO = new AdresDAOImpl(databaseConnection);
        cursistDAO = new CursistDAOImpl(databaseConnection);
        emailCheck = new EmailCheck();
        datumCheck = new DateCheck();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Alle teksten worden geïnstantieerd
        Text nameText = new Text("Name");
        Text geboorteDatumText = new Text("Birthdate");
        Text emailAdresText = new Text("Email");
        Text geslachtText = new Text("Man, vrouw or anders");
        Text error = new Text();

        // Alle TextFields worden geïnstantieerd
        TextField inputName = new TextField();
        TextField inputDatum = new TextField();
        TextField inputEmail = new TextField();
        inputDatum.setPromptText("e.g. YYYY-MM-DD");
        inputEmail.setPromptText("e.g. Johndoe@gmail.com");
        TextField inputGeslacht = new TextField();

        // De Toevoegen-knop wordt geïnstantieerd en gestyled
        Button add = new Button("Add student");
        add.setPrefSize(120, 40);
        add.setPadding(new Insets(10, 10, 10, 10));

        // De setOnAction wordt voor het toevoegen van een student
        int adresID = adresDAO.getNewestAdress();
        add.setOnAction(e -> {
            try {
                // Alle gegevens die nodig zijn voor het maken van een student
                String name = inputName.getText();
                String geboorteDatum = inputDatum.getText();
                String emailAdres = inputEmail.getText();
                String geslacht = inputGeslacht.getText();

                // Er wordt gecheckt of een van de invulvelden leeg zijn
                if (name.isEmpty() || geboorteDatum.isEmpty() || emailAdres.isEmpty() || geslacht.isEmpty()) {
                    error.setText("One or more input fields are empty!");
                } else {
                    // Check of de email en datum valide is
                    if (emailCheck.correctEmail(emailAdres) && datumCheck.isValidDate(geboorteDatum)) {
                        cursistDAO.createCursist(name, geboorteDatum, emailAdres, geslacht,
                                adresID);
                        // Stuur terug naar studentScreen
                        StudentScreen studentscreen = new StudentScreen();
                        studentscreen.start(stage);
                    } else {
                        error.setText("One or more input fields are formatted incorrectly!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        // Alle tekst(fields) toevoegen aan de vbox
        VBox vbox = new VBox(7, nameText, inputName, geboorteDatumText, inputDatum, emailAdresText, inputEmail,
                geslachtText, inputGeslacht, error, add);
        vbox.setMaxWidth(300);
        vbox.setAlignment(Pos.CENTER);

        // Root element aanmaken
        BorderPane root = new BorderPane();
        root.setCenter(vbox);

        // Scene maken en laten zien
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Homescreen.class);
    }
}
