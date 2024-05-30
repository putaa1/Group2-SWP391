/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;


import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO extends DBContext {

    public ArrayList<Order> search(String key, int index) {
        ArrayList<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM [Order] o WHERE o.address LIKE ? "
                + "ORDER BY o.oid "
                + "OFFSET ? ROWS "
                + "FETCH NEXT 5 ROWS ONLY;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + key + "%");
            stm.setInt(2, (index - 1) * 5);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt("oid"), rs.getString("orderDate"), rs.getInt("totalPrice"), 
                                   rs.getString("address"), rs.getInt("status"), rs.getInt("cartid")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Pagination for orders
    public ArrayList<Order> pagging(int index) {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM [Order] "
                + "ORDER BY oid "
                + "OFFSET ? ROWS "
                + "FETCH NEXT 5 ROWS ONLY;";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (index - 1) * 5);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("oid"), rs.getString("orderDate"), rs.getInt("totalPrice"), 
                                     rs.getString("address"), rs.getInt("status"), rs.getInt("cartid")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Count total number of orders
    public int count() {
        String sql = "SELECT count(*) FROM [Order]";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Get all orders
    public ArrayList<Order> getAllOrders() {
        ArrayList<Order> listOrders = new ArrayList<>();
        String sql = "SELECT * FROM [Order]";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listOrders.add(new Order(rs.getInt("oid"), rs.getString("orderDate"), rs.getInt("totalPrice"), 
                                         rs.getString("address"), rs.getInt("status"), rs.getInt("cartid")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOrders;
    }

    // Sort orders by address
    public ArrayList<Order> sortOrdersByAddress() {
        ArrayList<Order> listOrders = new ArrayList<>();
        String sql = "SELECT * FROM [Order] ORDER BY address DESC";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                listOrders.add(new Order(rs.getInt("oid"), rs.getString("orderDate"), rs.getInt("totalPrice"), 
                                         rs.getString("address"), rs.getInt("status"), rs.getInt("cartid")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOrders;
    }

    // Add a new order
    public void addOrder(String orderDate, int totalPrice, String address, int status, int cartid) {
        String sql = "INSERT INTO [Order] (orderDate, totalPrice, address, status, cartid) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderDate);
            stm.setInt(2, totalPrice);
            stm.setString(3, address);
            stm.setInt(4, status);
            stm.setInt(5, cartid);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete an order by ID
    public void deleteOrder(int oid) {
        String sql = "DELETE FROM [Order] WHERE oid=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, oid);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Update an order by ID
    public void updateOrder(int oid, String orderDate, int totalPrice, String address, int status, int cartid) {
        String sql = "UPDATE [Order] SET orderDate=?, totalPrice=?, address=?, status=?, cartid=? WHERE oid=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, orderDate);
            stm.setInt(2, totalPrice);
            stm.setString(3, address);
            stm.setInt(4, status);
            stm.setInt(5, cartid);
            stm.setInt(6, oid);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hàm phân trang cho đơn hàng
    public ArrayList<Order> paggingOrders(int pageIndex, int pageSize) {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM [Order] ORDER BY oid OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, (pageIndex - 1) * pageSize);
            stm.setInt(2, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("oid"), rs.getString("orderDate"), rs.getInt("totalPrice"), 
                                     rs.getString("address"), rs.getInt("status"), rs.getInt("cartid")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Hàm tìm kiếm và truy xuất danh sách đơn hàng được phân trang
    public ArrayList<Order> searchOrders(String keyword, int pageIndex, int pageSize) {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM [Order] WHERE address LIKE ? ORDER BY oid OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + keyword + "%");
            stm.setInt(2, (pageIndex - 1) * pageSize);
            stm.setInt(3, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                orders.add(new Order(rs.getInt("oid"), rs.getString("orderDate"), rs.getInt("totalPrice"), 
                                     rs.getString("address"), rs.getInt("status"), rs.getInt("cartid")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    
}
