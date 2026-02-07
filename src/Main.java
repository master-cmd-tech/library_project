import edu.oop.db.DatabaseConnection;

import controller.LibraryController;
import repository.BookRepository;
import repository.LoanRepository;
import repository.MemberRepository;
import entities.Book;
import entities.Loan;
import entities.Member;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BookRepository bookRepo = new BookRepository();
        MemberRepository memberRepo = new MemberRepository();
        LoanRepository loanRepo = new LoanRepository();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n LIBRARY MENU ");
            System.out.println("1. List available books");
            System.out.println("2. Borrow a book");
            System.out.println("3. Return a book");
            System.out.println("4. View member loans");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.println("\n=== AVAILABLE BOOKS ===");
                        for (int i = 1; i <= 10; i++) {
                            Book book = bookRepo.findById(i);
                            if (book != null && book.isAvailable()) {
                                System.out.println(i + ". " + book.getTitle() + " by " + book.getAuthor());
                            }
                        }
                    }
                    case 2 -> {
                        System.out.print("Enter book ID to borrow: ");
                        int bookId = scanner.nextInt();
                        System.out.print("Enter member ID: ");
                        int memberId = scanner.nextInt();

                        Book book = bookRepo.findById(bookId);
                        Member member = memberRepo.findById(memberId);

                        if (book != null && book.isAvailable() && member != null) {
                            Loan loan = new Loan(bookId, memberId, LocalDate.now(), LocalDate.now().plusDays(14));
                            loanRepo.save(loan);
                            bookRepo.updateAvailability(bookId, false);
                            System.out.println("Book borrowed successfully: " + book.getTitle());
                        } else {
                            System.out.println("Cannot borrow book. Either it's unavailable or member not found.");
                        }
                    }
                    case 3 -> {
                        System.out.print("Enter loan ID to return: ");
                        int loanId = scanner.nextInt();
                        System.out.print("Enter book ID: ");
                        int bookId = scanner.nextInt();

                        loanRepo.closeLoan(loanId, LocalDate.now());
                        bookRepo.updateAvailability(bookId, true);
                        System.out.println("Book returned successfully.");
                    }
                    case 4 -> {
                        System.out.print("Enter member ID to view loans: ");
                        int memberId = scanner.nextInt();

                        LibraryController controller = new LibraryController();
                        controller.viewMemberLoans(memberId);
                    }
                    case 5 -> {
                        System.out.println("Exiting...");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice, try again.");
                }
            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}