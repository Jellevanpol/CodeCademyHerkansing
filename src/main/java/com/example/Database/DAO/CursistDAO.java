package com.example.Database.DAO;

import java.util.List;
import com.example.Domain.Cursist;

import javafx.collections.ObservableList;

public interface CursistDAO {
     List<Cursist> getAllCursisten();

     ObservableList<Cursist> getCompletedCursisten();
}