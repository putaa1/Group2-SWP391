package controller.Order;

import context.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class addOrder extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet createOrder</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet createOrder at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String orderDate = request.getParameter("orderDate"); // Retrieve the value of orderDate from the request
       
        String address = request.getParameter("address");
        
           int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
           int status = Integer.parseInt(request.getParameter("status"));
           int cartid = Integer.parseInt(request.getParameter("cartid"));
        // Now you would call your OrderDAO and add the order
        // For illustration, let's assume you have an OrderDAO and the addOrder method works similarly to the CategoryDAO
        OrderDAO od = new OrderDAO();
        // Assuming oid is auto-generated in the database, we don't need to pass it explicitly
        od.addOrder(orderDate, totalPrice, address, status, cartid);
        
        // Redirect to listOrders page after adding order
        request.getRequestDispatcher("listOrders").forward(request, response);
    }

    // Other methods remain unchanged
}