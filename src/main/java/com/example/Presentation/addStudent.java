package com.example.Presentation;

import java.sql.SQLException;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursistDAO;
import com.example.Database.DAO.Implementations.CursistDAOImpl;
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

    public addStudent() throws SQLException {
        databaseConnection = new DatabaseConnection();
        cursistDAO = new CursistDAOImpl(databaseConnection);
        emailCheck = new EmailCheck();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Text nameText = new Text("Naam");
        Text geboorteDatumText = new Text("Geboorte datum");
        Text adresText = new Text("Adres");
        Text woonplaatsText = new Text("Woonplaats");
        Text landText = new Text("Land");
        Text emailAdresText = new Text("Email adres (LET OP: gebruik een correct format!)");
        Text geslachtText = new Text("Man, vrouw of anders");
        Text error = new Text();

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
        add.setPrefSize(120, 40);
        add.setPadding(new Insets(10, 10, 10, 10));

        add.setOnAction(e -> {
            try {
                String name = inputName.getText();
                String geboorteDatum = inputDatum.getText();
                String adres = inputAdres.getText();
                String woonplaats = inputWoonplaats.getText();
                String land = inputLand.getText();
                String emailAdres = inputEmail.getText();
                String geslacht = inputGeslacht.getText();

                if (name.isEmpty() || geboorteDatum.isEmpty() || adres.isEmpty() || woonplaats.isEmpty()
                        || land.isEmpty() || emailAdres.isEmpty() || geslacht.isEmpty()) {
                    error.setText("Een of meerdere velden zijn niet gevuld!");
                } else {
                    if (emailCheck.correctEmail(emailAdres)) {
                        cursistDAO.createCursist(name, geboorteDatum, adres, woonplaats, land, emailAdres, geslacht);
                        StudentScreen studentscreen = new StudentScreen();
                        studentscreen.start(stage);
                    }
                    error.setText("Verkeerde email format!");
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

        VBox vbox = new VBox(7, nameText, inputName, geboorteDatumText, inputDatum, adresText, inputAdres,
                woonplaatsText,
                inputWoonplaats, landText, inputLand, emailAdresText, inputEmail, geslachtText, inputGeslacht, error,
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
