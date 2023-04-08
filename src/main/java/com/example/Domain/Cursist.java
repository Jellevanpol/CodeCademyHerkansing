package com.example.Domain;

import java.util.Date;
import java.util.List;

import com.example.Domain.Enumerations.Geslacht;

public class Cursist {
    private String emailAdres;
    private String naam;
    private Date geboorteDatuml;
    private Adres adres;
    private int cursistid;
    private Geslacht geslacht;
    private List<Cursus> cursus;

    public Cursist(String emailAdres, String naam, Date geboorteDatuml, Adres adres, int cursistid, Geslacht geslacht,
            List<Cursus> cursus) {
        this.emailAdres = emailAdres;
        this.naam = naam;
        this.geboorteDatuml = geboorteDatuml;
        this.adres = adres;
        this.cursistid = cursistid;
        this.geslacht = geslacht;
        this.cursus = cursus;
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

    public Date getGeboorteDatuml() {
        return this.geboorteDatuml;
    }

    public void setGeboorteDatuml(Date geboorteDatuml) {
        this.geboorteDatuml = geboorteDatuml;
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
