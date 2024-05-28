<%-- 
    Document   : updateCate
    Created on : May 26, 2024, 9:07:13 PM
    Author     : chien
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Product</title>
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
                    <li><a href="<%=request.getContextPath()%>/listCate"
                           class="nav-link">Product</a></li>
                </ul>
            </nav>
        </header>
        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <form action="updateCate" method="post" >
                        <fieldset class="form-group">
                            <div> <label>Category ID</label></div>  
                            <input readonly="" name="cid" value="${cate.cid}"/>
                        </fieldset>
                        <fieldset class="form-group">
                            <label>Category Name</label> 
                            <input type="text" value="${cate.name}" class="form-control"
                                   name="category_name" id="name" required oninvalid="this.setCustomValidity('Please Enter valid email')">
                        </fieldset>

                        <div class="text-right">

                            <button type="submit" value="Update" onclick="return checkInput()" class="btn btn-success">Save</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <script>
            function checkinput() {
                // Lấy giá trị đầu vào
                var categoryName = document.getElementsByName("category_name")[0].value;

                // Kiểm tra không bỏ trống
                if (categoryName.trim() === "") {
                    alert("Tên Danh Mục là bắt buộc.");
                    return false;
                }

                // Kiểm tra không quá 10 ký tự
                if (categoryName.length > 10) {
                    alert("Tên Danh Mục không được quá 10 ký tự.");
                    return false;
                }

                // Nếu tất cả đều hợp lệ, trả về true
                return true;
            }


    
        </script>
    </body>
</html>
