package com.example.Database.DAO;

import java.util.List;
import com.example.Domain.Module;

public interface ModuleDAO {
    List<Module> getAllModules();

    List<Module> getAllModulesFromCursus(int cursistID);
}
