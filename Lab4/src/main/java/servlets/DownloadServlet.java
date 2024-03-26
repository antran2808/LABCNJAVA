package servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet("/images/download")
public class DownloadServlet extends HttpServlet {
    private final int ARBITARY_SIZE = 1048 ;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        if(fileName == null || fileName.equals("")){
            throw new ServletException("File name can't be null or empty");
        }
        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment;");

        try(InputStream in = request.getServletContext().getResourceAsStream("/WEB-INF/files/" + fileName);
            OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[ARBITARY_SIZE];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }
}
