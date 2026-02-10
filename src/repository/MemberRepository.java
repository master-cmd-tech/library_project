package repository;

import edu.oop.db.DatabaseConnection;
import entities.Book;
import entities.Member;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class MemberRepository implements CrudRepository<Book, Integer> {

    public Member findById(int id) throws SQLException {
        String sql = "SELECT * FROM members WHERE id = ?";
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Member(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
            );
        }
        return null;
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
