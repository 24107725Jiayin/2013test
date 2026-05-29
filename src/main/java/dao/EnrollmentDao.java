package dao;

import objects.Enrollment;
import jdbc.JDBCTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDao {
    private EnrollmentDao() {
    }

    public static int countLike(int attribute, String value) {
        String attr = getAttr(attribute);
        try {
            Connection conn = JDBCTool.getConnection();
            String sql = "SELECT COUNT(*) " +
                    "FROM enrollments AS e " +
                    "INNER JOIN courses AS c ON e.course_id=c.course_id " +
                    "INNER JOIN teachers AS t ON c.teacher_id=t.teacher_id " +
                    "INNER JOIN students AS s ON e.student_id=s.student_id " +
                    "WHERE "+attr+" LIKE ?";
            PreparedStatement ps = conn.prepareStatement(sql);
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

    public static List<Enrollment> searchLike(int attribute, String value, int page, int rows) {
        String attr = getAttr( attribute);
        List<Enrollment> list = new ArrayList<>();

        try {
            Connection conn = JDBCTool.getConnection();
            String sql = "SELECT e.enrollment_id, e.course_id, c.course_name, " +
                    "c.teacher_id, t.first_name AS tf, t.last_name AS tl," +
                    "e.student_id, s.first_name AS sf, s.last_name AS sl," +
                    "e.status, e.grade " +
                    "FROM enrollments AS e " +
                    "INNER JOIN courses AS c ON e.course_id=c.course_id " +
                    "INNER JOIN teachers AS t ON c.teacher_id=t.teacher_id " +
                    "INNER JOIN students AS s ON e.student_id=s.student_id " +
                    "WHERE "+attr+" LIKE ? LIMIT ?,?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + value + "%");
            ps.setInt(2, (page - 1) * rows);
            ps.setInt(3, rows);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Enrollment enrollment = rsToEnrollment(rs);
                list.add(enrollment);
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
                return  "e.course_id";
            case 3:
                return  "c.course_name";
            case 4:
                return  "c.teacher_id";
            case 5:
                return  "t.first_name";
            case 6:
                return  "t.last_name";
            case 7:
                return  "e.student_id";
            case 8:
                return  "s.first_name";
            case 9:
                return  "s.last_name";
            case 10:
                return  "e.grade";
            case 11:
                return  "e.status";
            default://include case 1
                return  "e.enrollment_id";
        }
    }

    public static boolean add(Enrollment enrollment) {
        String sql = "INSERT INTO enrollments (student_id, course_id, status) VALUES (?, ?, ?)";

        try {
            Connection conn = JDBCTool.getConnection();
            PreparedStatement ps = conn.prepareStatement( sql);
            ps.setInt(1, enrollment.getStudentId());
            ps.setInt(2, enrollment.getCourseId());
            ps.setString(3, enrollment.getStatus());
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean delete(int courseId, int studentId) {
        String sql = "DELETE FROM enrollments WHERE course_id = ? AND student_id = ?";
        try {
            Connection conn = JDBCTool.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, courseId);
            ps.setInt(2, studentId);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(int courseId, int studentId, String status) {
        String sql = "UPDATE enrollment SET status = ? WHERE course_id = ? AND student_id = ?";
        try {
            Connection conn = JDBCTool.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, courseId);
            ps.setInt(3, studentId);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Enrollment> findAll() {
        String sql = "SELECT e.enrollment_id, e.course_id, c.course_name, " +
                "c.teacher_id, t.first_name AS tf, t.last_name AS tl," +
                "e.student_id, s.first_name AS sf, s.last_name AS sl," +
                "e.status, e.grade " +
                "FROM enrollments AS e " +
                "INNER JOIN courses AS c ON e.course_id=c.course_id " +
                "INNER JOIN teachers AS t ON c.teacher_id=t.teacher_id " +
                "INNER JOIN students AS s ON e.student_id=s.student_id";
        List<Enrollment> list = new ArrayList<>();

        try {
            Connection conn = JDBCTool.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(rsToEnrollment(rs));
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


    private static Enrollment rsToEnrollment(ResultSet rs) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(rs.getInt("enrollment_id"));
        enrollment.setCourseId(rs.getInt("course_id"));
        enrollment.setCourseName(rs.getString("course_name"));
        enrollment.setTeacherId(rs.getInt("teacher_id"));
        enrollment.setTeacherFirstName(rs.getString("tf"));
        enrollment.setTeacherLastName(rs.getString("tl"));
        enrollment.setStudentId(rs.getInt("student_id"));
        enrollment.setStudentFirstName(rs.getString("sf"));
        enrollment.setStudentLastName(rs.getString("sl"));
        enrollment.setStatus(rs.getString("status"));
        enrollment.setGrade(rs.getDouble("grade"));
        return enrollment;
    }
}