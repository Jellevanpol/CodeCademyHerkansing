package com.example.Logic;

public class PercentageCheck {
    // Deze methode checkt of een percentage niet boven de honderd zit
    public boolean correctPerentage(String percentage) {
        // Hier wordt het percentage teken achter het percentage gesplit van het
        // daadwerkelijke getal
        String[] parts = percentage.split(" ");
        double percentagePart = Double.valueOf(parts[0]);
        if (percentagePart >= 0 && percentagePart <= 100) {
            return true;
        }
        return false;
    }
}
