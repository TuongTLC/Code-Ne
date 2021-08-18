<%-- 
    Document   : shoping
    Created on : Aug 17, 2021, 8:03:38 PM
    Author     : tuongtlc
--%>

<%@page import="java.util.List"%>
<%@page import="tuongtlc.products.BrandDTO"%>
<%@page import="tuongtlc.products.ProductDTO"%>
<%@page import="tuongtlc.users.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shoping Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
            if (loginUser == null || loginUser.getRoleID() != 2) {
                //loginUser.setFullName("");
        %>
        <h1>Welcome to the Showroom!</h1>
        <a href="login.jsp">Login</a><br/>
        <%
        } else {
        %>
        <h1>Hello <%= loginUser.getFullName()%>!</h1>
        <a href="MainController?action=Logout">Logout</a>
        <%
            }
        %>
         <form action="MainController">
            Search <input type="text" name="search" value="<%= search %>"/>
            <input type="submit" value="Search" name="action"/>
            <input type="submit" value="Show All" name="action"/><br/>
            <a href="viewCart.jsp">View Cart</a>
            <a href="viewOrder.jsp">View Order</a>
        </form>
        <%
            List<ProductDTO> list = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
            if (list != null && !list.isEmpty()) {
        %>
        <div class="tableDiv">
            <table border="1">
                <thead>
                    <tr>
                        <th>product Name</th>
                        <th>Price</th>
                        <th>Brand Name</th>
                        <th>Description</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (ProductDTO product : list) {
                    %>
                <form action="MainController" method="POST">
                    <tr>
                        <td>
                                <%= product.getProductName()%>
                        </td>
                        <td>
                            <%= product.getProductPrice()%>$
                        </td>
                        <td>

                            <%
                                List<BrandDTO> brandList = (List<BrandDTO>) request.getAttribute("LIST_BRAND");
                                for (BrandDTO brand : brandList) {
                                    if (brand.getBrandID() == product.getBrandID()) {
                            %>
                                <%= brand.getBrandName()%>
                            <%
                                    }
                                }
                            %>
                        </td>
                        <td>
                            <%= product.getDescription()%>
                        </td>
                        <td>
                            <%= product.getQuantity()%>
                        </td>
                        <td>
                            <a href="MainController?productID=<%= product.getProductID()%>&action=AddToCart&search=<%=search%>">Add to Cart</a>
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
            }
        %>
        <%
            String errorMsg = (String) request.getAttribute("message");
            if (errorMsg == null) {
                errorMsg = "";
            }
        %>
        <font color="red">
        <%= errorMsg %>
        </font>
    </body>
</html>
