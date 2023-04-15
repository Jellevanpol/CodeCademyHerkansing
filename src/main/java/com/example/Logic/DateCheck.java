package com.example.Logic;

import java.time.LocalDate;

public class DateCheck {
    // Deze methode checkt of de datum niet na de localdate ligt
    // en of deze in de goede format is (YYYY-MM-DD)
    public boolean isValidDate(String dateStr) {
        if (dateStr.matches("\\d{4}-\\d{2}-\\d{2}")) {
            LocalDate date = LocalDate.parse(dateStr);
            LocalDate now = LocalDate.now();
            if (!date.isAfter(now)) {
                return true;
            }
        }
        return false;
    }
}
