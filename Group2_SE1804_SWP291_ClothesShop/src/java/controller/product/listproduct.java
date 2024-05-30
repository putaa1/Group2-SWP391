package controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Product;
import context.ProductDAO;

public class listproduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Get the page index from the request, default to 1 if not provided
        int pageIndex = 1;
        int pageSize = 4; // Define the page size
        if (request.getParameter("page") != null) {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }

        String search = request.getParameter("search");
        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> products;
        int totalProducts;

        if (search != null && !search.trim().isEmpty()) {
            products = productDAO.searchByName(search, pageIndex, pageSize);
            totalProducts = productDAO.countSearchResults(search);
        } else {
            products = productDAO.pagging(pageIndex, pageSize);
            totalProducts = productDAO.count();
        }

        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        // Set the products, page index, total pages, and search query as request attributes
        request.setAttribute("products", products);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("search", search);

        // Forward to the JSP page for rendering
        request.getRequestDispatcher("product/productList.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
