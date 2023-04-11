package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursistDAO;
import com.example.Domain.Cursist;
import com.example.Domain.Enumerations.Geslacht;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CursistDAOImpl implements CursistDAO {

    private final Connection connection;

    public CursistDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public List<Cursist> getAllCursisten() {
        List<Cursist> cursisten = new ArrayList<>();

        String query = "SELECT * FROM Cursist";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String naam = resultSet.getString("Naam");
                Cursist cursist = new Cursist(naam);
                cursisten.add(cursist);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursisten;
    }

    @Override
    public int getCompletedCursisten() {
        int behaaldeCursisten = 0;
        String query = "SELECT APPROX_COUNT_DISTINCT(c.cursistID) AS Behaald " +
                "FROM Cursist c " +
                "JOIN [Cursist_Content-item] cci ON cci.CursistId = c.CursistId " +
                "JOIN [Content-Item] ci ON ci.ContentID = cci.ContentId " +
                "JOIN Module m ON m.contentID = ci.ContentID " +
                "WHERE NOT EXISTS (SELECT * " +
                "FROM Module " +
                "WHERE Voortgang != 100) ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                behaaldeCursisten = resultSet.getInt("Behaald");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return behaaldeCursisten;
    }
}
