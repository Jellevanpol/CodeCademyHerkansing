package com.example.Database.DAO;

import java.util.List;

import com.example.Domain.Inschrijving;

public interface InschrijvingDAO {
    public List<Inschrijving> getAllInschrijvingen();

    public void addInschrijving(int cursistID, int cursusID);
}
