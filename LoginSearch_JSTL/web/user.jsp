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
        <c:if   test="${(sessionScope.LOGIN_USER.fullName == null) or  (sessionScope.LOGIN_USER.roleID ne 'US')}">
                    <c:redirect url="login.jsp"></c:redirect>
                </c:if>
        <h1>Hello User: ${sessionScope.LOGIN_USER.fullName}</h1>
        <a href="MainController?action=Logout">Logout</a>
    </body>
</html>
