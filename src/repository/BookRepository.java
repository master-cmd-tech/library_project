package repository;

import edu.oop.db.DatabaseConnection;
import entities.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookRepository implements CrudRepository<Book, Integer> {

    public Book findById(int id) throws SQLException {
        String sql = "SELECT * FROM books WHERE id = ?";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Book(
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("isbn"),
                    rs.getBoolean("available")
            );
        }
        return null;
    }

    public void updateAvailability(int bookId, boolean available) throws SQLException {
        String sql = "UPDATE books SET available = ? WHERE id = ?";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
        ps.setBoolean(1, available);
        ps.setInt(2, bookId);
        ps.executeUpdate();
    }

    @Override
    public Optional<Book> findById(Integer integer) throws SQLException {
        return Optional.empty();
    }

    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();

        String sql = "SELECT * FROM books";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            books.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("isbn"),
                    rs.getBoolean("available")
            ));
        }

        return books;
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
