package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.WebcastDAO;
import com.example.Domain.Webcast;

public class WebcastDAOImpl implements WebcastDAO {

    private final Connection connection;

    public WebcastDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }
    
    @Override
    public List<Webcast> getAllWebcasts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllWebcasts'");
    }
    
}
