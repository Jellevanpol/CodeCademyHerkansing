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

    // Deze query haalt alle modules op
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

    // Deze query haalt de gemiddelde voortgang op de modules van een meegegeven
    // cursusNaam
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

    // Deze query haalt alle modules van een cursus waar een cursist is ingeschreven
    @Override
    public ObservableList<Module> getAllModulesFromCursus(String cursusNaam, String emailAdres) {
        ObservableList<Module> modules = FXCollections.observableArrayList();

        String query = "SELECT ci.Titel, cu.CursusNaam, cuci.Voortgang AS Voortgang_Module " +
                "FROM Module m " +
                "JOIN [Content-Item] ci on ci.ContentID = m.contentID " +
                "JOIN [Cursus_ContentItem] cci on cci.ContentID = m.contentID " +
                "JOIN [Cursist_Content-Item] cuci on cuci.ContentID = ci.ContentID " +
                "JOIN Cursist c on c.CursistId = cuci.CursistId " +
                "JOIN Cursus cu on cu.CursusID = cci.CursusID " +
                "WHERE cu.CursusNaam = ? AND EmailAdres = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cursusNaam);
            statement.setString(2, emailAdres);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String titel = resultSet.getString("Titel");
                String progress = resultSet.getDouble("Voortgang_Module") + " %";
                Module module = new Module(titel, progress);
                modules.add(module);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modules;
    }
}
