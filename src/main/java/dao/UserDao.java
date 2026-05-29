package dao;

import objects.User;
import jdbc.JDBCTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private UserDao() {}

    public static List<User> findAll() {
        String sql = "SELECT * FROM users";
        List<User> list = new ArrayList<>();

        try {
            Connection conn = JDBCTool.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(rsToUser(rs));
            }
            conn.close();
            st.close();
            rs.close();

            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static User rsToUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setStudentId(rs.getInt("student_id"));
        return user;
    }
}