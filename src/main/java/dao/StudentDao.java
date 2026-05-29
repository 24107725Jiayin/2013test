package dao;

import objects.Student;
import jdbc.JDBCTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private StudentDao() {}

    public static int countLike(int attribute, String value) {
        String attr = getAttr(attribute);
        try {
            Connection conn = JDBCTool.getConnection();
            PreparedStatement ps;
            String sql = "SELECT COUNT(*) FROM students WHERE "+ attr +" LIKE ?";
            ps = conn.prepareStatement(sql);
            //when value is empty return all(LIKE %%)
            ps.setString(1, "%" + value + "%");
            ResultSet rs = ps.executeQuery();
            int count = -1;
            if (rs.next()) {
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

    public static List<Student> searchLike(int attribute, String value, int page, int rows) {
        String attr = getAttr( attribute);
        List<Student> list = new ArrayList<>();

        try {
            Connection conn = JDBCTool.getConnection();
            PreparedStatement ps;
            String sql = "SELECT * FROM students WHERE "+ attr +" LIKE ? LIMIT ?,?";
            ps = conn.prepareStatement(sql);
            //when value is empty return all(LIKE %%)
            ps.setString(1, "%" + value + "%");
            ps.setInt(2, (page - 1) * rows);
            ps.setInt(3, rows);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = rsToStudent(rs);
                list.add(student);
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
                return  "major";
            case 6:
                return  "age";
            case 7:
                return  "birthdate";
            case 8:
                return  "email";
            case 9:
                return  "phone";
            default://include case 1
                return  "student_id";
        }

    }

    public static Student findById(int studentId) {
        String sql = "SELECT * FROM students WHERE student_id = ?";
        try {
            Connection conn = JDBCTool.getConnection();
            PreparedStatement ps = conn.prepareStatement( sql);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Student student = rsToStudent(rs);
                conn.close();
                ps.close();
                rs.close();
                return student;
            } else {
                conn.close();
                ps.close();
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Student> findAll() {
        String sql = "SELECT * FROM students";
        List<Student> list = new ArrayList<>();

        try {
            Connection conn = JDBCTool.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(rsToStudent(rs));
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

    private static Student rsToStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setStudentId(rs.getInt("student_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        student.setGender(rs.getString("gender"));
        student.setMajor(rs.getString("major"));
        student.setAge(rs.getInt("age"));
        student.setBirthdate(rs.getString("birthdate"));
        student.setEmail(rs.getString("email"));
        student.setPhone(rs.getString("phone"));
        return student;
    }
}