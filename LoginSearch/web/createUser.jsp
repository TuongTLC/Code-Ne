<%-- 
    Document   : createUser
    Created on : Aug 16, 2021, 9:11:54 AM
    Author     : trinh
--%>

<%@page import="tuongtlc.user.UserError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>
    </head>
    <body>
        <%
            UserError error = (UserError)request.getAttribute("ERROR_USER");
            if(error == null) {
                    error = new UserError();
                }
        %>
        <h1>Create New User</h1>
        <form action="MainController" method="POST">
            User ID <input type="text" name="txtUserID" required=""/><br/>
            <%= error.getUserIDError() %><br/>
            Full Name <input type="text" name="txtFullName" required="" /><br/>
            <%= error.getFullNameError() %><br/>
            Password <input type="password" name="txtPassword" required="" /><br/><br/>
            Confirm <input type="password" name="txtConfirm" required="" /><br/>
            <%= error.getPasswordError() %><br/>
            <input type="submit" value="Create" name="action" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</html>
