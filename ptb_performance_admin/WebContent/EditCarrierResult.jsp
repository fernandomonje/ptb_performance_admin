<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import=" java.util.List"%>
<%@ page import=" br.com.cleartech.ptb_performance_admin.util.Carrier"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	// retrieve your list from the request, with casting 
	@SuppressWarnings("unchecked")
	Carrier carrier = (Carrier) request.getAttribute("Carrier");
	String update_status = (String) request.getAttribute("update_status");
	String no_changes = (String) request.getAttribute("no_changes");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Resultado de Edi&ccedil;&atilde;o de Dados de Prestadora
	- SPID[<%=carrier.getSpid()%>]
</title>
<link rel="stylesheet" type="text/css" href="default_style.css">
</head>
<body>

	<h2>Resultado de edi&ccedil;&atilde;o de Dados de Prestadora</h2>


	<%
		if (no_changes.equals("true")) {
	%>
	<h3>Nenhuma informa&ccedil;&atilde;o alterada.</h3>
	<%
		} else if (!no_changes.equals("true") && update_status.equals("true")) {
	%>
	<h3>Altera&ccedil;&otilde;es aplicadas com sucesso.</h3>
	<%
		} else if (!no_changes.equals("true") && !update_status.equals("true")) {
	%>
	<h3>
		<font color="red"></font>Falha na atualiza&ccedil;&atilde;o dos dados.</font>
	</h3>
	<%
		}
		;
	%>
	<br />
	<br />
	<input type="button" value="voltar"
		onclick="window.location='<%=request.getContextPath()%>/ListCarrier';">
</body>
</html>