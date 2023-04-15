package com.example.Database.DAO;

import java.util.List;
import com.example.Domain.Module;

import javafx.collections.ObservableList;

public interface ModuleDAO {
    public List<Module> getAllModules();

    public ObservableList<Module> getAllAverageModulesFromCursus(String cursusNaam);

    public ObservableList<Module> getAllModulesFromCursus(String cursusNaam, String emailAdres);
}
