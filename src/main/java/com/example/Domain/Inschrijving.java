package com.example.Domain;

public class Inschrijving {
    private String datum;
    private Cursist cursist;
    private Cursus cursus;

    public Inschrijving(String datum, Cursist cursist, Cursus cursus) {
        this.datum = datum;
        this.cursist = cursist;
        this.cursus = cursus;
    }

    public String getDatum() {
        return this.datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public Cursist getCursist() {
        return this.cursist;
    }

    public void setCursist(Cursist cursist) {
        this.cursist = cursist;
    }

    public Cursus getCursus() {
        return this.cursus;
    }

    public void setCursus(Cursus cursus) {
        this.cursus = cursus;
    }

}
