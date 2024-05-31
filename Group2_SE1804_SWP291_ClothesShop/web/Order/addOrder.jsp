<%-- 
    Document   : addOrder
    Created on : May 31, 2024, 6:08:44 PM
    Author     : djxjs
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Order</title>
</head>
<body>
    <h2>Add New Order</h2>
    <form action="addOrder" method="post">
        <label for="orderDate">Order Date:</label>
        <input type="text" id="orderDate" name="orderDate" required><br>
        <label for="totalPrice">Total Price:</label>
        <input type="number" id="totalPrice" name="totalPrice" required><br>
        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required><br>
        <label for="status">Status:</label>
        <input type="number" id="status" name="status" required><br>
        <label for="cartid">Cart ID:</label>
        <input type="number" id="cartid" name="cartid" required><br>
        <button type="submit">Add Order</button>
    </form>
</body>
</html>
