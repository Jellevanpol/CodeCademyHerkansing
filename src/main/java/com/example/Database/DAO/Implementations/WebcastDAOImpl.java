package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.WebcastDAO;
import com.example.Domain.Webcast;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WebcastDAOImpl implements WebcastDAO {

    private final Connection connection;

    public WebcastDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }
    
    @Override
    public List<Webcast> getAllWebcasts() {
        return null;
    }

    @Override
    public ObservableList<Webcast> mostViewedWebcasts() {
        ObservableList<Webcast> webcasts = FXCollections.observableArrayList();

        String query = "SELECT m.Titel, cu.CursusNaam, Voortgang " +
                "FROM Module m " +
                "JOIN [Content-Item] ci on ci.ContentID = m.contentID " +
                "JOIN [Cursus_ContentItem] cci on cci.ContentID = m.contentID " +
                "JOIN Cursus cu on cu.CursusID = cci.CursusID " +
                "WHERE cu.CursusNaam = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String titel = resultSet.getString("Titel");
                String progress = resultSet.getDouble("Voortgang") + "%";
                Webcast webcast = new Webcast(0, progress, progress, progress);
                webcasts.add(webcast);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return webcasts;
    }
    
}
