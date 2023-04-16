package com.example.Presentation;

import java.sql.SQLException;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursistDAO;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UpdateStudent extends Application {

    private DatabaseConnection databaseConnection;
    private CursistDAO cursistDAO;
    private EmailCheck emailCheck;
    private DateCheck datumCheck;

    public UpdateStudent() throws SQLException {
        databaseConnection = new DatabaseConnection();
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
        error.setFill(Color.RED);
        Text question = new Text("Which student should be updated");

        // Alle tekstvelden worden geïnstantieerd
        TextField inputName = new TextField();
        TextField inputDatum = new TextField();
        inputDatum.setPromptText("e.g. YYYY-MM-DD");
        TextField inputEmail = new TextField();
        inputEmail.setPromptText("e.g. Johndoe@gmail.com");
        TextField inputGeslacht = new TextField();
        TextField awnser = new TextField();
        awnser.setPromptText("Email");
        awnser.setFocusTraversable(false);
        setFieldsDisabled(true, inputName, inputDatum, inputEmail,
                inputGeslacht);

        // Buttons aanmaken en stylen
        Button back = new Button("Back");
        back.setPrefSize(100, 50);
        Button update = new Button("Update student");
        update.setPrefSize(120, 40);
        update.setDisable(true);
        update.setPadding(new Insets(10, 10, 10, 10));

        // Checken of answer valide is
        awnser.textProperty().addListener((observable, oldValue, newValue) -> {
            if (cursistDAO.checkEmailCursist(newValue)) {
                setFieldsDisabled(false, inputName, inputDatum, inputEmail,
                        inputGeslacht);
                error.setText("");
            } else {
                setFieldsDisabled(true, inputName, inputDatum, inputEmail,
                        inputGeslacht);
                error.setText("Student not found!");
            }
        });

        // Logica voor updaten student
        update.setOnAction(e -> {
            try {
                // Variable vullen
                String name = inputName.getText();
                String geboorteDatum = inputDatum.getText();
                String emailAdres = inputEmail.getText();
                String geslacht = inputGeslacht.getText();

                if (name.isEmpty() || geboorteDatum.isEmpty() || emailAdres.isEmpty() || geslacht.isEmpty()) {
                    error.setText("One or more input fields are empty!");
                    update.setDisable(true);
                } else {
                    if (emailCheck.correctEmail(emailAdres) && datumCheck.isValidDate(geboorteDatum)) {
                        cursistDAO.updateCursist(name, geboorteDatum, emailAdres, geslacht);
                        update.setDisable(false);
                        StudentScreen studentscreen = new StudentScreen();
                        studentscreen.start(stage);
                    }
                    error.setText("One or more input fields are empty!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Back button werking
        back.setOnAction(e -> {
            try {
                StudentScreen studentscreen = new StudentScreen();
                studentscreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // HBox maken en elementen toevoegen
        HBox hbox = new HBox(5, question, awnser);
        hbox.setMaxWidth(300);
        hbox.setPadding(new Insets(0, 0, 20, 0));
        hbox.setAlignment(Pos.CENTER);

        // VBox maken en elementen toevoegen
        VBox vbox = new VBox(7, hbox, nameText, inputName, geboorteDatumText, inputDatum, emailAdresText, inputEmail,
                geslachtText, inputGeslacht, error,
                update);
        vbox.setMaxWidth(300);
        vbox.setAlignment(Pos.CENTER);

        // BorderPane maken en elementen toevoegen
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_LEFT);

        // Scene maken en laten zien
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        // Buttons disablen als er een inputveld leeg is
        update.setDisable(true);
        inputName.textProperty().addListener((observable, oldValue, newValue) -> {
            update.setDisable(isAnyTextFieldEmpty(inputName, inputDatum,
                    inputEmail, inputGeslacht));
        });
        inputDatum.textProperty().addListener((observable, oldValue, newValue) -> {
            update.setDisable(isAnyTextFieldEmpty(inputName, inputDatum,
                    inputEmail, inputGeslacht));
        });
        inputEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            update.setDisable(isAnyTextFieldEmpty(inputName, inputDatum,
                    inputEmail, inputGeslacht));
        });
        inputGeslacht.textProperty().addListener((observable, oldValue, newValue) -> {
            update.setDisable(isAnyTextFieldEmpty(inputName, inputDatum,
                    inputEmail, inputGeslacht));
        });
    }

    // Method om veld te disablen
    private void setFieldsDisabled(boolean disable, TextField... fields) {
        for (TextField field : fields) {
            field.setDisable(disable);
        }
    }

    // Methode voor checken of een veld leeg is
    private boolean isAnyTextFieldEmpty(TextField... fields) {
        for (TextField field : fields) {
            if (field.getText().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        launch(Homescreen.class);
    }
}
