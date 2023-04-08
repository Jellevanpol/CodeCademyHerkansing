package com.example.Domain;

public class Webcast {
    private double duur;
    private String url;
    private String sprekerNaam;
    private String organisatieSpreker;

    public Webcast(double duur, String url, String sprekerNaam, String organisatieSpreker) {
        this.duur = duur;
        this.url = url;
        this.sprekerNaam = sprekerNaam;
        this.organisatieSpreker = organisatieSpreker;
    }

    public double getDuur() {
        return this.duur;
    }

    public void setDuur(double duur) {
        this.duur = duur;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSprekerNaam() {
        return this.sprekerNaam;
    }

    public void setSprekerNaam(String sprekerNaam) {
        this.sprekerNaam = sprekerNaam;
    }

    public String getOrganisatieSpreker() {
        return this.organisatieSpreker;
    }

    public void setOrganisatieSpreker(String organisatieSpreker) {
        this.organisatieSpreker = organisatieSpreker;
    }

}
