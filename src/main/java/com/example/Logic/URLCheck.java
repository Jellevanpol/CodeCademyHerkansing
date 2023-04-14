package com.example.Logic;

public class URLCheck {
    public boolean correctURL(String url){
        if(url.matches("^(https?://)?[a-zA-Z]+\\.[a-zA-Z]+\\.[a-zA-Z]+$")){
            return true;
        }
        return false;
    }
}
