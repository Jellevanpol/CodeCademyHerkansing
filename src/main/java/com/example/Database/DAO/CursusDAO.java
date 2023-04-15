package com.example.Database.DAO;

import java.util.List;

import com.example.Domain.Cursus;

public interface CursusDAO {
    List<Cursus> getAllCursussen();

    List<Cursus> getAllCursussenFromCursist(String cursistNaam);

    int getCursusIdFromName(String cursistNaam);

    List<Cursus> getAllCursussenFromEmail(String emailAdres);
}
