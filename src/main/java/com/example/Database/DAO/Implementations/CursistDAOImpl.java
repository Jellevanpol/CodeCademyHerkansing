package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursistDAO;
import com.example.Domain.Cursist;

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
                "WHERE cci.Voortgang != 100) ";

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

    @Override
    public void createCursist(String naam, String geboorteDatum, String emailAdres, String geslacht, int adresID) {

        String query = "INSERT INTO Cursist(Naam, GeboorteDatum, EmailAdres, Geslacht, AdresID) " +
                "VALUES(?, ?, ?, ?, ?) ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, naam);
            statement.setString(2, geboorteDatum);
            statement.setString(3, emailAdres);
            statement.setString(4, geslacht);
            statement.setInt(5, adresID);
            ResultSet resultSet = statement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean deleteCursist(String emailAdres) {
        String query = "DELETE FROM cursist " +
                "WHERE EmailAdres = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, emailAdres);
            int rowsDeleted = statement.executeUpdate(); // use executeUpdate instead of executeQuery

            if (rowsDeleted == 0) {
                return false; // return false if no rows are affected
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void updateCursist(String naam, String geboorteDatum, String emailAdres, String geslacht, int adresID) {
        String query = "UPDATE cursist " +
                "SET Naam = ?,  " +
                "GeboorteDatum = ?, " +
                "Adres = ?, " +
                "Woonplaats = ?, " +
                "Land = ?, " +
                "EmailAdres = ?, " +
                "Geslacht = ? " +
                "WHERE EmailAdres = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, naam);
            statement.setDate(2, java.sql.Date.valueOf(geboorteDatum));
            statement.setString(6, emailAdres);
            statement.setString(7, geslacht);
            statement.setString(8, emailAdres);
            int rowsAffected = statement.executeUpdate();
            System.out.println(rowsAffected + " row(s) affected");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkEmailCursist(String emailAdres) {
        String query = "SELECT EmailAdres FROM cursist " +
                "WHERE EmailAdres = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, emailAdres);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int getCursistIdFromName(String cursistNaam) {
        int id = 0;
        String query = "SELECT CursistId " +
                "FROM Cursist " +
                "WHERE Naam = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cursistNaam);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("CursistId");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }

    @Override
    public int getCursistIdFromEmail(String emailAdes) {
        int id = 0;
        String query = "SELECT CursistId " +
                "FROM Cursist " +
                "WHERE EmailAdres = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, emailAdes);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt("CursistId");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }

    @Override
    public ObservableList<String> getAllEmails() {
        ObservableList<String> emails = FXCollections.observableArrayList();
        String query = "SELECT EmailAdres " +
                "FROM Cursist ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("EmailAdres");
                emails.add(email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    @Override
    public Cursist getCursistFromEmail(String emailAdres) {
        Cursist cursist = null;
        String query = "SELECT EmailAdres, Naam " +
                "FROM Cursist " +
                "WHERE EmailAdres = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, emailAdres);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString("EmailAdres");
                String naam = resultSet.getString("Naam");
                cursist = new Cursist(naam, email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursist;
    }

    @Override
    public List<String> getNotEnrolledInCursussen(String emailAdres) {
        List<String> cursussen = new ArrayList<>();

        String query = "SELECT CursusNaam " +
                "FROM Cursus " +
                "WHERE CursusID NOT IN ( " +
                "SELECT c.CursusID " +
                "FROM Cursus c " +
                "JOIN Inschrijving i ON i.CursusID = c.CursusID " +
                "JOIN Cursist cu ON cu.CursistId = i.CursistID " +
                "WHERE EmailAdres = ? ) ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, emailAdres);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String naam = resultSet.getString("CursusNaam");
                cursussen.add(naam);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursussen;
    }

}
