<%-- 
    Document   : createUser
    Created on : Aug 16, 2021, 9:11:54 AM
    Author     : trinh
--%>

<%@page import="tuongtlc.users.UserErr"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link rel="stylesheet" href="ProjCSS.css"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>
    </head>
    <body>
        <div class="background">
            <div class="innerDiv">
                <%
                    UserErr error = (UserErr) request.getAttribute("ERROR_USER");
                    if (error == null) {
                        error = new UserErr();
                    }
                %>
                <h1>Create New User</h1>
                <form action="MainController" method="POST">
                    User Name <input type="text" name="txtUserName" required=""/><br/>
                    <font style="color:red; font-size:10px"><%= error.getUserNameError()%><br/></font>
                    
                    Full Name <input type="text" name="txtUserFullName" required="" /><br/>
                    <font style="color:red; font-size:10px"><%= error.getUserFullNameError()%><br/></font>
                    
                    Password <input type="password" name="txtPassword" required="" /><br/>
                    <font style="color:red; font-size:10px"><%= error.getPasswordError()%><br/></font>
                    
                    Confirm <input type="password" name="txtConfirm" required="" /><br/>
                    <font style="color:red; font-size:10px"><%= error.getConfirmError()%><br/></font>
                    
                    <input type="submit" value="Create" name="action" />
                    <input type="reset" value="Reset" />
                </form>
            </div>
        </div>
    </body>
</html>
