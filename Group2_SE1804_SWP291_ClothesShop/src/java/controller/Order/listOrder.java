package controller.Order;

import context.OrderDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Order;

public class listOrder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO orderDAO = new OrderDAO();

        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }

        int index = Integer.parseInt(indexPage);
        String searchOrder = request.getParameter("searchOrder");

        int count;
        List<Order> listO;
        if (searchOrder == null || searchOrder.isEmpty()) {
            count = orderDAO.count();
            listO = orderDAO.pagging(index);
        } else {
            count = orderDAO.searchCount(searchOrder);
            listO = orderDAO.search(searchOrder, index);
        }

        int endPage = orderDAO.getTotalPages();

        request.setAttribute("listO", listO);
        request.setAttribute("endPage", endPage);

        request.getRequestDispatcher("Order/listOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}