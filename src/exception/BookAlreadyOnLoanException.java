package exception;

public class BookAlreadyOnLoanException extends RuntimeException {

    public BookAlreadyOnLoanException() {
        super("The book is already on loan.");
    }

    public BookAlreadyOnLoanException(String message) {
        super(message);
    }
}