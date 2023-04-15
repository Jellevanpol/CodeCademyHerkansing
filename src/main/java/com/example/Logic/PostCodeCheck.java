package com.example.Logic;

public class PostCodeCheck {
    // Deze methode checkt of de postoce in de correcte format staat (e.g. 1234 AB)
    public boolean correctPostcode(String postCode) {
        if (postCode.matches("^[1-9][0-9]{3}\\s?[A-Z]{2}$")) {
            return true;
        }
        return false;
    }
}
