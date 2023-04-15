package com.example.Database.DAO;

import java.util.List;

import com.example.Domain.Webcast;

import javafx.collections.ObservableList;

public interface WebcastDAO {
    public List<Webcast> getAllWebcasts();

    public ObservableList<Webcast> mostViewedWebcasts();
}
