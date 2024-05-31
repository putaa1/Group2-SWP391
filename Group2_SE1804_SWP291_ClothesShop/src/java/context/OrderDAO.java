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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author djxjs
 */
public class OrderDAO extends DBContext {
    public void addOrder(Order order) {
        String query = "INSERT INTO Orders (orderDate, totalPrice, address, status, cartid) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, order.getOrderDate());
            ps.setInt(2, order.getTotalPrice());
            ps.setString(3, order.getAddress());
            ps.setInt(4, order.getStatus());
            ps.setInt(5, order.getCartid());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();
                order.setOid(rs.getInt("oid"));
                order.setOrderDate(rs.getString("orderDate"));
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setAddress(rs.getString("address"));
                order.setStatus(rs.getInt("status"));
                order.setCartid(rs.getInt("cartid"));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}
