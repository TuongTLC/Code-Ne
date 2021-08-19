<%-- 
    Document   : viewCart
    Created on : Aug 17, 2021, 9:09:25 AM
    Author     : trinh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="tuongtlc.tea.TeaDTO"%>
<%@page import="tuongtlc.tea.CartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title>
    </head>
    <body>
        <h1>This is your Cart!</h1>

        <c:if test="${sessionScope.CART.cart != null}">
            <c:if test="${not empty sessionScope.CART.cart}">         
                <c:forEach var="cart" varStatus="counter" items="${sessionScope.CART.cart}">
                    <c:set var="total" value="${total +(cart.value.quantity * cart.value.price)}"/>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Quantity</th>
                                <th>Price</th>
                                <th>Total</th>
                                <th>Delete</th>
                                <th>Update</th>
                            </tr>
                        </thead>
                        <tbody>
                        <form action="MainController">
                            <tr>
                                <th>${counter.count}</th>
                                <th>${cart.value.id}</th>
                                <th>${cart.value.name}</th>
                                <th>
                                    <input type="number" name="quantity" value="${cart.value.quantity}" />
                                </th>
                                <th>${cart.value.price}</th>
                                <th>${cart.value.quantity * cart.value.price}</th>
                                <th>
                                    <input type="submit" value="Remove" name="action" />
                                    <input type="hidden" name="id" value="${cart.value.id}" />
                                </th>
                                <th>
                                    <input type="submit" value="Edit" name="action" />
                                </th>
                            </tr>
                        </form>
                    </c:forEach>    
                </c:if>
            </c:if>
        </tbody>
    </table>
    <h1>Total: ${total}</h1>
    <a href="shoping.jsp">Add more items</a>
</body>
</html>
