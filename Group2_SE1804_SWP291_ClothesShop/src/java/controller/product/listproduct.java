package controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import model.Category;
import model.Brand;
import model.Gender;
import context.ProductDAO;

public class listproduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        int pageIndex = 1;
        int pageSize = 4;
        if (request.getParameter("page") != null) {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }

        String search = request.getParameter("search");
        String category = request.getParameter("category");
        String brand = request.getParameter("brand");
        String gender = request.getParameter("gender");
        String priceFromStr = request.getParameter("priceFrom");
        String priceToStr = request.getParameter("priceTo");

        Double priceFrom = priceFromStr != null && !priceFromStr.trim().isEmpty() ? Double.parseDouble(priceFromStr) : null;
        Double priceTo = priceToStr != null && !priceToStr.trim().isEmpty() ? Double.parseDouble(priceToStr) : null;

        ProductDAO productDAO = new ProductDAO();
        ArrayList<Product> products;
        int totalProducts;

        if (search != null && !search.trim().isEmpty()) {
            products = productDAO.searchByNameAndFilters(search, category, brand, gender, priceFrom, priceTo, pageIndex, pageSize);
            totalProducts = productDAO.countSearchResultsWithFilters(search, category, brand, gender, priceFrom, priceTo);
        } else {
            products = productDAO.paggingWithFilters(category, brand, gender, priceFrom, priceTo, pageIndex, pageSize);
            totalProducts = productDAO.countWithFilters(category, brand, gender, priceFrom, priceTo);
        }

        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        // Fetch categories, brands, and genders
        List<Category> categories = productDAO.getAllCategories();
        List<Brand> brands = productDAO.getAllBrands();
        List<Gender> genders = productDAO.getAllGenders();

        // Calculate the maximum price
        double maxPrice = products.stream().mapToDouble(Product::getPrice).max().orElse(0.0);

        // Set lists and other attributes
        request.setAttribute("products", products);
        request.setAttribute("categories", categories);
        request.setAttribute("brands", brands);
        request.setAttribute("genders", genders);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("search", search);
        request.setAttribute("category", category);
        request.setAttribute("brand", brand);
        request.setAttribute("gender", gender);
        request.setAttribute("priceFrom", priceFromStr);
        request.setAttribute("priceTo", priceToStr);
        request.setAttribute("maxPrice", maxPrice); // Pass the maxPrice to the JSP

        request.getRequestDispatcher("product/productList1.jsp").forward(request, response);
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
