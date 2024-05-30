package controller.Order;

import context.OrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Order;

public class searchOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();

        String searchOrder = request.getParameter("searchOrder");
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }

        if (searchOrder == null || searchOrder.isEmpty()) {
            response.sendRedirect("listOrder?index=" + indexPage);
        } else {
            int index = Integer.parseInt(indexPage);
            int count = orderDAO.count();
            int endPage = count / 5;
            if (count % 5 != 0) {
                endPage++;
            }
            List<Order> listO = orderDAO.search(searchOrder, index);
            request.setAttribute("listO", listO);
            request.setAttribute("endPage", endPage);
            request.setAttribute("searchOrder", searchOrder);
            request.getRequestDispatcher("listOrder.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
