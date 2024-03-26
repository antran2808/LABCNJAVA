package servlets;

import jakarta.servlet.annotation.MultipartConfig;
import org.apache.commons.io.FilenameUtils;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class UploadServlet extends HttpServlet  {
    private HashMap<String, String> userList;

    private final String UPLOAD_DIRECTORY = "WEB-INF/uploads";
    private final Integer MAX_FILE_SIZE = 1024 * 1024 * 5;
    private final Integer MAX_REQUEST_SIZE = 1024 * 1024 * 5 * 5;
    private final Integer MEMORY_THRESHOLD = 1024 * 1024;
    private final String DEFAULT_FILENAME = "file.txt";

    private final String[] EXTENSION_LIST = {"txt", "pdf", "rar", "doc", "docx", "img", "zip"};

    @Override
    public void init() throws ServletException {
        System.out.println("Starting Upload Servlet!!!");
        String uploadPath = getServletContext().getRealPath("") + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
    }

    @Override
    public void destroy() {
        System.out.println("Deleting Servlet!!!");
    }

    private boolean validateFileName(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);
        return Arrays.asList(EXTENSION_LIST).contains(extension);
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return DEFAULT_FILENAME;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        try {
            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String fileName = request.getParameter("fileName");
            for (Part part : request.getParts()) {
                if (fileName == null) fileName = getFileName(part);
                part.write(uploadPath + File.separator + fileName);
            }
            request.setAttribute("message", "The file is uploaded successfully!");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
        }catch (Exception ex) {

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(request, response);
    }
}