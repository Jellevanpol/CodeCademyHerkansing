package com.example.Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.Domain.Enumerations.Geslacht;

public class Cursist {
    private String emailAdres;
    private String naam;
    private Date geboorteDatum;
    private Adres adres;
    private int cursistid;
    private Geslacht geslacht;
    private List<Cursus> cursus;

    public Cursist(String emailAdres, String naam, Date geboorteDatum, Adres adres, int cursistid, Geslacht geslacht) {
        this.emailAdres = emailAdres;
        this.naam = naam;
        this.geboorteDatum = geboorteDatum;
        this.adres = adres;
        this.cursistid = cursistid;
        this.geslacht = geslacht;
        this.cursus = new ArrayList<>();
    }

    public String getEmailAdres() {
        return this.emailAdres;
    }

    public void setEmailAdres(String emailAdres) {
        this.emailAdres = emailAdres;
    }

    public String getNaam() {
        return this.naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Date getGeboorteDatum() {
        return this.geboorteDatum;
    }

    public void setGeboorteDatum(Date geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public Adres getAdres() {
        return this.adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public int getCursistid() {
        return this.cursistid;
    }

    public void setCursistid(int cursistid) {
        this.cursistid = cursistid;
    }

    public Geslacht getGeslacht() {
        return this.geslacht;
    }

    public void setGeslacht(Geslacht geslacht) {
        this.geslacht = geslacht;
    }

    public List<Cursus> getCursus() {
        return this.cursus;
    }

    public void setCursus(List<Cursus> cursus) {
        this.cursus = cursus;
    }

}
