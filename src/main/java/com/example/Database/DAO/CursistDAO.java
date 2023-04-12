package com.example.Database.DAO;

import java.util.List;
import com.example.Domain.Cursist;

public interface CursistDAO {
     List<Cursist> getAllCursisten();

     int getCompletedCursisten();

     void createCursist(String naam, String geboorteDatum, String adres, String woonplaats, String land,
               String emailAdres, String geslacht);

     boolean deleteCursist(String naam);

     void updateCursist(String naam, String geboorteDatum, String adres, String woonplaats, String land,
               String emailAdres, String geslacht);

}