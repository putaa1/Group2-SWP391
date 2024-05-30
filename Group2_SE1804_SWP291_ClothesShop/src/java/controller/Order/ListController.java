/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.order;

import context.OrderDAO;
import model.Order;

import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ListOrderController
 */
@WebServlet(name = "ListOrderController", urlPatterns = {"/orders"})
public class ListController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        OrderDAO orderDB = new OrderDAO();
        
        int pageSize = Integer.parseInt(getServletContext().getInitParameter("pagesize"));
        String page = req.getParameter("page");
        if (page == null || page.length() == 0) {
            page = "1";
        }
        int pageIndex = Integer.parseInt(page);
//        ArrayList<Order> orders = orderDB.pagging(pageIndex, pageSize);
//        int totalRow = orderDB.count();
        int totalPage = (totalRow % pageSize == 0) ? totalRow / pageSize : (totalRow / pageSize) + 1;

        req.setAttribute("pageIndex", pageIndex);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/order/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String searched = req.getParameter("searched");
        String address = req.getParameter("address");
        String status = req.getParameter("status");

        OrderDAO orderDB = new OrderDAO();
        ArrayList<Order> orders = new ArrayList<>();

        if (searched != null && !searched.isEmpty()) {
            orders = orderDB.searchByText(searched);
        } else if (address != null && !address.equals("- Address -")) {
            orders = orderDB.searchByAddress(address);
        } else if (status != null && !status.equals("- Status -")) {
            orders = orderDB.searchByStatus(status);
        } else if (searched != null && !searched.isEmpty() && !address.equals("- Address -") && !status.equals("- Status -")) {
            orders = orderDB.searchOrder(searched, address, status);
        }

        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/order/list.jsp").forward(req, resp);
    }
    
    @Override
    public String getServletInfo() {
        return "Order List Controller";
    }
}
