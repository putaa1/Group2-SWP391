<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add New Product</title>
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link href="adminassets/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script>
        function validateForm() {
            let name = document.getElementById('name').value;
            let quantity = document.getElementById('quantity').value;
            let price = document.getElementById('price').value;
            let img = document.getElementById('img').value;
            let releaseDate = document.getElementById('releaseDate').value;
            let category = document.getElementById('category').value;
            let brand = document.getElementById('brand').value;
            let gender = document.getElementById('gender').value;
            let size = document.getElementById('size').selectedOptions;
            let description = document.getElementById('describe').value;
            
            // Basic validations
            if (!name || !quantity || !price || !img || !releaseDate || !category || !brand || !gender || size.length === 0 || !description) {
                alert("All fields are required.");
                return false;
            }
             let namePattern = /^[a-zA-Z0-9\s]{3,50}$/;
             if (!namePattern.test(name)) {
                alert("Name must be 3-50 characters long and contain only letters, numbers, and spaces.");
                return false;
            }
            // Quantity and Price validations
            if (isNaN(quantity) || quantity <= 0 || quantity > 1000) {
                alert("Quantity must be a positive number and no more than 1000.");
                return false;
            }
            if (isNaN(price) || price <= 0 || price > 1000) {
                alert("Price must be a positive number and no more than $10,00.");
                return false;
            }
            if (price < 5) {
                alert("Price must be at least $5.");
                return false;
            }
            // Image URL validation
            let imgPattern = /\.(jpg|jpeg|png|gif|webp)$/i;
            if (!imgPattern.test(img)) {
                alert("Image URL must end with .jpg, .jpeg, .png, .gif, or .webp.");
                return false;
            }
            let today = new Date().toISOString().split('T')[0];
            if (releaseDate > today) {
                alert("Release date cannot be in the future.");
                return false;
            }
            if (size.length === 0) {
                alert("At least one size must be selected.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body id="page-top">
    <!-- Page Wrapper -->
    <div id="wrapper">
        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">SB Admin</div>
            </a>
            <hr class="sidebar-divider my-0">
            <li class="nav-item">
                <a class="nav-link" href="index.html">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span>
                </a>
            </li>
            <hr class="sidebar-divider">
            <div class="sidebar-heading">Interface</div>
            <li class="nav-item">
                <a class="nav-link" href="listproduct">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Product Table</span>
                </a>
            </li>
            <hr class="sidebar-divider d-none d-md-block">
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>
        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">
            <div id="content">
                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <form class="form-inline">
                        <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                            <i class="fa fa-bars"></i>
                        </button>
                    </form>
                    <ul class="navbar-nav ml-auto">
                        <div class="topbar-divider d-none d-sm-block"></div>
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                <img class="img-profile rounded-circle" src="img/undraw_profile.svg">
                            </a>
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                 aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>
                    </ul>
                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">
                    <!-- Page Heading -->
                    <h1 class="h3 mb-4 text-gray-800">Add New Product</h1>
                    <form action="addproduct" method="post" onsubmit="return validateForm()">
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="name">Product Name</label>
                                <input type="text" class="form-control" id="name" name="name" required aria-describedby="nameHelp">
                                <small id="nameHelp" class="form-text text-muted">Enter the product name.</small>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="quantity">Quantity</label>
                                <input type="number" class="form-control" id="quantity" name="quantity" required aria-describedby="quantityHelp">
                                <small id="quantityHelp" class="form-text text-muted">Enter the product quantity.</small>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="price">Price</label>
                                <input type="number" step="0.01" class="form-control" id="price" name="price" required aria-describedby="priceHelp">
                                <small id="priceHelp" class="form-text text-muted">Enter the product price.</small>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="img">Image URL</label>
                                <input type="text" class="form-control" id="img" name="img" required aria-describedby="imgHelp">
                                <small id="imgHelp" class="form-text text-muted">Enter the URL for the product image.</small>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="releaseDate">Release Date</label>
                                <input type="date" class="form-control" id="releaseDate" name="releaseDate" required aria-describedby="releaseDateHelp">
                                <small id="releaseDateHelp" class="form-text text-muted">Select the release date.</small>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="category">Category</label>
                                <select class="form-control" id="category" name="category" required aria-describedby="categoryHelp">
                                    <c:forEach var="category" items="${categories}">
                                        <option value="${category.cid}">${category.name}</option>
                                    </c:forEach>
                                </select>
                                <small id="categoryHelp" class="form-text text-muted">Select the product category.</small>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-4">
                                <label for="brand">Brand</label>
                                <select class="form-control" id="brand" name="brand" required aria-describedby="brandHelp">
                                    <c:forEach var="brand" items="${brands}">
                                        <option value="${brand.bid}">${brand.name}</option>
                                    </c:forEach>
                                </select>
                                <small id="brandHelp" class="form-text text-muted">Select the product brand.</small>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="gender">Gender</label>
                                <select class="form-control" id="gender" name="gender" required aria-describedby="genderHelp">
                                    <c:forEach var="gender" items="${genders}">
                                        <option value="${gender.gid}">${gender.description}</option>
                                    </c:forEach>
                                </select>
                                <small id="genderHelp" class="form-text text-muted">Select the gender category.</small>
                            </div>
                            <div class="form-group col-md-4">
                                <label for="size">Size</label>
                                <select class="form-control" id="size" name="size" multiple required aria-describedby="sizeHelp">
                                    <c:forEach var="size" items="${sizes}">
                                        <option value="${size.sid}">${size.name}</option>
                                    </c:forEach>
                                </select>
                                <small id="sizeHelp" class="form-text text-muted">Select the product sizes (hold Ctrl to select multiple).</small>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="describe">Description</label>
                            <textarea class="form-control" id="describe" name="describe" rows="3" required aria-describedby="describeHelp"></textarea>
                            <small id="describeHelp" class="form-text text-muted">Provide a description for the product.</small>
                        </div>
                        <button type="submit" class="btn btn-primary">Add Product</button>
                    </form>
                </div>
                <!-- /.container-fluid -->
            </div>
            <!-- End of Main Content -->
        </div>
        <!-- End of Content Wrapper -->
    </div>
    <!-- End of Page Wrapper -->

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="adminassets/js/sb-admin-2.min.js"></script>
</body>
</html>
