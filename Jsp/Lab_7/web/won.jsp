<%@ page import="Model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Won the game</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    out.println("Congrats "+ user.getUsername()+ " you won !");
%>
</body>
<form action="LogOutServlet" method="get">
    <input type="submit" value="Logout"/>
</form>
</html>
