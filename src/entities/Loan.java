package entities;

import java.time.LocalDate;

public class Loan {
    private int id;
    private int bookId;
    private int memberId;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public Loan(){

    }

    public Loan(int bookId, int memberId, LocalDate loanDate, LocalDate dueDate) {
        setBookId(bookId);
        setMemberId(memberId);
        setLoanDate(loanDate);
        setDueDate(dueDate);
    }

    public Loan(int id, int bookId, int memberId, LocalDate loanDate, LocalDate dueDate,LocalDate returnDate ) {
        this(bookId, memberId, loanDate, dueDate);
        setId(id);
        setReturnDate(returnDate);
    }

    public int getId() { return id; }

    public int getBookId() { return bookId; }

    public int getMemberId() { return memberId; }

    public LocalDate getLoanDate() { return loanDate;}

    public LocalDate getDueDate() { return dueDate;}

    public LocalDate getReturnDate() { return returnDate;}


    public void setId(int id) { this.id = id; }

    public void setBookId(int bookId) { this.bookId = bookId; }

    public void setMemberId(int memberId) { this.memberId = memberId; }

    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate;}

    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
