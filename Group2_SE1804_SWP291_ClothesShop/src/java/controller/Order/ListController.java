package controller.Order;

import context.OrderDAO;
import model.Order;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrderDAO orderDAO = new OrderDAO();

        // Retrieve page size from servlet context
        int pageSize = Integer.parseInt(getServletContext().getInitParameter("pagesize"));

        // Retrieve page index from request parameter
        String page = request.getParameter("page");
        int pageIndex = (page == null || page.isEmpty()) ? 1 : Integer.parseInt(page);

        // Retrieve orders for the current page index
        List<Order> orderList = orderDAO.pagging(pageIndex);

        // Calculate total pages for pagination
        int totalOrders = orderDAO.count();
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

        // Set attributes for JSP rendering
        request.setAttribute("orderList", orderList);
        request.setAttribute("currentPage", pageIndex);
        request.setAttribute("totalPages", totalPages);

        // Forward the request to the JSP
        request.getRequestDispatcher("orderList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "OrderListServlet";
    }
}