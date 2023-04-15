package com.example.Logic;

public class EmailCheck {
    // Deze methode checkt of de email in de correcte format staat
    public boolean correctEmail(String emailAdres) {
        if (emailAdres.matches("^[^@]+@[^\\.]+\\..+$")) {
            return true;
        }
        return false;
    }
}
