package objects;

public class User {
    private int userId;
    private String username;
    private String password;
    private int studentId;

    public User() {
    }

    public User(int userId, String username, String password, int studentId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.studentId = studentId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", studentId=" + studentId +
                '}';
    }
}