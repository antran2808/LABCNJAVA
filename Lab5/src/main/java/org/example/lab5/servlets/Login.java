package org.example.lab5.servlets;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.lab5.DAO.UserDAO;
import org.example.lab5.models.User;

import java.io.IOException;

//@WebServlet("/Login")
public class Login extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        this.userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rememberPassword = req.getParameter("rememberPassword");
        try {
            User user = userDAO.findByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                System.out.println("User " + username + " logged in sucessfully!!");
                HttpSession session = req.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("username", user.getUsername());
                if (rememberPassword != null) {
                    Cookie userId = new Cookie("userId", String.valueOf(user.getId()));
                    userId.setMaxAge(30 * 60 * 60 * 24);
                    resp.addCookie(userId);
                }
                resp.sendRedirect("/Lab5_war_exploded/products");
            } else {
                System.out.println("User " + username + " failed to logged in!!");
                resp.sendRedirect("/Lab5_war_exploded/register");
            }
        } catch (Exception ex) {
            System.out.println("User " + username + " failed to log in!!");
            System.out.println(ex.getMessage());
            resp.sendRedirect("/Lab5_war_exploded/register");
        }
    }

}
