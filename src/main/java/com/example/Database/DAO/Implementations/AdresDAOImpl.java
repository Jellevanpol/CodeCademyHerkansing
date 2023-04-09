package com.example.Database.DAO.Implementations;

import java.sql.Connection;
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
    
}
