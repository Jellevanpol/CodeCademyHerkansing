package com.example.Presentation;

import java.sql.SQLException;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursistDAO;
import com.example.Database.DAO.CursusDAO;
import com.example.Database.DAO.Implementations.CursistDAOImpl;
import com.example.Database.DAO.Implementations.CursusDAOImpl;
import com.example.Domain.Cursist;
import com.example.Domain.Cursus;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Completed extends Application {

    private DatabaseConnection databaseConnection;
    private List<Cursus> cursussen;
    private CursusDAO cursusDAO;
    private CursistDAO cursistDAO;

    public Completed() throws SQLException {
        databaseConnection = new DatabaseConnection();
        cursusDAO = new CursusDAOImpl(databaseConnection);
        cursistDAO = new CursistDAOImpl(databaseConnection);
        cursussen = cursusDAO.getAllCursussen();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        ComboBox<String> dropdown = new ComboBox<>();
        cursussen.stream().map(Cursus::getCursusNaam).forEach(dropdown.getItems()::add);
        dropdown.getSelectionModel().selectFirst();
        dropdown.setMaxWidth(Double.MAX_VALUE);

        Text text = new Text();
        if(dropdown.getValue() != null){
            ObservableList<Cursist> cursisten = cursistDAO.getCompletedCursisten();
            text.setText("Aantal cursisten die de cursus behaald hebben: " + cursisten);
        }

        VBox vboxText = new VBox(text);
        vboxText.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(10, dropdown, vboxText);
        vbox.setAlignment(Pos.CENTER);

        back.setOnAction(e -> {
            try {
                Homescreen homescreen = new Homescreen();
                homescreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_LEFT);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Homescreen.class);
    }
}
