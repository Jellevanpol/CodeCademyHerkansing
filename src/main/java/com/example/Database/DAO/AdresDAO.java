package com.example.Database.DAO;

import java.util.List;

import com.example.Domain.Adres;

public interface AdresDAO {
    public List<Adres> getAllAdressen();

    public void addAdress(String huisnummer, String straatnaam, String woonplaats, String land, String postcode);

    public int getNewestAdress();
}
