<%-- 
    Document   : updateOrder
    Created on : May 26, 2024, 9:07:13 PM
    Author     : chien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Order</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/user-style/edit-style.css">
    </head>
    <body>
        <header>
            <nav class="navbar navbar-expand-md navbar-dark"
                 style="background-color: tomato">
                <div class="navbar-brand"> Online Shopping System </div>

                <ul class="navbar-nav">
                    <li><a href="<%=request.getContextPath()%>/listOrders"
                           class="nav-link">Orders</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <form action="updateOrder" method="post"  >
                        <fieldset class="form-group">
                            <div> <label>Order ID</label></div>  
                            <input readonly="" name="oid" value="${order.oid}"/>
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Order Date</label> 
                            <input type="date" value="${order.orderDate}" class="form-control"
                                   name="orderDate"  id="orderDate" required >
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Total Price</label> 
                            <input type="number" value="${order.totalPrice}" class="form-control"
                                   name="totalPrice"  id="totalPrice" required >
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Address</label> 
                            <input type="text" value="${order.address}" class="form-control"
                                   name="address"  id="address" required >
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Status</label> 
                            <input type="number" value="${order.status}" class="form-control"
                                   name="status"  id="status" required >
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Cart ID</label> 
                            <input type="number" value="${order.cartid}" class="form-control"
                                   name="cartid"  id="cartid" required >
                        </fieldset>

                        <div class="text-right">

                            <button type="submit" value="Update" onclick="validateForm()" class="btn btn-success">Save</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <script>
            function validateForm() {
                const orderDate = document.getElementById("orderDate");
                const address = document.getElementById("address");

                orderDate.setCustomValidity("");
                address.setCustomValidity("");

                const dateLength = orderDate.value.length;
                const addressLength = address.value.length;

                if (dateLength === 0) {
                    orderDate.setCustomValidity("Please enter a valid order date.");
                }

                if (addressLength < 1 || addressLength > 50) {
                    address.setCustomValidity("Address length must be between 1 and 50 characters.");
                }
            }
        </script>
    </body>
</html>