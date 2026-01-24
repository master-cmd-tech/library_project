package repository;

import edu.oop.db.DatabaseConnection;
import model.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberRepository {

    public Member findById(Long id) throws SQLException {
        String sql = "SELECT id, name, email FROM members WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Member(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
            return null;
        }
    }
}

