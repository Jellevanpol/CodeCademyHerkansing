package com.example.Presentation;

import java.sql.SQLException;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.AdresDAO;
import com.example.Database.DAO.CursistDAO;
import com.example.Database.DAO.Implementations.AdresDAOImpl;
import com.example.Database.DAO.Implementations.CursistDAOImpl;
import com.example.Logic.EmailCheck;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import com.example.Logic.PostCodeCheck;

public class addStudent extends Application {
    private DatabaseConnection databaseConnection;
    private CursistDAO cursistDAO;
    private EmailCheck emailCheck;
    private AdresDAO adresDAO;
    private PostCodeCheck postCodeCheck;

    // Constructor
    public addStudent() throws SQLException {
        databaseConnection = new DatabaseConnection();
        adresDAO = new AdresDAOImpl(databaseConnection);
        cursistDAO = new CursistDAOImpl(databaseConnection);
        emailCheck = new EmailCheck();
        postCodeCheck = new PostCodeCheck();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Alle teksten worden geïnstantieerd
        Text nameText = new Text("Naam");
        Text geboorteDatumText = new Text("Geboorte datum");
        Text emailAdresText = new Text("Email adres (LET OP: gebruik een correct format!)");
        Text geslachtText = new Text("Man, vrouw of anders");
        Text huisnummerText = new Text("Huisnummer");
        Text straatNaamText = new Text("Straatnaam");
        Text woonplaatsText = new Text("Woonplaats");
        Text postcodeText = new Text("Postode");
        Text landText = new Text("Land");
        Text error = new Text();

        // Alle TextFields worden geïnstantieerd
        TextField inputName = new TextField();
        TextField inputDatum = new TextField();
        TextField inputEmail = new TextField();
        TextField inputGeslacht = new TextField();
        TextField inputHuisnummer = new TextField();
        TextField inputStraatNaam = new TextField();
        TextField inputWoonplaats = new TextField();
        TextField inputPostcode = new TextField();
        TextField inputLand = new TextField();

        // De Terug-knop wordt geïnstantieerd en gestyled
        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        // De Toevoegen-knop wordt geïnstantieerd en gestyled
        Button add = new Button("Add student");
        add.setPrefSize(120, 40);
        add.setPadding(new Insets(10, 10, 10, 10));

        // De setOnAction wordt voor het toevoegen van een student
        add.setOnAction(e -> {
            try {
                // Alle gegevens die nodig zijn voor het maken van een student
                String name = inputName.getText();
                String geboorteDatum = inputDatum.getText();
                String emailAdres = inputEmail.getText();
                String geslacht = inputGeslacht.getText();
                String huisnummer = inputHuisnummer.getText();
                String straatnaam = inputStraatNaam.getText();
                String woonplaats = inputWoonplaats.getText();
                String postcode = inputPostcode.getText();
                String land = inputLand.getText();

                // Er wordt gecheckt of een van de invulvelden leeg zijn
                if (name.isEmpty() || geboorteDatum.isEmpty() || woonplaats.isEmpty()
                        || land.isEmpty() || emailAdres.isEmpty() || geslacht.isEmpty()) {
                    error.setText("Een of meerdere velden zijn niet gevuld!");
                } else {
                    if (postCodeCheck.correctPostcode(postcode)) {
                        if (emailCheck.correctEmail(emailAdres)) {
                            adresDAO.addAdress(huisnummer, straatnaam, woonplaats, land, postcode);
                            int adresID = adresDAO.getNewestAdress();
                            cursistDAO.createCursist(name, geboorteDatum, emailAdres, geslacht,
                                    adresID);
                            StudentScreen studentscreen = new StudentScreen();
                            studentscreen.start(stage);
                        } else {
                            error.setText("Verkeerde postcode format!");
                        }
                    } else {
                        error.setText("Verkeerde email format!");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        back.setOnAction(e -> {
            try {
                StudentScreen studentscreen = new StudentScreen();
                studentscreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        VBox vBoxHuisnummer = new VBox(5, huisnummerText, inputHuisnummer);
        VBox vBoxPostcode = new VBox(5, postcodeText, inputPostcode);
        HBox hBox = new HBox(5, vBoxHuisnummer, vBoxPostcode);

        VBox vBoxWoonplaats = new VBox(5, woonplaatsText, inputWoonplaats);
        VBox vBoxLand = new VBox(5, landText, inputLand);
        HBox hBox2 = new HBox(5, vBoxWoonplaats, vBoxLand);

        VBox vbox = new VBox(7, nameText, inputName, geboorteDatumText, inputDatum, emailAdresText, inputEmail,
                geslachtText, inputGeslacht, straatNaamText, inputStraatNaam, hBox, hBox2, error,
                add);
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
}
