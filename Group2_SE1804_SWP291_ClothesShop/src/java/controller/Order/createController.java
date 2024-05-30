/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.Order;


import context.OrderDAO;
import model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateOrderServlet", urlPatterns = {"/orders/create"})
public class createController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/order/create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderDate = req.getParameter("orderDate");
        int totalPrice = Integer.parseInt(req.getParameter("totalPrice"));
        String address = req.getParameter("address");
        int status = Integer.parseInt(req.getParameter("status"));
        int cartid = Integer.parseInt(req.getParameter("cartid"));

        Order order = new Order();
        order.setOrderDate(orderDate);
        order.setTotalPrice(totalPrice);
        order.setAddress(address);
        order.setStatus(status);
        order.setCartid(cartid);

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.createOrder(order);

        resp.sendRedirect(req.getContextPath() + "/orders");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
