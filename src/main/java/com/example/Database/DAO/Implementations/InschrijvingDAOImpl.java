package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.InschrijvingDAO;
import com.example.Domain.Inschrijving;

public class InschrijvingDAOImpl implements InschrijvingDAO{

    private final Connection connection;

    public InschrijvingDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }
    
    @Override
    public List<Inschrijving> getAllInschrijvingen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllInschrijvingen'");
    }
    
}
