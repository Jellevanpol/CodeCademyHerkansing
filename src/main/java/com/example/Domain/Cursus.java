package com.example.Domain;

import java.util.List;

import com.example.Domain.Enumerations.Niveau;

public class Cursus {
    private String cursusNaam;
    private String onderwerp;
    private String introText;
    private Niveau niveau;
    private List<Module> modules;
    private List<Cursus> aanbevelingen;
    private List<Cursist> cursist;

    public Cursus(String cursusNaam, String onderwerp, String introText, Niveau niveau, List<Module> modules,
            List<Cursus> aanbevelingen, List<Cursist> cursist) {
        this.cursusNaam = cursusNaam;
        this.onderwerp = onderwerp;
        this.introText = introText;
        this.niveau = niveau;
        this.modules = modules;
        this.aanbevelingen = aanbevelingen;
        this.cursist = cursist;
    }

    public String getCursusNaam() {
        return this.cursusNaam;
    }

    public void setCursusNaam(String cursusNaam) {
        this.cursusNaam = cursusNaam;
    }

    public String getOnderwerp() {
        return this.onderwerp;
    }

    public void setOnderwerp(String onderwerp) {
        this.onderwerp = onderwerp;
    }

    public String getIntroText() {
        return this.introText;
    }

    public void setIntroText(String introText) {
        this.introText = introText;
    }

    public Niveau getNiveau() {
        return this.niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public List<Module> getModules() {
        return this.modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Cursus> getAanbevelingen() {
        return this.aanbevelingen;
    }

    public void setAanbevelingen(List<Cursus> aanbevelingen) {
        this.aanbevelingen = aanbevelingen;
    }

    public List<Cursist> getCursist() {
        return this.cursist;
    }

    public void setCursist(List<Cursist> cursist) {
        this.cursist = cursist;
    }
}
