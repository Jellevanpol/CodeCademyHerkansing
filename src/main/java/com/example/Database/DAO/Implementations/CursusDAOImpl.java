package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursusDAO;
import com.example.Domain.Cursist;
import com.example.Domain.Cursus;

public class CursusDAOImpl implements CursusDAO {

    private final Connection connection;

    public CursusDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public List<Cursus> getAllCursussen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCursussen'");
    }

    @Override
    public List<Cursus> getAllCursussenFromCursist(String cursistNaam) {
        List<Cursus> cursussen = new ArrayList<>();
        List<Cursist> cursisten = new ArrayList<>();

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
                Cursist cursist = new Cursist(resultSet.getString("Naam"));
                cursisten.add(cursist);
                Cursus cursus = new Cursus(cursusNaam, cursisten);
                cursussen.add(cursus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursussen;
    }
}
