package repository;

import edu.oop.db.DatabaseConnection;
import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    public Book findById(Long id) throws SQLException {
        String sql = "SELECT id, author, isbn, available FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getInt("id"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getBoolean("available")
                );
            }
            return null;
        }
    }

    public List<Book> findAvailableBooks() throws SQLException {
        String sql = "SELECT id, author, isbn, available FROM books WHERE available = true";
        List<Book> books = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getBoolean("available")
                ));
            }
        }
        return books;
    }

    public void updateAvailability(Long bookId, boolean available) throws SQLException {
        String sql = "UPDATE books SET available = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, available);
            ps.setLong(2, bookId);
            ps.executeUpdate();
        }
    }
}

