package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class LoginServlet extends HttpServlet  {
    private HashMap<String, String> userList;

    @Override
    public void init() throws ServletException {
        System.out.println("Starting Login Servlet!!!");
        System.out.println("Initializing user list....");
        userList = new HashMap<>();
        userList.put("tan", "2211");
    }

    @Override
    public void destroy() {
        System.out.println("Deleting Servlet!!!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter pw = res.getWriter();
        res.setContentType("text/html");
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        if (userList.get(user).equals(pass))
            pw.println("Login Success...!");
        else
            pw.println("Login Failed...!");
        pw.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}