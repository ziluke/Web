<%@ page import="Model.User" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
//    out.println(user);
    out.println(user.getUsername());
    if (user.getPlayerNo() == 0) {
        boolean ok = true;
        out.println("Welcome " + user.getUsername() + " we are awaiting your opponent");

    } else if (user.getPlayerNo() == 1) {
        out.println("Start Game");

    } else if (user.getPlayerNo() > 1) {
        out.println("Sorry " + user.getUsername() + " there are already two players playing this game");
    }
%>
<form action="StartGameServlet" method="get">
    <input type="submit" value="Try to Start Game"/>
</form>
<form action="LogOutServlet" method="get">
    <input type="submit" value="Logout"/>
</form>
</body>
</html>