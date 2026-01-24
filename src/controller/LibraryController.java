package controller;

import service.LoanService;
import  model.Loan;

public class LibraryController {

    private final LoanService loanService = new LoanService();

    public void borrowBook(Long bookId, Long memberId) {
        try {
            loanService.borrowBook(bookId, memberId);
            System.out.println("Book borrowed successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnBook(Loan loan) {
        try {
            loanService.returnBook(loan);
            System.out.println("Book returned successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
