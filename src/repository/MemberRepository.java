package repository;

import edu.oop.db.DatabaseConnection;
import entities.Member;

import java.sql.*;

public class MemberRepository {

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
}
