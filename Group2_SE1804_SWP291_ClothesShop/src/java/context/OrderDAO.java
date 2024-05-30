/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package context;

import java.util.ArrayList;
import model.Order;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author djxjs
 */
public class OrderDAO extends DBContext {
    
    
    public ArrayList<Order> search(String key, int index) {
    ArrayList<Order> list = new ArrayList<>();
    String sql = "SELECT * FROM Order o WHERE o.address LIKE '%" + key + "%'"
            + "ORDER BY oid\n"
            + "OFFSET ? ROWS\n"
            + "FETCH NEXT 5 ROWS ONLY;";
    try {
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, (index - 1) * 5);
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
    
    public ArrayList<Order> pagging(int index) {
    ArrayList<Order> orders = new ArrayList<>();
    try {
        String sql = "SELECT * FROM Order\n"
                + "ORDER BY oid\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT 5 ROWS ONLY;";
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
    
    public int count() {
    try {
        String sql = "SELECT COUNT(*) FROM Order";
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
    
    public void addOrder(String orderDate, int totalPrice, String address, int status, int cartid) {
    String sql = "INSERT INTO Order (orderDate, totalPrice, address, status, cartid) VALUES (?, ?, ?, ?, ?)";
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
    
    public void deleteOrder(int oid) {
    String sql = "DELETE FROM Order WHERE oid = ?";
    try {
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, oid);
        stm.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public void updateOrder(int oid, String orderDate, int totalPrice, String address, int status, int cartid) {
    String sql = "UPDATE Order SET orderDate = ?, totalPrice = ?, address = ?, status = ?, cartid = ? WHERE oid = ?";
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
    
    public Order getOrderById(int oid) {
    String sql = "SELECT * FROM Order WHERE oid = ?";
    try {
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, oid);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return new Order(rs.getInt("oid"), rs.getString("orderDate"), rs.getInt("totalPrice"),
                             rs.getString("address"), rs.getInt("status"), rs.getInt("cartid"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}
    
    
    
   public ArrayList<Order> getOrdersByPage(int index) {
    ArrayList<Order> orders = new ArrayList<>();
    try {
        String sql = "SELECT * FROM Order\n"
                + "ORDER BY oid\n"
                + "OFFSET ? ROWS\n"
                + "FETCH NEXT 5 ROWS ONLY;";
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

public int getTotalPages() {
    try {
        String sql = "SELECT COUNT(*) FROM Order";
        PreparedStatement stm = connection.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            int totalOrders = rs.getInt(1);
            return (totalOrders + 4) / 5; // Divide total number of orders by page size (5) and round up
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

public int searchCount(String key) {
    try {
        String sql = "SELECT COUNT(*) FROM `Order` WHERE `address` LIKE ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setString(1, "%" + key + "%");
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0;
}

    // Phương thức để lấy tổng số trang dựa trên số lượng đơn hàng
    

public static void main(String[] args) {
    OrderDAO od = new OrderDAO();

    ArrayList<Order> list = od.search("", 2);

    for (Order order : list) {
        System.out.println("Order ID: " + order.getOid());
        System.out.println("Order Date: " + order.getOrderDate());
        System.out.println("Total Price: " + order.getTotalPrice());
        System.out.println("Address: " + order.getAddress());
        System.out.println("Status: " + order.getStatus());
        System.out.println("Cart ID: " + order.getCartid());
        System.out.println();
    }
}

}
