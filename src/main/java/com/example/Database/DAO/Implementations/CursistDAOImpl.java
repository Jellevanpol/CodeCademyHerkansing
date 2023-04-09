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

public class CursistDAOImpl implements CursistDAO {

    private final Connection connection;

    public CursistDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    // @Override
    // public List<Cursist> getAllCursisten() {
    // List<Cursist> cursisten = new ArrayList<>();

    // String query = "SELECT * FROM Cursist";

    // try {
    // PreparedStatement statement = connection.prepareStatement(query);
    // ResultSet resultSet = statement.executeQuery();

    // while (resultSet.next()) {
    // int id = resultSet.getInt("id");
    // String naam = resultSet.getString("naam");
    // String adres = resultSet.getString("adres");
    // String woonplaats = resultSet.getString("woonplaats");
    // String email = resultSet.getString("email");
    // String telefoonnummer = resultSet.getString("telefoonnummer");

    // // Cursist cursist = new Cursist(id, naam, adres, woonplaats, email,
    // // telefoonnummer);
    // // cursisten.add(cursist);
    // }

    // } catch (SQLException e) {
    // e.printStackTrace();
    // }

    // return cursisten;
    // }
}
