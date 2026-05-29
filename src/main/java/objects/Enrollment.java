package objects;

public class Enrollment {
    private int enrollmentId;
    private int courseId;
    private String courseName;
    private int teacherId;
    private String teacherFirstName;
    private String teacherLastName;
    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    private Double grade;
    private String status;

    public Enrollment() {
    }

    public Enrollment(int courseId, int studentId, Double grade, String status) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.grade = grade;
        this.status = status;
    }

    public Enrollment(int enrollmentId, int courseId, String courseName, int teacherId, String teacherFirstName, String teacherLastName, int studentId, String studentFirstName, String studentLastName, Double grade, String status) {
        this.enrollmentId = enrollmentId;
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.studentId = studentId;
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.grade = grade;
        this.status = status;
    }

    public int getEnrollmentId() {
        return enrollmentId;
    }
    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public int getCourseId() {
        return courseId;
    }
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getTeacherId() {
        return teacherId;
    }
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }
    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }
    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }


    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }
    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }
    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public Double getGrade() {
        return grade;
    }
    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId=" + enrollmentId +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", teacherId=" + teacherId +
                ", teacherFirstName='" + teacherFirstName + '\'' +
                ", teacherLastName='" + teacherLastName + '\'' +
                ", studentId=" + studentId +
                ", studentFirstName='" + studentFirstName + '\'' +
                ", studentLastName='" + studentLastName + '\'' +
                ", grade=" + grade +
                ", status='" + status + '\'' +
                '}';
    }

}