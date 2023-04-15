package com.example.Logic;

public class URLCheck {
    // Deze methode checkt of de URL in de correcte format staat
    public boolean correctURL(String url) {
        if (url.matches("^(https?://)?[a-zA-Z]+\\.[a-zA-Z]+\\.[a-zA-Z]+$")) {
            return true;
        }
        return false;
    }
}
