<%-- 
    Document   : viewCart
    Created on : Aug 17, 2021, 9:09:25 AM
    Author     : trinh
--%>

<%@page import="tuongtlc.users.UserDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="tuongtlc.products.CartProductDTO"%>
<%@page import="tuongtlc.products.CartDTO"%>
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
            UserDTO loginUser = (UserDTO)session.getAttribute("LOGIN_USER");
            if (loginUser == null || (loginUser.getRoleID()!=2)) {
                    response.sendRedirect("login.jsp");
                    return;
                }
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            if (cart != null) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>No</th>
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
                    List<CartProductDTO> order = new ArrayList<>();
                    for (CartProductDTO pro : cart.getCart().values()) {
                        order.add(pro);
                        total+= pro.getPrice() * pro.getQuantity();
                %>
                <form action="MainController" method="POST">
                <tr>
                    <td><%= count++ %></td>
                    <td>
                        <%= pro.getName() %>
                    </td>
                    <td>
                        <input type="number" name="quantity" value="<%= pro.getQuantity()%>" required="" min="1" max="99"/>
                    </td>
                    <td><%= pro.getPrice() %> $</td>
                    <td><%= pro.getPrice() * pro.getQuantity() %>$</td>
                    <td>
                        <input type="submit" name="action" value="Remove"/>
                        <input type="hidden" name="id" value="<%= pro.getName() %>" />
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
                <h1>Total: <%= BigDecimal.valueOf(total)+"" %>$</h1>
                <%
                    String search = request.getParameter("search");
                %>
            
            <form action="MainController" method="POST">
                <%
                session.setAttribute("orderList", order);
            %>
                <input type="submit" value="Check out"  name="action"/>
            </form>
            
        <%
            }
        %>
        <a href="shoping.jsp">Add more items</a>
    </body>
</html>
