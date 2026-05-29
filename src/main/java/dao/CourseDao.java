package dao;

import objects.Course;
import jdbc.JDBCTool;
import objects.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {
    private CourseDao(){}

    public static int countLike(int attribute, String value) {
        String attr = getAttr(attribute);
        try {
            Connection conn = JDBCTool.getConnection();
            String sql = "SELECT COUNT(*) " +
                    "FROM courses AS c " +
                    "INNER JOIN teachers AS t ON c.teacher_id = t.teacher_id " +
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

    public static List<Course> searchLike(int attribute, String value, int page, int rows) {
        String attr = getAttr(attribute);
        List<Course> list = new ArrayList<>();
        try {
            Connection conn = JDBCTool.getConnection();
            String sql = "SELECT c.course_id,c.course_name,c.teacher_id,t.first_name,t.last_name,c.credit,c.course_time " +
                    "FROM courses AS c " +
                    "INNER JOIN teachers AS t ON c.teacher_id = t.teacher_id " +
                    "WHERE "+attr+" LIKE ? LIMIT ?,?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + value + "%");
            ps.setInt(2, (page - 1) * rows);
            ps.setInt(3, rows);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = rsToCourse(rs);
                list.add(course);
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
                return  "course_name";
            case 3:
                return  "teacher_id";
            case 4:
                return  "first_name";
            case 5:
                return  "last_name";
            case 6:
                return  "credit";
            case 7:
                return  "course_time";
            default://include case 1
                return  "course_id";
        }
    }

    public static List<Course> findAll() {
        String sql = "SELECT c.course_id,c.course_name,c.teacher_id,t.first_name,t.last_name,c.credit,c.course_time " +
                "FROM courses AS c " +
                "INNER JOIN teachers AS t ON c.teacher_id = t.teacher_id";
        List<Course> list = new ArrayList<>();

        try {
            Connection conn = JDBCTool.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                list.add(rsToCourse(rs));
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

    private static Course rsToCourse(ResultSet rs) throws SQLException {
        Course course = new Course();
        course.setCourseId(rs.getInt("course_id"));
        course.setCourseName(rs.getString("course_name"));
        course.setTeacherId(rs.getInt("teacher_id"));
        course.setTeacherFirstName(rs.getString("first_name"));
        course.setTeacherLastName(rs.getString("last_name"));
        course.setCredit(rs.getInt("credit"));
        course.setCourseTime(rs.getString("course_time"));
        return course;
    }

    public static boolean updateAllStatus(List<Course> courses, Student stu){
        for(Course course : courses){
            course.setStatus("Unselected");
        }
        String sql = "SELECT course_id,status " +
                "FROM enrollments WHERE student_id = ?";
        try {
            Connection conn = JDBCTool.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, stu.getStudentId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                for(Course course : courses){
                    if(course.getCourseId() == rs.getInt("course_id")){
                        course.setStatus(rs.getString("status"));
                    }
                }
            }
            conn.close();
            ps.close();
            rs.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}