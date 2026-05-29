package Servlet;

import function.HomepageFunction;
import function.LoginFunction;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet("/api/operation")
public class OperationServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        int courseId = Integer.parseInt(request.getParameter("courseId"));
        String status = request.getParameter("status");

        String message = HomepageFunction.operation(LoginFunction.getStudent().getStudentId(), courseId, status);
        response.getWriter().write("{\"message\": \"" + message + "\"}");
    }
    public void destroy() {
    }
}
