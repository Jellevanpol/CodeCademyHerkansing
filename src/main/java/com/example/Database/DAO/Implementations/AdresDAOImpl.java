package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.AdresDAO;
import com.example.Domain.Adres;

public class AdresDAOImpl implements AdresDAO {

    private final Connection connection;

    public AdresDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public List<Adres> getAllAdressen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllAdressen'");
    }

    @Override
    public void addAdress(String huisnummer, String straatnaam, String woonplaats, String land, String postcode) {
        String query = "INSERT INTO Adres(Huisnummer, StraatNaam, Woonplaats, Land, Postcode) " +
                "VALUES(?, ?, ?, ?, ?) ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, huisnummer);
            statement.setString(2, straatnaam);
            statement.setString(3, woonplaats);
            statement.setString(4, land);
            statement.setString(5, postcode);
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getNewestAdress() {
        int id = 0;
        String query = "SELECT TOP 1 AdresID " +
                "FROM Adres " +
                "ORDER BY AdresID DESC ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            id = resultSet.getInt("AdresID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

}
