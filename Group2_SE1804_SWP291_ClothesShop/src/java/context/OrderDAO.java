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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderDAO extends DBContext {

    public void createOrder(Order order) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO Orders (ordeDate, totalPrice, address, status, cartid) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, order.getOrderDate());
            stm.setInt(2, order.getTotalPrice());
            stm.setString(3, order.getAddress());
            stm.setString(4, order.getStatus());
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
                order.setStatus(rs.getString("status"));
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
    
    public ArrayList<Order> pagging(int pageindex, int pagesize) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT oid, orderDate, totalPrice, address, status, cartid " +
                         "FROM ClothesShop.Order " +
                         "ORDER BY oid ASC " +
                         "LIMIT ?, ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            int offset = (pageindex - 1) * pagesize;
            stm.setInt(1, offset);
            stm.setInt(2, pagesize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();

                order.setOid(rs.getInt("oid"));
                order.setOrderDate(rs.getString("orderDate")); // Assuming orderDate is a string
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setAddress(rs.getString("address"));
                order.setStatus(rs.getString("status"));
                order.setCartid(rs.getInt("cartid"));

                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    public int count() {
        try {
            String sql = "SELECT count(*) AS total FROM ClothesShop.Order";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public ArrayList<Order> searchByText(String searched) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT oid, orderDate, totalPrice, address, status, cartid " +
                         "FROM Orders " +
                         "WHERE orderDate LIKE ? " +
                         "OR address LIKE ? " +
                         "OR status LIKE ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + searched + "%");
            stm.setString(2, "%" + searched + "%");
            stm.setString(3, "%" + searched + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOid(rs.getInt("oid"));
                order.setOrderDate(rs.getString("orderDate")); // Assuming orderDate is a string
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setAddress(rs.getString("address"));
                order.setStatus(rs.getString("status")); // Assuming status is a string
                order.setCartid(rs.getInt("cartid"));

                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    public ArrayList<Order> searchByStatus(String status) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT oid, orderDate, totalPrice, address, status, cartid " +
                         "FROM Orders " +
                         "WHERE status = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOid(rs.getInt("oid"));
                order.setOrderDate(rs.getString("orderDate")); // Assuming orderDate is a string
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setAddress(rs.getString("address"));
                order.setStatus(rs.getString("status")); // Assuming status is a string
                order.setCartid(rs.getInt("cartid"));

                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    public ArrayList<Order> searchByAddress(String address) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT oid, orderDate, totalPrice, address, status, cartid " +
                         "FROM Orders " +
                         "WHERE address LIKE ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + address + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOid(rs.getInt("oid"));
                order.setOrderDate(rs.getString("orderDate")); // Assuming orderDate is a string
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setAddress(rs.getString("address"));
                order.setStatus(rs.getString("status")); // Assuming status is a string
                order.setCartid(rs.getInt("cartid"));

                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
    
    public ArrayList<Order> searchByOrderDate(String orderDate) {
        ArrayList<Order> orders = new ArrayList<>();
        try {
            String sql = "SELECT oid, orderDate, totalPrice, address, status, cartid " +
                         "FROM Orders " +
                         "WHERE orderDate LIKE ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + orderDate + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setOid(rs.getInt("oid"));
                order.setOrderDate(rs.getString("orderDate")); // Assuming orderDate is a string
                order.setTotalPrice(rs.getInt("totalPrice"));
                order.setAddress(rs.getString("address"));
                order.setStatus(rs.getString("status")); // Assuming status is a string
                order.setCartid(rs.getInt("cartid"));

                orders.add(order);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }

    
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setOid(resultSet.getInt("oid"));
                order.setOrderDate(resultSet.getString("orderDate"));
                order.setTotalPrice(resultSet.getInt("totalPrice"));
                order.setAddress(resultSet.getString("address"));
                order.setStatus(resultSet.getString("status"));
                order.setCartid(resultSet.getInt("cartid"));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
 public Order findOrderByOid(int oid) {
    Order order = null;
    try {
        String sql = "SELECT oid, orderDate, totalPrice, address, status, cartid " +
                     "FROM Orders " +
                     "WHERE oid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, oid);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            order = new Order();
            order.setOid(rs.getInt("oid"));
            order.setOrderDate(rs.getString("orderDate")); // Assuming orderDate is a string
            order.setTotalPrice(rs.getInt("totalPrice"));
            order.setAddress(rs.getString("address"));
            order.setStatus(rs.getString("status")); // Assuming status is a string
            order.setCartid(rs.getInt("cartid"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return order;
}

 public void deleteOrder(Order order) {
    try {
        connection.setAutoCommit(false);
        String sql = "DELETE FROM Orders WHERE oid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, order.getOid());
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

