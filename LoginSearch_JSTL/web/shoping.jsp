<%-- 
    Document   : shoping
    Created on : Aug 17, 2021, 8:50:41 AM
    Author     : trinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop Page</title>
    </head>
    <body>
        <h1>Welcome to our shop!</h1>
        <form action="MainController" method="POST">
            Please select your tea:
            <select name="cmbTea">
                <option value="T01-Olong-50">O long Tea</option>
                <option value="T02-C2-20">C2 Tea</option>
                <option value="T03-Khong DO-10">0 Do Tea</option>
                <option value="T04-Tra Den-55">Black Tea</option>
            </select>
            <input type="submit" value="Add" name="action" />
            <input type="submit" value="View" name="action" />
        </form>
        <font color="red">
        ${requestScope.message}
            
        </font>
    </body>
</html>
