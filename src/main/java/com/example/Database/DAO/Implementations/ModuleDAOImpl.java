package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.ModuleDAO;
import com.example.Domain.Module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ModuleDAOImpl implements ModuleDAO {

    private final Connection connection;

    public ModuleDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public List<Module> getAllModules() {
        List<Module> modules = new ArrayList<>();
        String query = "SELECT ci.Titel, AVG(cci.Voortgang) AS AVG_Voortgang " +
                "FROM Module m " +
                "JOIN [Content-Item] ci on ci.ContentID = m.contentID " +
                "JOIN [Cursist_Content-item] cci on cci.ContentId = ci.contentID " +
                "GROUP BY m.contentID, ci.Titel ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String titel = resultSet.getString("Titel");
                String progress = resultSet.getDouble("AVG_Voortgang") + "%";

                Module module = new Module(titel, progress);
                modules.add(module);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modules;
    }

    @Override
    public ObservableList<Module> getAllAverageModulesFromCursus(String cursusNaam) {
        ObservableList<Module> modules = FXCollections.observableArrayList();

        String query = "SELECT ci.Titel, AVG(Voortgang) AS AVG_Voortgang " +
                "FROM [Content-Item] ci " +
                "JOIN [Cursus_ContentItem] cci on cci.ContentID = ci.contentID " +
                "JOIN [Cursist_Content-item] cuci ON cuci.ContentId = ci.ContentId " +
                "JOIN Cursus cu on cu.CursusID = cci.CursusID " +
                "WHERE cu.CursusNaam = ? AND Type = 'Module'  " +
                "GROUP BY ci.Titel, cu.CursusNaam, ci.contentID ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cursusNaam);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String titel = resultSet.getString("Titel");
                String progress = resultSet.getDouble("AVG_Voortgang") + " %";
                Module module = new Module(titel, progress);
                modules.add(module);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modules;
    }

    @Override
    public ObservableList<Module> getAllModulesFromCursus(String cursusNaam) {
        ObservableList<Module> modules = FXCollections.observableArrayList();

        String query = "SELECT ci.Titel, cu.CursusNaam, cci.Voortgang " +
                "FROM Module m " +
                "JOIN [Content-Item] ci on ci.ContentID = m.contentID " +
                "JOIN [Cursus_ContentItem] cci on cci.ContentID = m.contentID " +
                "JOIN Cursus cu on cu.CursusID = cci.CursusID " +
                "WHERE cu.CursusNaam = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cursusNaam);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String titel = resultSet.getString("Titel");
                String progress = resultSet.getDouble("Voortgang") + " %";
                Module module = new Module(titel, progress);
                modules.add(module);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modules;
    }
}
