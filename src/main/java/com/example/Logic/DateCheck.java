package com.example.Logic;

import java.time.LocalDate;

public class DateCheck {
    public boolean isValidDate(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr);
        LocalDate now = LocalDate.now();
        if (!date.isAfter(now)) {
            return true;
        }
        return false;
    }

    public boolean checkFormat(String date) {
        if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return true;
        }
        return false;
    }
}
