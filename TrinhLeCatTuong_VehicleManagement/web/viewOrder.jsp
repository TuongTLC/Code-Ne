<%-- 
    Document   : viewOrder
    Created on : Aug 18, 2021, 10:43:40 PM
    Author     : tuongtlc
--%>

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
        %>
        <font color="red">
        <%= errorMsg %>
        </font>
    </body>
</html>
