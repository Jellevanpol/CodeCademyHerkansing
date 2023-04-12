package com.example.Database.DAO;

import java.util.List;
import com.example.Domain.Cursist;

import javafx.collections.ObservableList;

public interface CursistDAO {
     List<Cursist> getAllCursisten();

     int getCompletedCursisten();

     boolean createCursist(String naam, String geboorteDatum, String adres, String woonplaats, String land, String emailAdres, String geslacht);
}