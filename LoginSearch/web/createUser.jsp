<%-- 
    Document   : createUser
    Created on : Aug 16, 2021, 9:11:54 AM
    Author     : trinh
--%>

<%@page import="tuongtlc.user.UserError"%>
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
                    UserError error = (UserError) request.getAttribute("ERROR_USER");
                    if (error == null) {
                        error = new UserError();
                    }
                %>
                <h1>Create New User</h1>
                <form action="MainController" method="POST">
                    User ID <input type="text" name="txtUserID" required=""/><br/>
                    <font style="color:red; font-size:10px"><%= error.getUserIDError()%><br/></font>
                    
                    Full Name <input type="text" name="txtFullName" required="" /><br/>
                    <font style="color:red; font-size:10px"><%= error.getFullNameError()%><br/></font>
                    
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
