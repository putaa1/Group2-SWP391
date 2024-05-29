package controller.Order;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import context.OrderDAO;
import model.Order;

public class UpdateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy OID từ request
        int oid = Integer.parseInt(request.getParameter("oid"));

        // Tạo đối tượng OrderDAO
        OrderDAO orderDAO = new OrderDAO();

        // Tìm order dựa trên OID
        Order order = orderDAO.findOrderByOid(oid);

        // Đặt order vào thuộc tính của request để chuyển đến trang cập nhật
        request.setAttribute("order", order);

        // Chuyển hướng đến trang cập nhật order
        request.getRequestDispatcher("UpdateOrder.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Xử lý yêu cầu cập nhật order ở đây
        int oid = Integer.parseInt(request.getParameter("oid"));
        String orderDate = request.getParameter("orderDate");
        int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
        String address = request.getParameter("address");
        String status = request.getParameter("status");
        int cartid = Integer.parseInt(request.getParameter("cartid"));

        // Tạo đối tượng Order từ dữ liệu mới
        Order updatedOrder = new Order();
        updatedOrder.setOid(oid);
        updatedOrder.setOrderDate(orderDate);
        updatedOrder.setTotalPrice(totalPrice);
        updatedOrder.setAddress(address);
        updatedOrder.setStatus(status);
        updatedOrder.setCartid(cartid);

        // Tạo đối tượng OrderDAO
        OrderDAO orderDAO = new OrderDAO();

        // Gọi phương thức UpdateOrder để cập nhật thông tin vào database
        orderDAO.UpdateOrder(updatedOrder);

        // Chuyển hướng đến trang thông báo cập nhật thành công
        response.sendRedirect("UpdateSuccess.jsp");
    }
}