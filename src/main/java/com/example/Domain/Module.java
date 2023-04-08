package com.example.Domain;

public class Module {
    private String versie;
    private String naamContact;
    private String emailContact;
    private int volgNummer;

    public Module(String versie, String naamContact, String emailContact, int volgNummer) {
        this.versie = versie;
        this.naamContact = naamContact;
        this.emailContact = emailContact;
        this.volgNummer = volgNummer;
    }

    public String getVersie() {
        return this.versie;
    }

    public void setVersie(String versie) {
        this.versie = versie;
    }

    public String getNaamContact() {
        return this.naamContact;
    }

    public void setNaamContact(String naamContact) {
        this.naamContact = naamContact;
    }

    public String getEmailContact() {
        return this.emailContact;
    }

    public void setEmailContact(String emailContact) {
        this.emailContact = emailContact;
    }

    public int getVolgNummer() {
        return this.volgNummer;
    }

    public void setVolgNummer(int volgNummer) {
        this.volgNummer = volgNummer;
    }

}
