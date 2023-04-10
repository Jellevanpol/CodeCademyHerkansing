package com.example.Database.DAO;

import java.util.List;
import com.example.Domain.Module;

import javafx.collections.ObservableList;

public interface ModuleDAO {
    List<Module> getAllModules();

    ObservableList<Module> getAllAverageModulesFromCursus(String cursusNaam);

    ObservableList<Module> getAllModulesFromCursus(String cursusNaam);
}
