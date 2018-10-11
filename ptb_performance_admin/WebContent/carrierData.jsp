<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import =" java.util.List"%>
<%@ page import =" br.com.cleartech.ptb_performance_admin.Carrier"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%  
// retrieve your list from the request, with casting 
@SuppressWarnings("unchecked")
Carrier carrier = (Carrier) request.getAttribute("Carrier");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dados de Prestadora - SPID[<%=carrier.getSpid()%>]</title>
<link rel="stylesheet" type="text/css" href="default_style.css">
</head>
<body>

<h2>Dados de Prestadora</h2>
    <table>
        <thead>
            <tr>
                <th>SPID</th>
                <th>NOME</th>
                <th>STATUS</th>
            </tr>
        </thead>
        <tbody>
	 <tr>
     <td><%=carrier.getSpid()%></td>
     <td><%=carrier.getName()%></td>
     <td><%=carrier.getStatus()%></td>
    </tr>
</tbody>
</table>
<br />
<br />
<input type="button" value="voltar" onclick="window.location='<%=request.getContextPath()%>/ListaSPID';">
</body>
</html>