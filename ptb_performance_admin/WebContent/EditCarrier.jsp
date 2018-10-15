<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import =" java.util.List"%>
<%@ page import =" br.com.cleartech.ptb_performance_admin.util.Carrier"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%  
// retrieve your list from the request, with casting 
@SuppressWarnings("unchecked")
Carrier carrier = (Carrier) request.getAttribute("Carrier");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Dados de Prestadora - SPID[<%=carrier.getSpid()%>]</title>
<link rel="stylesheet" type="text/css" href="default_style.css">
</head>
<body>

<h2>Editar Dados de Prestadora</h2>


<form action="EditCarrier" method="POST"">
SPID&nbsp;:&nbsp;<input type="text" value="<%=carrier.getSpid()%>" name="spid" size="10" disabled ><br />
NOME&nbsp;:&nbsp;<input type="text" value="<%=carrier.getName()%>" name="name" size="30" ><br />
<%String carrier_status = String.valueOf(carrier.getStatus());%>
<%if (carrier_status.equals("1")) { %>
	STATUS&nbsp;:&nbsp;<input type="radio" name="status" value="true" checked="checked" >&nbsp;ATIVA&nbsp;&nbsp;&nbsp;<input type="radio" name="status" value="false" >&nbsp;INATIVA
<%} else { %>
	STATUS&nbsp;:&nbsp;<input type="radio" name="status" value="true" >&nbsp;ATIVA&nbsp;&nbsp;&nbsp;<input type="radio" name="status" value="false" checked="checked" >&nbsp;INATIVA
<%}; %>
<br />
<input type="submit" value="Salvar">
</form>
<br />
<br />
<input type="button" value="voltar" onclick="window.location='<%=request.getContextPath()%>/ListCarrier';">
</body>
</html>