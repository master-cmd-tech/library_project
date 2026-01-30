package exception;

public class LoanOverdueException extends RuntimeException {

    public LoanOverdueException() {
        super("Loan is overdue. Fine is required.");
    }

    public LoanOverdueException(String message) {
        super(message);
    }
}

