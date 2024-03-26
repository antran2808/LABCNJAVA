package servlets;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

@WebServlet("/images")
public class ImagesServlet extends HttpServlet  {

    @Override
    public void init() throws ServletException {
        System.out.println("Starting Images Servlet!!!");
    }

    @Override
    public void destroy() {
        System.out.println("Stopping Images Servlet!!!");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/images.jsp").forward(request, response);
    }

}
