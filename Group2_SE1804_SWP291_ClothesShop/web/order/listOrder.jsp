<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  class="no-js" lang="en">
<head> 
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>List Order</title>
    <link rel="icon" href="assets/img/icon.png" type="image/gif" sizes="16x16">
    <link rel="icon" href="assets/img/icon.png" type="image/gif" sizes="18x18">
    <link rel="icon" href="assets/img/icon.png" type="image/gif" sizes="20x20">
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/fontawesome.all.min.css">
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="assets/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="assets/css/magnific-popup.css">
    <link rel="stylesheet" href="assets/css/normalize.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/responsive.css">
    <link rel="stylesheet" href="./assets/user-style/list-style.css">
</head>
<body>
    <!-- Header -->
    <header class="header">
        <!-- Header Top -->
        <!-- Header Middle -->
        <!-- Header Bottom -->
        <div class="header-bottm">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="menu">
                            <nav>
                                <ul>
                                    <li><a href="admin">Home</a></li>
                                    <li><a href="trending-product"> Trending Product</a></li>
                                    <li><a>Product<i class="fa fa-angle-down"></i></a>
                                        <ul class="submenu-item">
                                            <li><a href="addproduct">Add New Product</a></li>
                                            <li><a href="listproduct">List Product</a></li>
                                        </ul>
                                    </li>
                                    <li><a href="orderlist">Order</a></li>                               
                                    <li><a href="user-list">User</a></li>
                                    <li><a href="customer-list">Customer</a></li>
                                    <li><a href="#">Cart</a></li>
                                    <li><a href="#">Feedback</a></li>
                                    <li><a href="blog.html">blog <i class="fa fa-angle-down"></i></a>
                                        <ul class="submenu-item">
                                            <li><a href="blog.html">Blog</a></li>
                                            <li><a href="blog-grid.html">Blog Grid</a></li>
                                            <li><a href="single.html">Blog Single</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- End Header -->
    <div style="padding-top: 100px">
        <div class="title text-center">
            <div class="container">
                <h1>Order List</h1>
                <br>
                <div style="margin-left: 100px; margin-right: 100px">
                    <table class="table table-striped table-bordered table-title" cellspacing="0" rules="all" border="1" id="gvLO" style="border-collapse:collapse;">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Order Date</th>
                                <th scope="col">Address</th>
                                <th scope="col">Total Price</th>
                                <th scope="col">Status</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listO}" var="o">
                                <tr>
                                    <td>${o.oid}</td>
                                    <td>${o.orderDate}</td>
                                    <td>${o.address}</td>
                                    <td>${o.totalPrice}</td>
                                    <td>${o.status}</td>
                                    <td>
                                        <a href="deleteOrder?oid=${o.oid}" onclick="return checkdelete()">
                                            <button class="btn btn-danger btn-sm rounded-0" type="button" data-toggle="tooltip" data-placement="top" title="Delete">
                                                <i class="fa fa-trash"></i>
                                            </button>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="row">
                <div class="col-12 mb-30">
                    <div class="page-pagination text-center">
                        <ul>
                            <c:forEach begin="1" end="${endPage}" var="i">
                                <li>
                                    <c:choose>
                                        <c:when test="${empty param.searchOrder}">
                                            <a href="listOrder?index=${i}">${i}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="searchOrder?index=${i}&searchOrder=${param.searchOrder}">${i}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Js File -->
    <script src="assets/js/modernizr.min.js"></script>
    <script src="assets/js/jquery-3.5.1.min.js"></script>
    <script src="assets/js/popper.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/owl.carousel.min.js"></script>
    <script src="assets/js/jquery.nav.min.js"></script>
    <script src="assets/js/jquery.magnific-popup.min.js"></script>
    <script src="assets/js/mixitup.min.js"></script>
    <script src="assets/js/wow.min.js"></script>
    <script src="assets/js/script.js"></script>
    <script src="assets/js/mobile-menu.js"></script>
    <script>
        function checkdelete() {
            return confirm("Are you sure you want to delete this order?");
        }
    </script>
</body>
</html>