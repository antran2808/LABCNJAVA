package org.example.lab5.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.lab5.DAO.ProductDAO;
import org.example.lab5.models.Product;

import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() throws ServletException {
        this.productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> products = productDAO.getAll();
        req.setAttribute("products", products);
        req.getRequestDispatcher("/WEB-INF/jsp/products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deleteProductId = req.getParameter("deleteProductId");

        if (deleteProductId != null) {
            // Xóa mục từ cơ sở dữ liệu hoặc danh sách sản phẩm của bạn
            // Ví dụ:
            productDAO.removeById(Long.parseLong(deleteProductId));
            // Sau khi xóa, bạn có thể chuyển hướng hoặc làm mới trang để cập nhật danh sách sản phẩm
            resp.sendRedirect("/Lab5_war_exploded/products");
        }
        else {
            String productName = req.getParameter("productname");
            double productPrice = Double.parseDouble(req.getParameter("productprice"));

            Product product = new Product(productName, productPrice);

            // Thêm sản phẩm vào cơ sở dữ liệu bằng cách sử dụng ProductDAO
            Long success = productDAO.add(product);

            if (success != null) {
                // Nếu thêm sản phẩm thành công, chuyển hướng đến trang products để hiển thị lại danh sách sản phẩm
                resp.sendRedirect("/Lab5_war_exploded/products");
            } else {
                req.setAttribute("error", "Failed to add product");
            }
        }

    }
}
