<%-- 
    Document   : user
    Created on : Aug 12, 2021, 7:56:58 AM
    Author     : trinh
--%>

<%@page import="tuongtlc.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>
        <% 
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser==null || !"US".equals(loginUser.getRoleID())) {
                    response.sendRedirect("login.jsp");
                    return;
                }
        %>
        <h1>Hello User: <%= loginUser.getFullName() %></h1>
        <a href="MainController?action=Logout">Logout</a>
    </body>
</html>
