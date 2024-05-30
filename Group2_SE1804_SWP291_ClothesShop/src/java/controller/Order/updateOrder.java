package controller.Order;

import context.OrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class updateOrder extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet updateOrder</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateOrder at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        int oid = Integer.parseInt(request.getParameter("oid"));
        String orderDate = request.getParameter("orderDate");
        int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
        String address = request.getParameter("address");
        int status = Integer.parseInt(request.getParameter("status"));
        int cartid = Integer.parseInt(request.getParameter("cartid"));

        // Now you would call your OrderDAO and update the order
        OrderDAO od = new OrderDAO();
        od.updateOrder(oid, orderDate, totalPrice, address, status, cartid);
        
        // Redirect to listOrders page after updating order
        response.sendRedirect("listOrders");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}