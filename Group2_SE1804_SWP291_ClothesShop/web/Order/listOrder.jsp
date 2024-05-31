<%-- 
    Document   : listOrder
    Created on : May 31, 2024, 6:12:40 PM
    Author     : djxjs
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>List of Orders</title>
</head>
<body>
    <h2>List of Orders</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>Order Date</th>
                <th>Total Price</th>
                <th>Address</th>
                <th>Status</th>
                <th>Cart ID</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="order" items="${orderList}">
                <tr>
                    <td>${order.oid}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.totalPrice}</td>
                    <td>${order.address}</td>
                    <td>${order.status}</td>
                    <td>${order.cartid}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>