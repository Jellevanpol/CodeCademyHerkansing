package com.example.Database.DAO;

import java.util.List;

import com.example.Domain.Cursus;

public interface CursusDAO {
    public List<Cursus> getAllCursussen();

    public List<Cursus> getAllCursussenFromCursist(String cursistNaam);

    public int getCursusIdFromName(String cursistNaam);

    public List<Cursus> getAllCursussenFromEmail(String emailAdres);
}
