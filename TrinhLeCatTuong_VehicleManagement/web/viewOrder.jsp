<%-- 
    Document   : viewOrder
    Created on : Aug 18, 2021, 10:43:40 PM
    Author     : tuongtlc
--%>

<%@page import="tuongtlc.products.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="tuongtlc.order.OrderProductDTO"%>
<%@page import="tuongtlc.order.OrderDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Order Page</title>
    </head>
    <body>
        <h1>View Order</h1>
        <form action="MainController" method="POST">
            Order ID <input type="text" name="searchOrder" />
            <input type="submit" value="Search Order" name="action"/>
        </form>
        <%
            String errorMsg = (String) request.getAttribute("message");
            if (errorMsg == null) {
                errorMsg = "";
            }
            OrderDTO order = (OrderDTO) request.getAttribute("searchedOrder");
            List<ProductDTO> proDTO = (List<ProductDTO>) request.getAttribute("productNameList");
            List<OrderProductDTO> orderItems = (List<OrderProductDTO>) request.getAttribute("orderItems");
            if (order != null && !orderItems.isEmpty()) {
        %>
        <p>Order ID: <%= order.getOrderID()%></p>
        <p>User Name : <%= order.getUserName()%></p>
        <table border="1">
            <thead>
                <tr>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <%
                    float billTotal = 0;
                    for (OrderProductDTO item : orderItems) {
                        for (ProductDTO orderItem : proDTO) {
                            if (item.getProductID() == orderItem.getProductID()) {

                %>
                <tr>
                    <td><%= orderItem.getProductName()%></td>
                    <td><%= item.getQuantity()%></td>
                    <td><%= orderItem.getProductPrice()%>$</td>
                    <td><%= item.getQuantity() * orderItem.getProductPrice()%>$</td>
                    <%
                        billTotal += item.getQuantity() * orderItem.getProductPrice();
                    %>
                </tr>
                <%
                            }
                        }

                    }%>
            </tbody>
        </table>
            <h1> Bill total: <%= billTotal %></h1>
        <%
            }
        %>
        <%
            String erroMsg = (String) request.getAttribute("message");
            if (erroMsg == null) {
                erroMsg = "";
            }
        %>
        <font color="red">
            <%= erroMsg %>
        </font>
    </body>
</html>
