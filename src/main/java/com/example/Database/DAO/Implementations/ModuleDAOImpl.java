package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.ModuleDAO;

public class ModuleDAOImpl implements ModuleDAO {

    private final Connection connection;

    public ModuleDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }
    
    @Override
    public List<Module> getAllModules() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllModules'");
    }
    
}
