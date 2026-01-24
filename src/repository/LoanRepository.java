package repository;

import edu.oop.db.DatabaseConnection;
import model.Loan;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanRepository {

    public void save(Loan loan) throws SQLException {
        String sql = """
                INSERT INTO loans (book_id, member_id, loan_date, due_date)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, loan.getBookId());
            ps.setLong(2, loan.getMemberId());
            ps.setDate(3, Date.valueOf(loan.getLoanDate()));
            ps.setDate(4, Date.valueOf(loan.getDueDate()));
            ps.executeUpdate();
        }
    }

    public List<Loan> findActiveLoansByMember(Long memberId) throws SQLException {
        String sql = """
                SELECT id, book_id, member_id, loan_date, due_date
                FROM loans
                WHERE member_id = ? AND return_date IS NULL
                """;

        List<Loan> loans = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, memberId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                loans.add(new Loan(
                        rs.getInt("id"),
                        rs.getInt("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("loan_date").toLocalDate(),
                        rs.getDate("due_date").toLocalDate(),
                        null
                ));
            }
        }
        return loans;
    }

    public void closeLoan(Long loanId, LocalDate returnDate) throws SQLException {
        String sql = "UPDATE loans SET return_date = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(returnDate));
            ps.setLong(2, loanId);
            ps.executeUpdate();
        }
    }
}

