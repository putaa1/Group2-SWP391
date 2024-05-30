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

    public void createOrder(Order order) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO orders (order_date, total_price, address, status, cartid) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, order.getOrderDate());
            stm.setInt(2, order.getTotalPrice());
            stm.setString(3, order.getAddress());
            stm.setInt(4, order.getStatus());
            stm.setInt(5, order.getCartid());
            stm.executeUpdate();

            String sql_get_orderid = "SELECT @@IDENTITY AS oid";
            PreparedStatement stm_get_orderid = connection.prepareStatement(sql_get_orderid);
            ResultSet rs = stm_get_orderid.executeQuery();
            if (rs.next()) {
                order.setOid(rs.getInt("oid"));
            }
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public ArrayList<Order> listOrder() {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT oid, orderDate, totalPrice, address, status, cartid FROM orders";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
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
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    public void UpdateOrder(Order order) {
        try {
            connection.setAutoCommit(false);
            String sql = "UPDATE orders \n"
                    + "SET orderDate = ?, totalPrice = ?, address = ?, status = ?, cartid = ?\n"
                    + "WHERE oid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, order.getOrderDate());
            stm.setInt(2, order.getTotalPrice());
            stm.setString(3, order.getAddress());
            stm.setString(4, order.getStatus());
            stm.setInt(5, order.getCartid());
            stm.setInt(6, order.getOid());
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
