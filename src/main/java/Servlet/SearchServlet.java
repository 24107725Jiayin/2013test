package Servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import function.HomepageFunction;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/api/search")
public class SearchServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");

        int tableNo = Integer.parseInt(request.getParameter("tableNo"));
        int page = Integer.parseInt(request.getParameter("pageNo"));
        int attribute = Integer.parseInt(request.getParameter("attribute"));
        String value = request.getParameter("value");

        Map<String, Object> result = new HashMap<>();
        int totalPage = HomepageFunction.totalPages(tableNo, attribute, value);
        List<Object> list = (List<Object>) HomepageFunction.search(tableNo,attribute,value,page);
        result.put("totalPage",totalPage);
        result.put("list",list);

        Gson gson = new Gson();
        String json = gson.toJson( result);
        response.getWriter().write(json);
    }

    public void destroy() {
    }
}