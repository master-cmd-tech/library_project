package service;

import edu.oop.db.DatabaseConnection;
import entities.Book;
import entities.Loan;
import entities.Member;
import repository.BookRepository;
import repository.LoanRepository;
import repository.MemberRepository;

import exception.BookAlreadyOnLoanException;
import exception.MemberNotFoundException;
import exception.LoanOverdueException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanService {

    private final BookRepository bookRepository = new BookRepository();
    private final MemberRepository memberRepository = new MemberRepository();
    private final LoanRepository loanRepository = new LoanRepository();
    private final FineCalculator fineCalculator = new FineCalculator();

    public LoanService() {}

    public void borrowBook(int bookId, int memberId) throws SQLException {

        Book book = bookRepository.findById(bookId);
        if (book == null) {
            throw new IllegalArgumentException("Book not found");
        }

        if (!book.isAvailable()) {
            throw new BookAlreadyOnLoanException();
        }

        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        Loan loan = new Loan(
                bookId,
                memberId,
                LocalDate.now(),
                LocalDate.now().plusDays(14)
        );

        loanRepository.save(loan);
        bookRepository.updateAvailability(bookId, false);
    }

    public double returnBook(int loanId, LocalDate dueDate) throws SQLException {
        LocalDate returnDate = LocalDate.now();

        if (returnDate.isAfter(dueDate)) {
            throw new LoanOverdueException();
        }

        double fine = fineCalculator.calculateFine(dueDate, returnDate);
        loanRepository.closeLoan(loanId, returnDate);

        return fine;
    }

    public void viewLoansByMember(int memberId) throws SQLException {

        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new MemberNotFoundException();
        }

        List<Loan> loans = loanRepository.findActiveLoansByMember(memberId);

        if (loans.isEmpty()) {
            System.out.println("No active loans for member: " + member.getName());
            return;
        }

        System.out.println("\nActive loans for " + member.getName() + ":");

        loans.forEach(loan -> System.out.println(
                "Loan ID: " + loan.getId() +
                        ", Book ID: " + loan.getBookId() +
                        ", Due date: " + loan.getDueDate()
        ));
    }

}
