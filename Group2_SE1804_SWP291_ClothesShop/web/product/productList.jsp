<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Product Management</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .table-container {
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Product Management</h1>
        <a href="addproduct" class="btn btn-success mb-3">Add New Product</a>

        <!-- Search Form -->
        <form method="get" action="listproduct" class="mb-4">
            <div class="form-group">
                <input type="text" name="search" class="form-control" placeholder="Search by name" value="${param.search}">
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>

        <!-- Product Table -->
        <div class="table-container">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Brand</th>
                        <th>Gender</th>
                        <th>Size</th>
                        <th>Release Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.pid}</td>
                            <td><a href="productdetail?pid=${product.pid}">${product.name}</a></td>
                            <td>${product.quantity}</td>
                            <td>$${product.price}</td>
                            <td>${product.category.name}</td>
                            <td>${product.brand.name}</td>
                            <td>${product.gender.description}</td>
                            <td>${product.size}</td>
                            <td>${product.releaseDate}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Pagination -->
        <nav aria-label="Page navigation example" class="mt-4">
            <ul class="pagination justify-content-center">
                <c:if test="${pageIndex > 1}">
                    <li class="page-item">
                        <a class="page-link" href="listproduct?page=${pageIndex - 1}&search=${param.search}">Previous</a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${totalPages}" var="i">
                    <li class="page-item ${pageIndex == i ? 'active' : ''}">
                        <a class="page-link" href="listproduct?page=${i}&search=${param.search}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${pageIndex < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="listproduct?page=${pageIndex + 1}&search=${param.search}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
