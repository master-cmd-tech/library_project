package service;

import config.LibraryConfig;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {

    public double calculateFine(LocalDate dueDate, LocalDate returnDate) {
        if (returnDate.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            return daysLate * LibraryConfig.getInstance().getDailyFine();
        }
        return 0;
    }
}