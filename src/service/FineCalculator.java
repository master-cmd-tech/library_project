package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {

    private static final double DAILY_FINE = 1.5;

    public double calculateFine(LocalDate dueDate, LocalDate returnDate) {
        if (returnDate.isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
            return daysLate * DAILY_FINE;
        }
        return 0;
    }
}
