package com.example.Presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.ModuleDAO;
import com.example.Database.DAO.Implementations.ModuleDAOImpl;
import com.example.Domain.Module;

public class ModuleScreen extends Application {

    private DatabaseConnection databaseConnection;
    private TableView<Module> tableView;
    private ModuleDAO moduleDAO;
    private List<Module> modules;

    public ModuleScreen() throws SQLException {
        databaseConnection = new DatabaseConnection();
        moduleDAO = new ModuleDAOImpl(databaseConnection);
        modules = moduleDAO.getAllModules();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the dropdown menu
        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        ComboBox<String> dropdown = new ComboBox<>();
        modules.stream().map(Module::getTitel).forEach(dropdown.getItems()::add);
        dropdown.getSelectionModel().selectFirst();
        dropdown.setMaxWidth(Double.MAX_VALUE);

        // Create the table view
        TableColumn<Module, String> moduleColumn = new TableColumn<>("Module");
        moduleColumn.setCellValueFactory(new PropertyValueFactory<>("module"));

        TableColumn<Module, Double> progressColumn = new TableColumn<>("Progress");
        progressColumn.setCellValueFactory(new PropertyValueFactory<>("progress"));

        List<TableColumn<Module, ?>> columns = new ArrayList<>();
        columns.add(moduleColumn);
        columns.add(progressColumn);

        tableView = new TableView<>();
        tableView.getColumns().addAll(columns);
        tableView.setMaxWidth(300);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Create a VBox to hold the dropdown and the table view
        VBox vBox = new VBox(10, dropdown, tableView);
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

    public void populateDrowDown(ComboBox<String> comboBox) {

    }
}
