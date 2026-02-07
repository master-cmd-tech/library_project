package controller;

import service.LoanService;

import java.time.LocalDate;

public class LibraryController {

    private LoanService loanService = new LoanService();

    public void borrowBook(int bookId, int memberId) {
        try {
            loanService.borrowBook(bookId, memberId);
            System.out.println("Book borrowed successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void returnBook(int loanId, LocalDate dueDate) {
        try {
            double fine = loanService.returnBook(loanId, dueDate);
            System.out.println("Fine: " + fine);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void viewMemberLoans(int memberId) {
        try {
            loanService.viewLoansByMember(memberId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
