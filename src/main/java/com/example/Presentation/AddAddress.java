package com.example.Presentation;

import java.sql.SQLException;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.AdresDAO;
import com.example.Database.DAO.CursistDAO;
import com.example.Database.DAO.Implementations.AdresDAOImpl;
import com.example.Database.DAO.Implementations.CursistDAOImpl;
import com.example.Logic.EmailCheck;
import com.example.Logic.PostCodeCheck;

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

public class AddAddress extends Application {

    private DatabaseConnection databaseConnection;
    private AdresDAO adresDAO;
    private PostCodeCheck postCodeCheck;

    // Constructor
    public AddAddress() throws SQLException {
        databaseConnection = new DatabaseConnection();
        adresDAO = new AdresDAOImpl(databaseConnection);
        postCodeCheck = new PostCodeCheck();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Alle teksten worden geïnstantieerd
        Text huisnummerText = new Text("Huisnummer");
        Text straatNaamText = new Text("Straatnaam");
        Text woonplaatsText = new Text("Woonplaats");
        Text postcodeText = new Text("Postode");
        Text landText = new Text("Land");
        Text error = new Text();

        // Alle TextFields worden geïnstantieerd
        TextField inputHuisnummer = new TextField();
        TextField inputStraatNaam = new TextField();
        TextField inputWoonplaats = new TextField();
        TextField inputPostcode = new TextField();
        TextField inputLand = new TextField();

        // De Terug-knop wordt geïnstantieerd en gestyled
        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        // De Toevoegen-knop wordt geïnstantieerd en gestyled
        Button add = new Button("Add address");
        add.setPrefSize(120, 40);
        add.setPadding(new Insets(10, 10, 10, 10));

        // De setOnAction wordt voor het toevoegen van een student
        add.setOnAction(e -> {
            try {
                // Alle gegevens die nodig zijn voor het maken van een student
                String huisnummer = inputHuisnummer.getText();
                String straatnaam = inputStraatNaam.getText();
                String woonplaats = inputWoonplaats.getText();
                String postcode = inputPostcode.getText();
                String land = inputLand.getText();

                // Er wordt gecheckt of een van de invulvelden leeg zijn
                if (huisnummer.isEmpty() || straatnaam.isEmpty() || woonplaats.isEmpty()
                        || land.isEmpty() || postcode.isEmpty()) {
                    error.setText("Een of meerdere velden zijn niet gevuld!");
                } else {
                    if (postCodeCheck.correctPostcode(postcode)) {
                        adresDAO.addAdress(huisnummer, straatnaam, woonplaats, land, postcode);
                        addStudent addStudentScreen = new addStudent();
                        addStudentScreen.start(stage);
                    } else {
                        error.setText("Verkeerde postcode format!");
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

        VBox vbox = new VBox(7, straatNaamText, inputStraatNaam, huisnummerText, inputHuisnummer, woonplaatsText,
                inputWoonplaats, postcodeText, inputPostcode, landText, inputLand, error, add);
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
