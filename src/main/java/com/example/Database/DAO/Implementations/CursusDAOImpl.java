package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.CursusDAO;
import com.example.Domain.Cursus;

public class CursusDAOImpl implements CursusDAO{

    private final Connection connection;

    public CursusDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public List<Cursus> getAllCursussen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCursussen'");
    }
    
}
