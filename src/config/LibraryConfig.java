package config;

public class LibraryConfig {

    private static LibraryConfig instance;

    private final int loanDays;
    private final double dailyFine;

    private LibraryConfig() {
        this.loanDays = 14;
        this.dailyFine = 1.5;
    }

    public static LibraryConfig getInstance() {
        if (instance == null) {
            instance = new LibraryConfig();
        }
        return instance;
    }

    public int getLoanDays() {
        return loanDays;
    }

    public double getDailyFine() {
        return dailyFine;
    }
}