<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% 
	String insert_status = (String) request.getAttribute("insert_status");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inclus&atilde;o de Prestadora - SPID[<%=request.getParameter("spid")%>]</title>
<link rel="stylesheet" type="text/css" href="default_style.css">
</head>
<body>
<%if(insert_status.equals("true")) { %>
	<p>
		<h3>Prestadora <%=request.getParameter("name")%> cadastrada com sucesso.</h3>
	</p>
<%} else { %>
	<p>
		<h3><font color="red">Falha no cadastro da prestadora <%=request.getParameter("name")%>.</font></h3>
	</p>
<%} %>
<br />
<br />
<input type="button" value="voltar" onclick="window.location='<%=request.getContextPath()%>/ListaSPID';">
</body>
</html>