package jdbc;


import java.sql.*;

public class JDBCTool {
    private static final String DB_NAME = "student_management_system";
    private static final String USER = "root";
    private static final String PASSWORD = "Jiayin20060314";
    private static final String URL = "jdbc:mysql://localhost/" + DB_NAME + "?user=" + USER + "&password=" + PASSWORD;
    //update your mysql connection info here

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL connection failed");
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("MySQL connection failed");
            e.printStackTrace();
        }
        return conn;
    }
}