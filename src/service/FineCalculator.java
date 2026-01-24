package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {

    private static final int FINE_PER_DAY = 100; // you can explain this in defense

    public long calculateFine(LocalDate dueDate, LocalDate returnDate) {
        if (returnDate.isAfter(dueDate)) {
            long overdueDays = ChronoUnit.DAYS.between(dueDate, returnDate);
            return overdueDays * FINE_PER_DAY;
        }
        return 0;
    }
}
