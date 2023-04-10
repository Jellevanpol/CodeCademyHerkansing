package com.example.Presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursistDAO;
import com.example.Database.DAO.CursusDAO;
import com.example.Database.DAO.Implementations.CursistDAOImpl;
import com.example.Database.DAO.Implementations.CursusDAOImpl;
import com.example.Domain.Cursist;
import com.example.Domain.Cursus;
import com.example.Domain.Module;

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

public class StudentScreen extends Application {
    private TableView<Module> tableView;
    private CursusDAO cursusDAO;
    private DatabaseConnection databaseConnection;
    private CursistDAO cursistDAO;
    private List<Cursist> cursisten;
    private List<Cursus> courses;

    public StudentScreen() throws SQLException {
        databaseConnection = new DatabaseConnection();
        cursistDAO = new CursistDAOImpl(databaseConnection);
        cursusDAO = new CursusDAOImpl(databaseConnection);
        cursisten = cursistDAO.getAllCursisten();
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

        TableColumn<Module, String> moduleColumn = new TableColumn<>("Module");
        moduleColumn.setCellValueFactory(new PropertyValueFactory<>("module"));

        TableColumn<Module, String> cursusColumn = new TableColumn<>("Cursus");
        cursusColumn.setCellValueFactory(new PropertyValueFactory<>("cursus"));

        List<TableColumn<Module, ?>> columns = new ArrayList<>();
        columns.add(moduleColumn);
        columns.add(cursusColumn);

        tableView = new TableView<>();
        tableView.getColumns().addAll(columns);
        tableView.setMaxWidth(300);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

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
        for (Cursist c : cursisten) {
            comboBox.getItems().add(c.getNaam());
        }
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
