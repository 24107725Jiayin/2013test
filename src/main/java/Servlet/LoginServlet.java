package Servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import function.LoginFunction;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(sb.toString(),JsonObject.class);
        int userID = jsonObject.get("userID").getAsInt();
        String password = jsonObject.get("password").getAsString();

        boolean status = LoginFunction.Login(userID, password);

        response.getWriter().write("{\"status\": " + status + "}");
//        if (userID.equals("123") && password.equals("123")){
//            response.getWriter().write("{\"status\": " + true + "}");
//        } else {
//            response.getWriter().write("{\"status\": " + false + "}");
//        }
    }

    public void destroy() {
    }
}
