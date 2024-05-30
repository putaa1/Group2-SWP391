<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List Order</title>
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/fontawesome.all.min.css">
    <link rel="stylesheet" href="assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="assets/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="assets/css/animate.css">
    <link rel="stylesheet" href="assets/css/magnific-popup.css">
    <link rel="stylesheet" href="assets/css/normalize.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/responsive.css">
</head>
<body>
    <!-- Header -->
    <header class="header">
        <!-- Header Top -->
        <div class="header-top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-5">
                        <div class="top-text">
                            <p>Welcome to Electronics Shop</p>
                        </div>
                    </div>
                    <div class="col-lg-7">
                        <div class="top-list">
                            <c:if test="${sessionScope.acc != null}">
                                <a href="profile"><i class="fas fa-user"></i>${sessionScope.acc.username}</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header Middle -->
        <!-- Header Bottom -->
    </header>
    <!-- End Header -->

    <!-- Start Hero Area -->
    <div style="padding-top: 100px">
        <div class="title text-center">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <!-- Add your search form here -->
                        <div class="form-group row">
                            <form action="searchOrder" method="get">
                                <div class="input-group">
                                    <div class="form-outline">
                                        <input type="search" name="searchOrder" class="form-control" placeholder="Search order by name here..."  />
                                    </div>
                                    <button type="submit" class="btn btn-primary">
                                        <i class="fas fa-search"></i>
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="col-6">
                        <!-- Add your title here -->
                        <h1>Order List</h1>
                    </div>
                    <div class="col">
                        <!-- Add any additional content here -->
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 mb-30">
                <div class="page-pagination text-center">
                    <ul>
                        <!-- Add pagination links here -->
                        <c:forEach begin="1" end="${requestScope.endPage}" var="i">
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
    <!-- End Hero Area -->

    <!-- Start Footer Area -->
    <footer class="footer">
        <!-- Footer Top -->
        <!-- Footer Bottom -->
    </footer>
    <!-- End Footer Area -->

    <!-- Include necessary JavaScript files -->
    <script src="assets/js/jquery-3.5.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- Add any additional JavaScript scripts -->
</body>
</html>