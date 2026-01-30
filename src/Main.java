import service.LoanService;
import service.FineCalculator;
import repository.BookRepository;
import repository.MemberRepository;
import repository.LoanRepository;
import exception.BookAlreadyOnLoanException;
import exception.MemberNotFoundException;
import exception.LoanOverdueException;
import entities.Book;
import entities.Loan;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try (
                Connection connection = DatabaseConnection.getConnection()
        ) {

            // Repositories (JDBC inside, NOT here)
            BookRepository bookRepository = new BookRepository(connection);
            MemberRepository memberRepository = new MemberRepository(connection);
            LoanRepository loanRepository = new LoanRepository(connection);

            // Services
            FineCalculator fineCalculator = new FineCalculator();
            LoanService loanService = new LoanService(
                    bookRepository,
                    memberRepository,
                    loanRepository,
                    fineCalculator
            );

            System.out.println("=== AVAILABLE BOOKS ===");
            List<Book> availableBooks = bookRepository.findAvailableBooks();
            availableBooks.forEach(System.out::println);

            int memberId = 1;
            int bookId = 2;

            System.out.println("\n=== BORROW BOOK ===");
            loanService.borrowBook(memberId, bookId);
            System.out.println("Book borrowed successfully.");

            System.out.println("\n=== CURRENT LOANS FOR MEMBER ===");
            List<Loan> loans = loanService.getLoansByMember(memberId);
            loans.forEach(System.out::println);

            System.out.println("\n=== RETURN BOOK ===");
            loanService.returnBook(bookId);
            System.out.println("Book returned successfully.");

        } catch (BookAlreadyOnLoanException e) {
            System.out.println("ERROR: Book is already on loan.");

        } catch (MemberNotFoundException e) {
            System.out.println("ERROR: Member not found.");

        } catch (LoanOverdueException e) {
            System.out.println("ERROR: Loan is overdue. Fine required.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
