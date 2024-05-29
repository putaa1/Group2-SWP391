<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Orders</title>
</head>
<body>
    <h2>List of Orders</h2>
    <table border="1">
        <tr>
            <th>Order ID</th>
            <th>Order Date</th>
            <th>Total Price</th>
            <th>Address</th>
            <th>Status</th>
            <th>Cart ID</th>
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.oid}</td>
                <td>${order.orderDate}</td>
                <td>${order.totalPrice}</td>
                <td>${order.address}</td>
                <td>${order.status}</td>
                <td>${order.cartid}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>