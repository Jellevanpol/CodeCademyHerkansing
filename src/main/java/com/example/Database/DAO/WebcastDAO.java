package com.example.Database.DAO;

import java.util.List;

import com.example.Domain.Webcast;

import javafx.collections.ObservableList;

public interface WebcastDAO {
    List<Webcast> getAllWebcasts();

    ObservableList<Webcast> mostViewedWebcasts();
    
}
