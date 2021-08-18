<%-- 
    Document   : admin
    Created on : Aug 17, 2021, 8:02:59 PM
    Author     : tuongtlc
--%>

<%@page import="tuongtlc.products.BrandDTO"%>
<%@page import="tuongtlc.products.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="tuongtlc.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || loginUser.getRoleID() != 1) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <h1>Hello <%= loginUser.getFullName()%>!</h1>
        <a href="MainController?action=Logout">Logout</a>
        <form action="MainController">
            Search <input type="text" name="search" value="<%= search%>"/>
            <input type="submit" value="Search" name="action"/>
            <input type="submit" value="Show All" name="action"/>
        </form>
            <a href="createProduct.jsp" >Create New Product</a>
        <%
            List<ProductDTO> list = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
            if (list != null && !list.isEmpty()) {
        %>
        <div class="tableDiv">
            <table border="1">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>product Name</th>
                        <th>Price</th>
                        <th>Brand Name</th>
                        <th>Description</th>
                        <th>Quantity</th>
                        <th>Sold</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (ProductDTO product : list) {
                    %>
                <form action="MainController" method="POST">
                    <tr>
                        <td>
                            <%= product.getProductID()%>
                            <input type="hidden" name="productID" value="<%= product.getProductID()%>"/>
                        </td>
                        <td>
<!--                            <input type="text" name="productName" value="" required=""/>-->
                                <%= product.getProductName()%>
                            
                        </td>
                        <td>
                            <input type="text" name="productPrice" value="<%= product.getProductPrice()%>" required=""/>$
                        </td>
                        <td>

                            <%
                                List<BrandDTO> brandList = (List<BrandDTO>) request.getAttribute("LIST_BRAND");
                                for (BrandDTO brand : brandList) {
                                    if (brand.getBrandID() == product.getBrandID()) {
                            %>
<!--                            <input type="text" name="brandID" value="" />-->
                                <%= brand.getBrandName()%>
                            <%

                                    }
                                }
                            %>



                        </td>
                        <td>
                            <input type="text" name="productDescription" value="<%= product.getDescription()%>" required=""/>
                        </td>
                        <td>
                            <input type="text" name="productQuantity" value="<%= product.getQuantity()%>" required=""/>
                        </td>
                        <td>
                            <input type="text" name="productSold" value="<%= product.getSold()%>" required=""/>
                        </td>


                        <td>
                            <a href="MainController?productID=<%= product.getProductID()%>&action=Delete&search=<%=search%>">Delete</a>
                        </td>
                        <td>
                            <input type="submit" value="Update" name="action"/>
                            <input type="hidden" name="search" value="<%= search%>"/>
                        </td>
                    </tr>
                </form>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
        <%
            String errorMsg = (String) request.getAttribute("ERROR");
            if (errorMsg == null) {
                errorMsg = "";
            }
        %>
        <font color="red">
        <%= errorMsg%>
        </font>
        <%
            }
        %>
    </body>
</html>
