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
            out.println("<title>Servlet addOrder</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addOrder at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderDate = request.getParameter("orderDate");
        int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
        String address = request.getParameter("address");
        int status = Integer.parseInt(request.getParameter("status"));
        int cartid = Integer.parseInt(request.getParameter("cartid"));
        
        OrderDAO od = new OrderDAO();
        od.addOrder(orderDate, totalPrice, address, status, cartid);
        request.getRequestDispatcher("listOrder").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}