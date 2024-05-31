package controller.product;

import context.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import java.io.IOException;

@WebServlet(name = "ProductDetailServlet", urlPatterns = {"/productdetail"})
public class ProductDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pid = Integer.parseInt(request.getParameter("pid"));
        ProductDAO dao = new ProductDAO();
        Product product = dao.getProductById(pid);
        request.setAttribute("product", product);
        request.getRequestDispatcher("product/productdetail.jsp").forward(request, response);
    }
}
