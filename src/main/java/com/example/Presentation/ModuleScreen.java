package com.example.Presentation;

import java.sql.SQLException;
import java.util.List;

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

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursusDAO;
import com.example.Database.DAO.ModuleDAO;
import com.example.Database.DAO.Implementations.CursusDAOImpl;
import com.example.Database.DAO.Implementations.ModuleDAOImpl;
import com.example.Domain.Module;
import com.example.Domain.Cursus;

public class ModuleScreen extends Application {

    private DatabaseConnection databaseConnection;
    private TableView<Module> tableView = new TableView<>();
    private ModuleDAO moduleDAO;
    private List<Cursus> cursussen;
    private CursusDAO cursusDAO;

    public ModuleScreen() throws SQLException {
        databaseConnection = new DatabaseConnection();
        cursusDAO = new CursusDAOImpl(databaseConnection);
        cursussen = cursusDAO.getAllCursussen();
        moduleDAO = new ModuleDAOImpl(databaseConnection);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Back button maken
        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        // Tekst elementen
        Text kiesText = new Text("Select a course:");

        // Create the dropdown
        ComboBox<String> dropdown = new ComboBox<>();
        cursussen.stream().map(Cursus::getCursusNaam).forEach(dropdown.getItems()::add);
        dropdown.getSelectionModel().selectFirst();
        dropdown.setMaxWidth(300);
        dropdown.setValue(null);

        // Create the table view
        TableColumn<Module, String> titelColumn = new TableColumn<>("Title");
        titelColumn.setCellValueFactory(new PropertyValueFactory<>("titel"));

        TableColumn<Module, Double> progressColumn = new TableColumn<>("Progress");
        progressColumn.setCellValueFactory(new PropertyValueFactory<>("progress"));

        tableView.getColumns().setAll(titelColumn, progressColumn);
        tableView.setMaxWidth(300);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        dropdown.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (dropdown.getValue() != null) {
                ObservableList<Module> modules = moduleDAO.getAllAverageModulesFromCursus(dropdown.getValue());
                tableView.setItems(modules);
            }
        });

        // Create a VBox to hold the dropdown and the table view
        VBox vBox = new VBox(10, kiesText, dropdown, tableView);
        vBox.setAlignment(Pos.CENTER);

        // Create a BorderPane and put the VBox in the center
        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_LEFT);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Logic buttons
        back.setOnAction(e -> {
            try {
                Homescreen homescreen = new Homescreen();
                homescreen.start(primaryStage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(Homescreen.class);
    }
}
