<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import=" java.util.Map"%>
<%@ page import=" java.util.HashMap"%>
<%
	Map<String, String> messages = (HashMap) request.getAttribute("messages");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link href="resources/css/login.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body class="text-center">


<div class="form-signin shadow border border-primary rounded bg-light text-white">
	<form action="Login" method="POST" class="form-signin">
		<div class="position: absolute">
		<h4 class="h4 font-weight-normal text-secondary">PTB Performance Admin</h4>
		</div>
		<label for="username" class="sr-only">Usu&aacute;rio</label> <input
			type="text" id="username" name="username" class="form-control"
			placeholder="Usuario de Rede" required autofocus> <label
			for="password" class="sr-only">Senha</label> <input type="password"
			id="password" name="password" class="form-control"
			placeholder="Senha" required>

		<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>

		<%
			if (messages != null) {
		%>
		<hr />
		<div class="alert alert-danger" role="alert">
			<%
				if (messages.get("username") != null) {
			%>
			<%=messages.get("username")%><br />
			<%
				}
			%>
			<%
				if (messages.get("password") != null) {
			%>
			<%=messages.get("password")%>
			<br />
			<%
				}
			%>
			<%
				if (messages.get("login") != null) {
			%>
			<%=messages.get("login")%>
			<br />
			<%
				}
			%>
		</div>
		<%
			}
		%>

	</form>
	<img src="resources/includes/ctech_logo_black_no_bg.png" style="width: 75px; height: 30px;opacity: 0.5;">

</div>
</body>
</html>