package com.example.Database.DAO;

import java.util.List;
import com.example.Domain.Cursist;
import com.example.Domain.Cursus;

import javafx.collections.ObservableList;

public interface CursistDAO {
     List<Cursist> getAllCursisten();

     int getCompletedCursisten();

     void createCursist(String naam, String geboorteDatum, String emailAdres, String geslacht, int adresID);

     boolean deleteCursist(String naam);

     void updateCursist(String naam, String geboorteDatum, String emailAdres, String geslacht);

     boolean checkEmailCursist(String emailAdres);

     int getCursistIdFromName(String cursistNaam);

     ObservableList<String> getAllEmails();

     Cursist getCursistFromEmail(String emailAdres);

     int getCursistIdFromEmail(String emailAdres);

     List<String> getNotEnrolledInCursussen(String emailAdres);
}