<%-- 
    Document   : index
    Created on : Aug 12, 2021, 7:04:05 AM
    Author     : trinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Login Page</h1>
        <form action="MainController" method="POST">
            User ID <input type="text" name="txtUserID" /><br/>
            Password <input type="password" name="txtPassword" /><br/>
            <input type="submit" value="Login" name="action" />
            <input type="reset" value="Reset" />
        </form>
        <%
            String error = (String)request.getAttribute("ERROR");
            if (error==null) {
                    error ="";
                }
        %>
        <font color="red">
        <%= error %>
        </font>
    </body>
</html>
