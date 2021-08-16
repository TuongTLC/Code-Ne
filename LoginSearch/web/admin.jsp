<%-- 
    Document   : admin
    Created on : Aug 12, 2021, 7:53:37 AM
    Author     : trinh
--%>

<%@page import="java.util.List"%>
<%@page import="tuongtlc.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <% 
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser==null) {
                    response.sendRedirect("login.jsp");
                    return;
                }
            String search = request.getParameter("search");
            if (search==null) {
                    search="";
                }
            if (loginUser == null || !"AD".equals(loginUser.getRoleID())) {
                    response.sendRedirect("login.jsp");
                    return;
                }
        %>
        <h1>Welcome : <%= loginUser.getFullName() %> </h1>
        <a href="MainController?action=Logout">Logout</a><br/><br/>
        <form action="MainController">
            Search <input type="text" name="search" value="<%= search %>"/>
            <input type="submit" value="Search" name="action"/>
        </form>
        <% 
            List<UserDTO> list = (List<UserDTO>)request.getAttribute("LIST_USER");
            if (list!=null && !list.isEmpty()) {
                %>
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>User ID</th>
                            <th>Full Name</th>
                            <th>Role ID</th>
                            <th>Password</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int count=0;
                            for(UserDTO user:list){
                            %>
                    <form action="MainController" method="POST">
                        <tr>
                            <td><%= ++count%></td>
                            <td>
                                <%= user.getUserID()%>
                                <input type="text" name="UserID" value="<%= user.getUserID()%>" readonly=""/>
                            </td>
                                
                            <td>
                                <%= user.getFullName()%>
                                <input type="text" name="fullName" value="<%= user.getFullName()%>" required=""/>
                            </td>
                            <td>
                                <%= user.getRoleID()%>
                                <input type="text" name="roleID" value="<%= user.getRoleID()%>" required=""/>
                            </td>
                            <td><%= user.getPassword()%></td>
                            
                            <td>
                                <a href="MainController?userID=<%= user.getUserID()%>&action=Delete&search=<%=search%>">Delete</a>
                            </td>
                            <td>
                                <input type="submit" value="Update" name="action"/>
                                <input type="hidden" name="search" value="<%= search %>"/>
                            </td>
                        </tr>
                    </form>
                        <%                         
                            }
                        %>
                    </tbody>
                </table>
                    <%
                    String errorMsg = (String)request.getAttribute("ERROR");
                    if (errorMsg==null) {
                            errorMsg="";
                        }
                    %>
                    <font color="red">
                    <%= errorMsg %>
                    </font>
        <%
                }
        %>
    </body>
</html>
