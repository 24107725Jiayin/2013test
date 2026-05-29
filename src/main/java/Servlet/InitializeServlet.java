package Servlet;

import com.google.gson.Gson;
import function.LoginFunction;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/init")
public class InitializeServlet extends HttpServlet {
    private String message;
    public void init() {
        message = "Hello World!";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> result = new HashMap<>();
        result.put("stu", LoginFunction.getStudent());
        result.put("user", LoginFunction.getUser());
        response.getWriter().write(new Gson().toJson(result));
    }
    public void destroy() {
    }
}
