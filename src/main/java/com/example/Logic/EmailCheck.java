package com.example.Logic;

public class EmailCheck {
    public boolean correctEmail(String emailAdres){
        if(emailAdres.matches("^[^@]+@[^\\.]+\\..+$")){
            return true;
        }
        return false;
    }
}
