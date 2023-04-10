package com.example.Presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursistDAO;
import com.example.Database.DAO.CursusDAO;
import com.example.Database.DAO.ModuleDAO;
import com.example.Database.DAO.Implementations.CursistDAOImpl;
import com.example.Database.DAO.Implementations.CursusDAOImpl;
import com.example.Database.DAO.Implementations.ModuleDAOImpl;
import com.example.Domain.Cursist;
import com.example.Domain.Cursus;
import com.example.Domain.Module;

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
import javafx.stage.Stage;

public class StudentScreen extends Application {
    private TableView<Module> tableView = new TableView<>();
    private CursusDAO cursusDAO;
    private DatabaseConnection databaseConnection;
    private CursistDAO cursistDAO;
    private List<Cursist> cursisten;
    private List<Cursus> courses;
    private ModuleDAO moduleDAO;

    public StudentScreen() throws SQLException {
        databaseConnection = new DatabaseConnection();
        cursistDAO = new CursistDAOImpl(databaseConnection);
        cursusDAO = new CursusDAOImpl(databaseConnection);
        cursisten = cursistDAO.getAllCursisten();
        moduleDAO = new ModuleDAOImpl(databaseConnection);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Button back = new Button("Back");
        back.setPrefSize(100, 50);

        // String -> Cursist
        ComboBox<String> comboStudent = new ComboBox<>();
        populateComboBoxNames(comboStudent);

        // String -> Cursus
        ComboBox<String> comboCourse = new ComboBox<>();
        comboCourse.setDisable(true); // Disable the ComboBox initially
        comboStudent.valueProperty().addListener((obs, oldVal, newVal) -> {
            // Enable the ComboBox and populate it with the modules for the selected cursist
            comboCourse.setDisable(false);
            populateComboBoxCourses(comboCourse, comboStudent);
        });

        TableColumn<Module, String> titelColumn = new TableColumn<>("Titel");
        titelColumn.setCellValueFactory(new PropertyValueFactory<>("titel"));

        TableColumn<Module, Double> progressColumn = new TableColumn<>("Voortgang");
        progressColumn.setCellValueFactory(new PropertyValueFactory<>("progress"));

        tableView.getColumns().setAll(titelColumn, progressColumn);
        tableView.setMaxWidth(300);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        comboCourse.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && comboStudent.getValue() != null) {
                ObservableList<Module> modules = moduleDAO.getAllModulesFromCursus(newVal);
                tableView.setItems(modules);
            }
        });

        VBox vBox = new VBox(10, comboStudent, comboCourse, tableView);
        vBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(vBox);
        root.setBottom(back);
        BorderPane.setAlignment(back, Pos.BOTTOM_LEFT);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        // logic Buttons
        back.setOnAction(e -> {
            try {
                Homescreen homescreen = new Homescreen();
                homescreen.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void populateComboBoxNames(ComboBox<String> comboBox) {
        cursisten.stream().map(Cursist::getNaam).forEach(comboBox.getItems()::add);
    }

    public void populateComboBoxCourses(ComboBox<String> comboBox, ComboBox<String> comboBox2) {
        String selectedCursistName = comboBox2.getValue();
        Cursist selectedCursist = null;
        for (Cursist c : cursisten) {
            if (c.getNaam().equals(selectedCursistName)) {
                selectedCursist = c;
                break;
            }
        }

        if (selectedCursist != null) {
            String cursistNaam = selectedCursist.getNaam();

            courses = cursusDAO.getAllCursussenFromCursist(cursistNaam);

            comboBox.getItems().clear();

            for (Cursus c : courses) {
                comboBox.getItems().add(c.getCursusNaam());
            }
        }
    }
}
