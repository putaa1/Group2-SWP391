
import context.OrderDAO;
import model.Order;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteController")
public class DeleteController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oidParam = request.getParameter("oid");
        int oid = Integer.parseInt(oidParam);

        // Tìm kiếm Order với OID nhận được
        OrderDAO orderDAO = new OrderDAO();
        Order orderToDelete = orderDAO.findOrderByOid(oid);

        if (orderToDelete != null) {
            // Nếu Order tồn tại, tiến hành xóa
            orderDAO.deleteOrder(orderToDelete);
            response.getWriter().println("Order with OID " + oid + " has been deleted successfully.");
        } else {
            // Nếu Order không tồn tại, thông báo lỗi
            response.getWriter().println("Order with OID " + oid + " does not exist.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}