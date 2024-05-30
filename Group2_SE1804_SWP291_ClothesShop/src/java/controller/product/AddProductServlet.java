package controller.product;

import context.ProductDAO;
import model.Brand;
import model.Category;
import model.Gender;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "AddProductServlet", urlPatterns = {"/addproduct"})
public class AddProductServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    ProductDAO productDAO = new ProductDAO();
    
    // Retrieve lists of categories, brands, and genders
    List<Category> categories = productDAO.getAllCategories();
    List<Brand> brands = productDAO.getAllBrands();
    List<Gender> genders = productDAO.getAllGenders();
    
    // Set attributes
    request.setAttribute("categories", categories);
    request.setAttribute("brands", brands);
    request.setAttribute("genders", genders);
    
    // Forward to JSP
    request.getRequestDispatcher("product/addproduct.jsp").forward(request, response);
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        String describe = request.getParameter("describe");
        String img = request.getParameter("img");
        Date releaseDate = Date.valueOf(request.getParameter("releaseDate"));
        int categoryId = Integer.parseInt(request.getParameter("category"));
        int brandId = Integer.parseInt(request.getParameter("brand"));
        int genderId = Integer.parseInt(request.getParameter("gender"));
        String size = request.getParameter("size");

        Category category = productDAO.getCategoryById(categoryId);
        Brand brand = productDAO.getBrandById(brandId);
        Gender gender = productDAO.getGenderById(genderId);

        Product product = new Product(0, name, quantity, price, describe, img, releaseDate, category, brand, gender, size);
        productDAO.addProduct(product);

        response.sendRedirect("listproduct");
    }
}
