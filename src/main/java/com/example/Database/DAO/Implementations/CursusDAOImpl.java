package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursusDAO;
import com.example.Domain.Cursus;

public class CursusDAOImpl implements CursusDAO {

    private final Connection connection;

    public CursusDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public List<Cursus> getAllCursussen() {
        String query = "SELECT * FROM Cursus ";
        List<Cursus> cursussen = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String cursusNaam = resultSet.getString("CursusNaam");
                Cursus cursus = new Cursus(cursusNaam);
                cursussen.add(cursus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursussen;
    }

    @Override
    public List<Cursus> getAllCursussenFromCursist(String cursistNaam) {
        List<Cursus> cursussen = new ArrayList<>();

        String query = "SELECT c.CursusNaam, cu.Naam " +
                "FROM Cursus c " +
                "JOIN Inschrijving i on i.CursusID = c.CursusID " +
                "JOIN Cursist cu on cu.CursistId = i.CursistID " +
                "WHERE cu.Naam = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cursistNaam);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String cursusNaam = resultSet.getString("CursusNaam");
                Cursus cursus = new Cursus(cursusNaam);
                cursussen.add(cursus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursussen;
    }

    @Override
    public int getCursusIdFromName(String cursistNaam) {
        int id = 0;
        String query = "SELECT CursusID " +
                "FROM Cursus " +
                "WHERE CursusNaam = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cursistNaam);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("CursusID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public List<Cursus> getAllCursussenFromEmail(String emailAdres) {
        List<Cursus> cursussen = new ArrayList<>();

        String query = "SELECT c.CursusNaam, cu.Naam " +
                "FROM Cursus c " +
                "JOIN Inschrijving i on i.CursusID = c.CursusID " +
                "JOIN Cursist cu on cu.CursistId = i.CursistID " +
                "WHERE cu.EmailAdres = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, emailAdres);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String cursusNaam = resultSet.getString("CursusNaam");
                Cursus cursus = new Cursus(cursusNaam);
                cursussen.add(cursus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursussen;
    }
}
