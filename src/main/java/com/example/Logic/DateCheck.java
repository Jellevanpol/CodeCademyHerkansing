package com.example.Logic;

import java.time.LocalDate;

public class DateCheck {
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
