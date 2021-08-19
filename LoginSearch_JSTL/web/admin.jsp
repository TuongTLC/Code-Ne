<%-- 
    Document   : admin
    Created on : Aug 12, 2021, 7:53:37 AM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="tuongtlc.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="ProjCSS.css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <div class="background">
            <div class="innerSearchDiv">
                
                <c:if   test="${(sessionScope.LOGIN_USER.fullName == null) or  (sessionScope.LOGIN_USER.roleID ne 'AD')}">
                    <c:redirect url="login.jsp"></c:redirect>
                </c:if>
                
                <h1>Welcome : ${sessionScope.LOGIN_USER.fullName} </h1>
                <a href="MainController?action=Logout">Logout</a><br/><br/>
                <c:url var="logoutLink" value="MainController">
                    <c:param name="action" value="Logout"></c:param>
                </c:url>
                <a href="${logoutLink}">Logout JSTL</a>
                <form action="MainController">
                    Search <input type="text" name="search" value="${param.search}"/>
                    <input type="submit" value="Search" name="action"/>
                </form>
                    
                <c:if test="${requestScope.LIST_USER != null}">
                    <c:if test="${not empty requestScope.LIST_USER}">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Role ID</th>
                                    <th>Password</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" varStatus="counter" items="${requestScope.LIST_USER}">
                                <form action="MainController" method="POST">
                                
                                    <tr>
                                    <td>${counter.count}</td>
                                    <td>
                                        <input type="text" name="userID" value="${user.userID}" readonly="">
                                    </td>
                                    <td>
                                        <input type="text" name="fullName" value="${user.fullName}" required="">
                                    </td>
                                    <td>
                                        <input type="text" name="roleID" value="${user.roleID}" required="">
                                    </td>
                                    <td>${user.password}</td>
                                    <td>
                                        <input type="submit" value="Update" name="action" />
                                        <input type="hidden" name="search" value="${requestScope.search}"/>
                                    </td>
                                    <td>
                                        <c:url var="deleteLink" value="MainController">
                                            <c:param name="action" value="Delete"></c:param>
                                            <c:param name="search" value="${param.search}"></c:param>
                                            <c:param name="userID" value="${user.userID}"></c:param>
                                        </c:url>
                                        <a href="${deleteLink}">Delete</a>
                                    </td>
                                </tr>
                                                                </form>

                                </c:forEach>
                            </tbody>
                        </table>
                        ${requestScope.ERROR}
                    </c:if>
                </c:if>
                    
            </div>
        </div>
    </body>
</html>
