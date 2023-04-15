package com.example.Database.DAO;

import java.util.List;
import com.example.Domain.Cursist;

import javafx.collections.ObservableList;

public interface CursistDAO {
     public List<Cursist> getAllCursisten();

     public int getCompletedCursisten(String cursusNaam);

     public void createCursist(String naam, String geboorteDatum, String emailAdres, String geslacht, int adres);

     public boolean deleteCursist(String naam);

     public void updateCursist(String naam, String geboorteDatum, String emailAdres, String geslacht);

     public boolean checkEmailCursist(String emailAdres);

     public int getCursistIdFromName(String cursistNaam);

     public ObservableList<String> getAllEmails();

     public Cursist getCursistFromEmail(String emailAdres);

     public int getCursistIdFromEmail(String emailAdres);

     public List<String> getNotEnrolledInCursussen(String emailAdres);
}