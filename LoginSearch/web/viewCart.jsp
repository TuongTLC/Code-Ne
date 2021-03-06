<%-- 
    Document   : viewCart
    Created on : Aug 17, 2021, 9:09:25 AM
    Author     : trinh
--%>

<%@page import="tuongtlc.tea.TeaDTO"%>
<%@page import="tuongtlc.tea.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>
        <h1>This is your Cart!</h1>
        <%
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if (cart != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                    <th>Delete</th>
                    <th>Update</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    float total = 0;
                    for (TeaDTO tea : cart.getCart().values()) {
                        total+= tea.getPrice() * tea.getQuantity();
                %>
                <form action="MainController" method="POST">
                <tr>
                    <td><%= count++ %></td>
                    <td><%= tea.getId() %></td>
                    <td><%= tea.getName() %></td>
                    <td>
                        <input type="number" name="quantity" value="<%= tea.getQuantity()%>" required="" min="1" max="99"/>
                    </td>
                    <td><%= tea.getPrice() %></td>
                    <td><%= tea.getPrice() * tea.getQuantity() %></td>
                    <td>
                        <input type="submit" name="action" value="Remove"/>
                        <input type="hidden" name="id" value="<%= tea.getId() %>" />
                    </td>
                    <td>
                        <input type="submit" value="Edit" name="action" />
                    </td>
                </tr>
                </form>
                <%
                    }
                %>
            </tbody>
        </table>   
            <h1>Total: <%= total %></h1>
            <a href="shoping.jsp">Add more items</a>
        <%
            }
        %>
    </body>
</html>
