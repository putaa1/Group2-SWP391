/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Brand;
import model.Category;
import model.Gender;
import model.Product;

public class ProductDAO extends DBContext {

    public ArrayList<Product> pagging(int index, int pageSize) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = """
                        SELECT * FROM product
                        ORDER BY pid
                        OFFSET ? ROWS
                        FETCH NEXT ? ROWS ONLY;
                        """;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * pageSize);
            stm.setInt(2, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = extractProduct(rs);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public int calculateTotalPages(int pageSize) {
        int totalRecords = count();
        return (int) Math.ceil((double) totalRecords / pageSize);
    }

    public int count() {
        try {
            String sql = "SELECT COUNT(*) FROM product";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addProduct(Product product) {
        try {
            String sql = "INSERT INTO product (name, quantity, price, describe, img, releaseDate, cid, bid, gid, size) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, product.getName());
            stm.setInt(2, product.getQuantity());
            stm.setDouble(3, product.getPrice());
            stm.setString(4, product.getDescribe());
            stm.setString(5, product.getImg());
            stm.setDate(6, new java.sql.Date(product.getReleaseDate().getTime()));
            stm.setInt(7, product.getCategory().getCid());
            stm.setInt(8, product.getBrand().getBid());
            stm.setInt(9, product.getGender().getGid());
            stm.setString(10, product.getSize());
            stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            String sql = "SELECT * FROM category";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("cid");
                String name = rs.getString("name");
                categories.add(new Category(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public List<Brand> getAllBrands() {
        List<Brand> brands = new ArrayList<>();
        try {
            String sql = "SELECT * FROM brand";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("bid");
                String name = rs.getString("name");
                brands.add(new Brand(id, name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brands;
    }

    public List<Gender> getAllGenders() {
        List<Gender> genders = new ArrayList<>();
        try {
            String sql = "SELECT * FROM gender";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("gid");
                String description = rs.getString("description");
                genders.add(new Gender(id, description));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return genders;
    }

    private Product extractProduct(ResultSet rs) throws Exception {
        int pid = rs.getInt("pid");
        String name = rs.getString("name");
        int quantity = rs.getInt("quantity");
        double price = rs.getDouble("price");
        String describe = rs.getString("describe");
        String img = rs.getString("img");
        Date releaseDate = rs.getDate("releaseDate");
        int categoryId = rs.getInt("cid");
        int brandId = rs.getInt("bid");
        int genderId = rs.getInt("gid");
        String size = rs.getString("size");

        Category category = getCategoryById(categoryId);
        Brand brand = getBrandById(brandId);
        Gender gender = getGenderById(genderId);

        return new Product(pid, name, quantity, price, describe, img, releaseDate, category, brand, gender, size);
    }

    // Method to get Category by ID
    public Category getCategoryById(int id) {
        Category category = null;
        try {
            String sql = "SELECT * FROM category WHERE cid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                category = new Category(id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public ArrayList<Product> searchByName(String name, int index) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = """
                        SELECT * FROM product
                        WHERE name LIKE ?
                        ORDER BY pid
                        OFFSET ? ROWS
                        FETCH NEXT 4 ROWS ONLY;
                        """;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            stm.setInt(2, (index - 1) * 4);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                // Extract product details and add to the list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    // Method to get Brand by ID
    public Brand getBrandById(int id) {
        Brand brand = null;
        try {
            String sql = "SELECT * FROM brand WHERE bid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                brand = new Brand(id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return brand;
    }

    // Method to get Gender by ID
    public Gender getGenderById(int id) {
        Gender gender = null;
        try {
            String sql = "SELECT * FROM gender WHERE gid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String description = rs.getString("description");
                gender = new Gender(id, description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gender;
    }

    public Product getProductById(int pid) {
        Product product = null;
        try {
            String sql = "SELECT * FROM product WHERE pid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                product = extractProduct(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public ArrayList<Product> searchByName(String name, int pageIndex, int pageSize) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String sql = """
                    SELECT * FROM product
                    WHERE name LIKE ?
                    ORDER BY pid
                    OFFSET ? ROWS
                    FETCH NEXT ? ROWS ONLY;
                    """;
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            stm.setInt(2, (pageIndex - 1) * pageSize);
            stm.setInt(3, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Product product = extractProduct(rs);
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public int countSearchResults(String search) {
        try {
            String sql = "SELECT COUNT(*) FROM product WHERE name LIKE ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + search + "%");
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
