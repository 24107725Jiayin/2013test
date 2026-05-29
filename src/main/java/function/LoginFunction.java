package function;

import objects.Student;
import objects.User;
import dao.StudentDao;
import dao.UserDao;

import java.util.List;

public class LoginFunction {
    static private User user = null;
    static private Student student = null;
    private LoginFunction(){}

    public static boolean Login(int userID, String password) {
        List<User> users = UserDao.findAll();
        for (User u : users) {
            if (u.getUserId() == userID && u.getPassword().equals(password)) {
                user = u;
                student = StudentDao.findById(u.getStudentId());
                return true;
            }
        }
        return false;
    }

    public static User getUser() {
        return user;
    }
    public static Student getStudent() {
        return student;
    }

}
