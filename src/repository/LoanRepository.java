package repository;

import edu.oop.db.DatabaseConnection;
import entities.Loan;

import java.sql.*;
import java.time.LocalDate;

public class LoanRepository {

    public void save(Loan loan) throws SQLException {
        String sql = """
                INSERT INTO loans (book_id, member_id, loan_date, due_date)
                VALUES (?, ?, ?, ?)
                """;

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, loan.getBookId());
        ps.setInt(2, loan.getMemberId());
        ps.setDate(3, Date.valueOf(loan.getLoanDate()));
        ps.setDate(4, Date.valueOf(loan.getDueDate()));
        ps.executeUpdate();
    }

    public void closeLoan(int loanId, LocalDate returnDate) throws SQLException {
        String sql = "UPDATE loans SET return_date = ? WHERE id = ?";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
        ps.setDate(1, Date.valueOf(returnDate));
        ps.setInt(2, loanId);
        ps.executeUpdate();
    }
}
