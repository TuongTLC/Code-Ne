<%-- 
    Document   : shoping
    Created on : Aug 17, 2021, 8:03:38 PM
    Author     : tuongtlc
--%>

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
            UserDTO loginUser = (UserDTO)session.getAttribute("LOGIN_USER");
            if (loginUser == null || loginUser.getRoleID() != 2) {
                    response.sendRedirect("login.jsp");
                    return;
                }
        %>
        <h1>Hello <%= loginUser.getFullName() %>!</h1>
        <a href="MainController?action=Logout">Logout</a>
        
    </body>
</html>
