package com.example.Database.DAO;

import java.sql.SQLException;
import java.util.List;

import com.example.Domain.Inschrijving;

public interface InschrijvingDAO {
    List<Inschrijving> getAllInschrijvingen();

    void addInschrijving(int cursistID, int cursusID);
}
