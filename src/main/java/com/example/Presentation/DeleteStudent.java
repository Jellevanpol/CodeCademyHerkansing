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

public class DeleteStudent extends Application{
    
    private DatabaseConnection databaseConnection;
    private CursistDAO cursistDAO;
    private EmailCheck emailCheck;

    public DeleteStudent() throws SQLException {
        databaseConnection = new DatabaseConnection();
        cursistDAO = new CursistDAOImpl(databaseConnection);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Text emailAdresText = new Text("Email adres");
        Text error = new Text();

        TextField inputEmail = new TextField();

        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        Button delete = new Button("Delete student");
        delete.setPrefSize(120, 40);
        delete.setPadding(new Insets(10, 10, 10, 10));
        
        delete.setOnAction(e -> {
            try {
                String emailAdres = inputEmail.getText();

                if (cursistDAO.deleteCursist(emailAdres) == false) {
                    error.setText("Email niet gevonden!");
                } else {
                        cursistDAO.deleteCursist(emailAdres);
                        StudentScreen studentscreen = new StudentScreen();
                        studentscreen.start(stage);
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

        VBox vbox = new VBox(7, emailAdresText, inputEmail, error, delete);
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
