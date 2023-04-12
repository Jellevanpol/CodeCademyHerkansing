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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateStudent extends Application {

    private DatabaseConnection databaseConnection;
    private CursistDAO cursistDAO;
    private EmailCheck emailCheck;

    public UpdateStudent() throws SQLException {
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
        error.setFill(Color.RED);
        Text question = new Text("Welke cursist wilt u updaten?");

        TextField inputName = new TextField();
        TextField inputDatum = new TextField();
        TextField inputAdres = new TextField();
        TextField inputWoonplaats = new TextField();
        TextField inputLand = new TextField();
        TextField inputEmail = new TextField();
        TextField inputGeslacht = new TextField();
        TextField awnser = new TextField();
        awnser.setPromptText("Email adres");
        awnser.setFocusTraversable(false);
        setFieldsDisabled(true, inputName, inputDatum, inputAdres, inputWoonplaats, inputLand, inputEmail,
                inputGeslacht);
        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        Button update = new Button("Update student");
        update.setPrefSize(120, 40);
        update.setDisable(true);
        update.setPadding(new Insets(10, 10, 10, 10));

        awnser.textProperty().addListener((observable, oldValue, newValue) -> {
            if (cursistDAO.checkEmailCursist(newValue)) {
                setFieldsDisabled(false, inputName, inputDatum, inputAdres, inputWoonplaats, inputLand, inputEmail,
                        inputGeslacht);
                error.setText("");
            } else {
                setFieldsDisabled(true, inputName, inputDatum, inputAdres, inputWoonplaats, inputLand, inputEmail,
                        inputGeslacht);
                error.setText("User niet gevonden!");
            }
        });

        update.setOnAction(e -> {

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
                    update.setDisable(true);
                } else {
                    if (emailCheck.correctEmail(emailAdres)) {
                        cursistDAO.updateCursist(name, geboorteDatum, adres, woonplaats, land, emailAdres, geslacht);
                        update.setDisable(false);
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

        HBox hbox = new HBox(5, question, awnser);
        hbox.setMaxWidth(300);
        hbox.setPadding(new Insets(0, 0, 20, 0));
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(7, hbox, nameText, inputName, geboorteDatumText, inputDatum, adresText, inputAdres,
                woonplaatsText,
                inputWoonplaats, landText, inputLand, emailAdresText, inputEmail, geslachtText, inputGeslacht, error,
                update);
        vbox.setMaxWidth(300);
        vbox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_LEFT);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        update.setDisable(true);
        inputName.textProperty().addListener((observable, oldValue, newValue) -> {
            update.setDisable(isAnyTextFieldEmpty(inputName, inputDatum, inputAdres, inputWoonplaats, inputLand,
                    inputEmail, inputGeslacht));
        });
        inputDatum.textProperty().addListener((observable, oldValue, newValue) -> {
            update.setDisable(isAnyTextFieldEmpty(inputName, inputDatum, inputAdres, inputWoonplaats, inputLand,
                    inputEmail, inputGeslacht));
        });
        inputAdres.textProperty().addListener((observable, oldValue, newValue) -> {
            update.setDisable(isAnyTextFieldEmpty(inputName, inputDatum, inputAdres, inputWoonplaats, inputLand,
                    inputEmail, inputGeslacht));
        });
        inputWoonplaats.textProperty().addListener((observable, oldValue, newValue) -> {
            update.setDisable(isAnyTextFieldEmpty(inputName, inputDatum, inputAdres, inputWoonplaats, inputLand,
                    inputEmail, inputGeslacht));
        });
        inputLand.textProperty().addListener((observable, oldValue, newValue) -> {
            update.setDisable(isAnyTextFieldEmpty(inputName, inputDatum, inputAdres, inputWoonplaats, inputLand,
                    inputEmail, inputGeslacht));
        });
        inputEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            update.setDisable(isAnyTextFieldEmpty(inputName, inputDatum, inputAdres, inputWoonplaats, inputLand,
                    inputEmail, inputGeslacht));
        });
        inputGeslacht.textProperty().addListener((observable, oldValue, newValue) -> {
            update.setDisable(isAnyTextFieldEmpty(inputName, inputDatum, inputAdres, inputWoonplaats, inputLand,
                    inputEmail, inputGeslacht));
        });
    }

    private void setFieldsDisabled(boolean disable, TextField... fields) {
        for (TextField field : fields) {
            field.setDisable(disable);
        }
    }

    private boolean isAnyTextFieldEmpty(TextField... fields) {
        for (TextField field : fields) {
            if (field.getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
