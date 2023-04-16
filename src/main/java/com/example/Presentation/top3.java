package com.example.Presentation;

import java.sql.SQLException;
import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.WebcastDAO;
import com.example.Database.DAO.Implementations.WebcastDAOImpl;
import com.example.Domain.Webcast;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class top3 extends Application {
    private DatabaseConnection databaseConnection;
    private WebcastDAO webcastDAO;
    private TableView<Webcast> tableView = new TableView<>();

    public top3() throws SQLException {
        databaseConnection = new DatabaseConnection();
        webcastDAO = new WebcastDAOImpl(databaseConnection);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // Back button werking
        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        // Tekst element wordt geïnstantieerd
        Text topText = new Text("The top 3 most viewed webcasts are: ");

        // Kolom aanmaken voor tableview
        TableColumn<Webcast, String> titelColumn = new TableColumn<>("Title");
        titelColumn.setCellValueFactory(new PropertyValueFactory<>("titel"));

        // Kolom aanmaken voor tableview
        TableColumn<Webcast, Double> progressColumn = new TableColumn<>("Amount of students");
        progressColumn.setCellValueFactory(new PropertyValueFactory<>("aantal"));

        // Kolommen aan tableview toevoegen
        tableView.getColumns().setAll(titelColumn, progressColumn);
        tableView.setMaxWidth(300);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<Webcast> webcasts = webcastDAO.mostViewedWebcasts();
        tableView.setItems(webcasts);
        tableView.setPrefHeight(webcasts.size() * 37);

        // VBox aanmaken en elementen toewijzen
        VBox vbox = new VBox(10, topText, tableView);
        vbox.setAlignment(Pos.CENTER);

        // Werking back button
        back.setOnAction(e -> {
            try {
                Homescreen homescreen = new Homescreen();
                homescreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Borderpane maken en stylen
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_LEFT);

        // Scene maken en laten zien
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Homescreen.class);
    }

}
