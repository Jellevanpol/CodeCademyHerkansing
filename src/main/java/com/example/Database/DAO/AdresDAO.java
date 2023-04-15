package com.example.Database.DAO;

import java.util.List;

import com.example.Domain.Adres;

public interface AdresDAO {
    List<Adres> getAllAdressen();

    void addAdress(String huisnummer, String straatnaam, String woonplaats, String land, String postcode);

    int getNewestAdress();
}
