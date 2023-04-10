package com.example.Database.DAO.Implementations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.Database.DatabaseConnection;
import com.example.Database.DAO.ModuleDAO;
import com.example.Domain.Module;

public class ModuleDAOImpl implements ModuleDAO {

    private final Connection connection;

    public ModuleDAOImpl(DatabaseConnection databaseConnection) {
        this.connection = databaseConnection.getConnection();
    }

    @Override
    public List<Module> getAllModules() {
        List<Module> modules = new ArrayList<>();
        String query = "SELECT Titel FROM Module ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String titel = resultSet.getString("Titel");

                Module module = new Module(titel);
                modules.add(module);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modules;
    }

    @Override
    public List<Module> getAllModulesFromCursus(int cursistID) {
        List<Module> modules = new ArrayList<>();

        String query = "SELECT m.*, co.NaamContact, co.EmailContact " +
                "FROM Module m " +
                "JOIN Module_Contact mc on mc.ModuleID = m.ModuleID " +
                "JOIN Contact co on co.ContactID = mc.ContactID " +
                "JOIN [Content-Item] c on m.contentID = c.contentID " +
                "JOIN [Cursist_Content-item] cci on cci.ContentId = c.ContentID " +
                "JOIN Cursist cu on cu.CursistId = cci.CursistId " +
                "WHERE cu.CursistId = ? ";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, cursistID);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String versie = resultSet.getString("Versie");
                String naamContact = resultSet.getString("NaamContact");
                String emailContact = resultSet.getString("EmailContact");
                int volgnummer = resultSet.getInt("Volgnummer");
                String titel = resultSet.getString("Titel");

                Module module = new Module(versie, naamContact, emailContact, volgnummer, titel);
                modules.add(module);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return modules;
    }
}
