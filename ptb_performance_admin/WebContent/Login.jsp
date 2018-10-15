<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% String messages = (String) request.getAttribute("messages"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>


<form action="Login" method="POST">
Usuario:&nbsp;&nbsp;<input type="text" name="username" size="20"><br />
Senha:&nbsp;&nbsp;<input type="password" name="password" size="20"><br />
<%if(messages != null) { %>
	<h3><font color="Red"><%=messages %></font></h3>
<%} %>
<input type="submit" value="Login">

</form>

</body>
</html>