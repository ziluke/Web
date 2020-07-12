<%@ page import="Model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lost</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    out.println("Maybe next time "+ user.getUsername()+ " !");
%>
</body>
<form action="LogOutServlet" method="get">
    <input type="submit" value="Logout"/>
</form>
</body>
</html>
