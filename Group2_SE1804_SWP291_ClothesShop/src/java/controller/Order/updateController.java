///*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Order;

import context.OrderDAO;
import model.Order;

import context.OrderDAO;
import model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
@WebServlet(name = "UpdateOrderServlet", urlPatterns = {"/orders/update"})
public class updateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int oid = Integer.parseInt(req.getParameter("oid"));
        OrderDAO orderDB = new OrderDAO();
        Order order = orderDB.get(oid);

        req.setAttribute("order", order);
        req.getRequestDispatcher("/order/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int oid = Integer.parseInt(req.getParameter("oid"));
        String orderDate = req.getParameter("orderDate");
        String address = req.getParameter("address");
        String status = req.getParameter("status");
        int cartid = Integer.parseInt(req.getParameter("cartid"));

        Order order = new Order();
        order.setOid(oid);
        order.setOrderDate(orderDate);
        order.setAddress(address);
        order.setStatus(status);
        order.setCartid(cartid);

        OrderDAO orderDB = new OrderDAO();
        orderDB.UpdateOrder(order);
        resp.sendRedirect("order-list");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}