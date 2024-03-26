package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/main")
public class MainServlet extends HttpServlet  {

    @Override
    public void init() throws ServletException {
        System.out.println("Starting Servlet!!!");
    }

    @Override
    public void destroy() {
        System.out.println("Stopping Servlet!!!");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub
        super.service(req, resp);
        System.out.println("Request method " + req.getMethod());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter write =resp.getWriter();
        write.println("<h1>Hello -TDTU");
        write.close();
    }

}