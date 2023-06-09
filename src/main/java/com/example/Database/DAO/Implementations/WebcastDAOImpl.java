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

    // Query heeft nog geen implementatie en wordt nog nergens gebruikt
    @Override
    public List<Webcast> getAllWebcasts() {
        return null;
    }

    // Deze methode haalt de drie meest bekeken webcasts op
    @Override
    public ObservableList<Webcast> mostViewedWebcasts() {
        ObservableList<Webcast> webcasts = FXCollections.observableArrayList();

        String query = "SELECT  TOP 3   ci.Titel, COUNT(cci.CursistId) AS Aantal_Cursisten " +
                "FROM [Cursist_Content-item] cci " +
                "JOIN [Content-Item] ci ON ci.ContentID = cci.ContentId " +
                "GROUP BY cci.ContentID, ci.Titel " +
                "ORDER BY COUNT(cci.CursistId) DESC, cci.ContentId ASC ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String titel = resultSet.getString("Titel");
                int aantal = resultSet.getInt("Aantal_Cursisten");
                Webcast webcast = new Webcast(titel, aantal);
                webcasts.add(webcast);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return webcasts;
    }
}
