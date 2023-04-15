package com.example.Logic;

public class PercentageCheck {
    public boolean correctPerentage(String percentage){
        String[] parts = percentage.split(" ");
        double percentagePart = Double.valueOf(parts[0]);
        if(percentagePart >= 0 && percentagePart <= 100){
            return true;
        }
        return false;
    }
}
    