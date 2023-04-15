package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.InschrijvingDAO;
import com.example.Domain.Inschrijving;

public class InschrijvingDAOImpl implements InschrijvingDAO {

    private final Connection connection;

    public InschrijvingDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public List<Inschrijving> getAllInschrijvingen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllInschrijvingen'");
    }

    // Deze query schrijft de meegegeven cursist in in de meegegeven cursus
    @Override
    public void addInschrijving(int cursistID, int cursusID) {
        int id = 0;
        String query = "INSERT INTO Inschrijving (CursistID, CursusID, Datum) " +
                "VALUES (?, ?, getDate()) ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, cursistID);
            statement.setInt(2, cursusID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                id = resultSet.getInt(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
