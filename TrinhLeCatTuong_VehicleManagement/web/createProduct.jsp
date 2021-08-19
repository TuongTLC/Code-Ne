<%-- 
    Document   : createProduct
    Created on : Aug 18, 2021, 4:08:40 PM
    Author     : tuongtlc
--%>

<%@page import="tuongtlc.users.UserDTO"%>
<%@page import="tuongtlc.products.ProductErr"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="ProjCSS.css"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Product Page</title>
    </head>
    <body>
        <div class="background">
            <div class="innerDiv">
                <%
                    ProductErr error = (ProductErr) request.getAttribute("PRODUCT_ERROR");
                    if (error == null) {
                        error = new ProductErr();
                    }
                    UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                    if (loginUser == null || loginUser.getRoleID() != 1) {
                        response.sendRedirect("login.jsp");
                        return;
                    }
                %>
                <h1>Create New Product</h1>
                <form action="MainController" method="POST">
                    Product Name <input type="text" name="txtProductName" required=""/><br/>
                    <font style="color:red; font-size:10px"><%= error.getProductNameErr()%><br/></font>

                    Price <input type="text" name="txtProductPrice" required="" /><br/>
                    <font style="color:red; font-size:10px"><%= error.getPriceErr()%><br/></font>

                    Brand Name <input type="text" name="txtBrand" required="" /><br/>
                    <font style="color:red; font-size:10px"><%= error.getBrandErr()%><br/></font>

                    Description <input type="text" name="txtDescription" required="" /><br/>
                    <font style="color:red; font-size:10px"><%= error.getDescriptionErr()%><br/></font>
                    
                    Quantity <input type="text" name="txtQuantity" required="" /><br/>
                    <font style="color:red; font-size:10px"><%= error.getQuantityErr()%><br/></font>
                    
                    <input type="submit" value="Create Product" name="action" />
                    <input type="reset" value="Reset" />
                </form>
            </div>
        </div>
    </body>
</html>
