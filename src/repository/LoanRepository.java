package repository;

import edu.oop.db.DatabaseConnection;
import entities.Book;
import entities.Loan;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LoanRepository implements CrudRepository<Book, Integer> {

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

    public List<Loan> findActiveLoansByMember(int memberId) throws SQLException {

        String sql = """
            SELECT * FROM loans
            WHERE member_id = ? AND return_date IS NULL
            """;

        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, memberId);

        ResultSet rs = ps.executeQuery();
        List<Loan> loans = new ArrayList<>();

        while (rs.next()) {
            loans.add(new Loan(
                    rs.getInt("id"),
                    rs.getInt("book_id"),
                    rs.getInt("member_id"),
                    rs.getDate("loan_date").toLocalDate(),
                    rs.getDate("due_date").toLocalDate(),
                    rs.getDate("return_date") != null
                            ? rs.getDate("return_date").toLocalDate()
                            : null
            ));
        }

        return loans;
    }

    @Override
    public Optional<Book> findById(Integer integer) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Book> findAll() throws SQLException {
        return List.of();
    }

    @Override
    public void save(Book entity) throws SQLException {

    }

    @Override
    public void update(Book entity) throws SQLException {

    }

    @Override
    public void deleteById(Integer integer) throws SQLException {

    }
}
