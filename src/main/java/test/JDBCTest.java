package test;

import function.LoginFunction;
import objects.Course;
import objects.Student;
import objects.User;
import dao.*;

import java.util.List;
/*
 * this class is to test if the dao can get data from database
 */
public class JDBCTest {
    public static void main(String[] args) {
        System.out.println(LoginFunction.Login(36,"Amber$Gold#11"));
    }

    private static void testCourseDao(){//test completed
    	List<Course> List = CourseDao.findAll();
        for( Course course : List){
            System.out.println(course.toString());
        }
    }
    private static void testStudentDao(){//test completed
    	List<Student> List = StudentDao.findAll();
        for( Student student : List){
            System.out.println(student.toString());
        }
    }
    private static void teatTeacherDao(){//test completed
    	List<objects.Teacher> List = TeacherDao.findAll();
        for( objects.Teacher teacher : List){
            System.out.println(teacher.toString());
        }
    }
    private static void testEnrollmentDao(){//test completed
    	List<objects.Enrollment> List = EnrollmentDao.findAll();
        for( objects.Enrollment enrollment : List){
            System.out.println(enrollment.toString());
        }
    }
    private static void testUserDao(){//test completed
    	List<User> List = UserDao.findAll();
        for( User user : List){
            System.out.println(user.toString());
        }
    }

}
