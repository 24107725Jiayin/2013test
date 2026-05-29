package function;

import dao.CourseDao;
import dao.EnrollmentDao;
import dao.StudentDao;
import dao.TeacherDao;
import objects.Course;
import objects.Enrollment;

import java.util.List;

public class HomepageFunction {
    private HomepageFunction(){}

    private static final int ROWS_PER_PAGE = 10;

    public static List<? extends Object> search(int tableNo,int attribute, String value, int page) {
        switch (tableNo) {
            case 1:
                return StudentDao.searchLike(attribute, value, page, ROWS_PER_PAGE);
            case 2:
                List<Course> courseList = CourseDao.searchLike(attribute, value, page, ROWS_PER_PAGE);
                if (LoginFunction.getStudent() != null){
                    CourseDao.updateAllStatus(courseList, LoginFunction.getStudent());
                }
                return courseList;
            case 3:
                return EnrollmentDao.searchLike(attribute, value, page, ROWS_PER_PAGE);
            case 4:
                return TeacherDao.searchLike(attribute, value, page, ROWS_PER_PAGE);
            default:
                return null;
        }
    }

    public static int totalPages(int tableNo,int attribute, String value) {
        switch (tableNo) {
            case 1:
                return (int) Math.ceil((double) StudentDao.countLike(attribute, value) / ROWS_PER_PAGE);
            case 2:
                return (int) Math.ceil((double) CourseDao.countLike(attribute, value) / ROWS_PER_PAGE);
            case 3:
                return (int) Math.ceil((double) EnrollmentDao.countLike(attribute, value) / ROWS_PER_PAGE);
            case 4:
                return (int) Math.ceil((double) TeacherDao.countLike(attribute, value) / ROWS_PER_PAGE );
            default:
                return -1;
        }
    }

    //operates enroll or drop a course, return a message indicates if the operation is successful
    public static String operation(int studentId, int courseId, String status) {
        switch ( status ){
            case "Unselected":
                if (EnrollmentDao.add(new Enrollment(courseId, studentId, null, "Enrolled"))){
                    return "Enroll successfully";
                } else {
                    return "Error: Enroll failed";
                }
            case "Enrolled":
                if (EnrollmentDao.delete(courseId, studentId)){
                    return "Drop successfully";
                } else {
                    return "Error: Drop failed";
                }
            case "Dropped":
                if (EnrollmentDao.update(courseId, studentId, "Enrolled")){
                    return "Enroll successfully";
                } else {
                    return "Error: Enroll failed";
                }
            case "Completed":
                return "Error: You have already completed this course";
            default:
                return "Error: Invalid status";
        }
    }


}
