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
import com.example.Domain.Adres;
import com.example.Domain.Cursist;
import com.example.Domain.Enumerations.Geslacht;

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
                String emailAdres = resultSet.getString("EmailAdres");
                String naam = resultSet.getString("Naam");
                Date geboorteDatum = resultSet.getDate("GeboorteDatum");
                String adres = resultSet.getString("Adres");
                int cursistid = resultSet.getInt("Cursistid");
                String geslacht = resultSet.getString("Geslacht");

                Cursist cursist = new Cursist(emailAdres, naam, geboorteDatum, adres, cursistid, geslacht);
                cursisten.add(cursist);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursisten;
    }
}
