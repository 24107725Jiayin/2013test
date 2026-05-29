package dao;

import objects.Teacher;
import jdbc.JDBCTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TeacherDao {
    private TeacherDao() {}

    public static int countLike(int attribute, String value) {
        String attr = getAttr(attribute);
        try {
            Connection conn = JDBCTool.getConnection();
            String sql = "SELECT COUNT(*) FROM teachers WHERE "+attr+" LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + value + "%");
            ResultSet rs = ps.executeQuery();
            int count = -1;
            while (rs.next()) {
                count = rs.getInt(1);
            }
            conn.close();
            ps.close();
            rs.close();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return -114514;
        }
    }

    public static List<Teacher> searchLike(int attribute, String value, int page, int rows) {
        String attr = getAttr( attribute);
        List<Teacher> list = new ArrayList<>();

        try {
            Connection conn = JDBCTool.getConnection();
            String sql = "SELECT * FROM teachers WHERE "+attr+" LIKE ? LIMIT ?,?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + value + "%");
            ps.setInt(2, (page - 1) * rows);
            ps.setInt(3, rows);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Teacher teacher = rsToTeacher(rs);
                list.add(teacher);
            }
            conn.close();
            ps.close();
            rs.close();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getAttr(int n){
        switch (n) {
            case 2:
                return  "first_name";
            case 3:
                return  "last_name";
            case 4:
                return  "gender";
            case 5:
                return  "title";
            case 6:
                return  "age";
            case 7:
                return  "birthdate";
            case 8:
                return  "email";
            case 9:
                return  "phone";
            default://include case 1
                return  "teacher_id";
        }
    }

    public static List<Teacher> findAll() {
        String sql = "SELECT * FROM teachers";
        List<Teacher> list = new ArrayList<>();

        try {
            Connection conn = JDBCTool.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(rsToTeacher(rs));
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

    private static Teacher rsToTeacher(ResultSet rs) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(rs.getInt("teacher_id"));
        teacher.setFirstName(rs.getString("first_name"));
        teacher.setLastName(rs.getString("last_name"));
        teacher.setGender(rs.getString("gender"));
        teacher.setTitle(rs.getString("title"));
        teacher.setAge(rs.getInt("age"));
        teacher.setBirthdate(rs.getString("birthdate"));
        teacher.setEmail(rs.getString("email"));
        teacher.setPhone(rs.getString("phone"));
        return teacher;
    }
}