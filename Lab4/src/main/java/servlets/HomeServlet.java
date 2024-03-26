package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/home")
public class HomeServlet extends HttpServlet  {

    @Override
    public void init() throws ServletException {
        System.out.println("Starting Home Servlet!!!");
    }

    @Override
    public void destroy() {
        System.out.println("Stopping Servlet!!!");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");
        if (page != null) {
            if (page.equals("about")) {
                request.getRequestDispatcher("/WEB-INF/jsp/about.jsp").forward(request, response);
                return;
            } else if (page.equals("contact")) {
                request.getRequestDispatcher("/WEB-INF/jsp/contact.jsp").forward(request, response);
                return;
            } else if (page.equals("help")) {
                request.getRequestDispatcher("/WEB-INF/jsp/help.jsp").forward(request, response);
                return;
            }
        }
        response.setContentType("text/html");
        PrintWriter write = response.getWriter();
        write.println("<h1>Welcome to our website</h1>");
        write.close();
        return;
    }
}