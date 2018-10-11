<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import =" java.util.List"%>
<%@ page import =" br.com.cleartech.ptb_performance_admin.Carrier"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%  
// retrieve your list from the request, with casting 
@SuppressWarnings("unchecked")
List<Carrier> CarrierList = (List<Carrier>) request.getAttribute("listCarrier");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listagem de Prestadora</title>
<link rel="stylesheet" type="text/css" href="default_style.css">
</head>
<body>

<h2>Listagem de Prestadoras</h2>
    <table>
        <thead>
            <tr>
                <th>SPID</th>
                <th>NOME</th>
                <th>STATUS</th>
                <th>DETALHES</th>
            </tr>
        </thead>
        <tbody>

    <% for(int i = 0; i < CarrierList.size(); i++) {
                Carrier carrier = new Carrier();
                carrier = CarrierList.get(i);
    %>
	 <tr>
     <td><%=carrier.getSpid()%></td>
     <td><%=carrier.getName()%></td>
     <td><%=carrier.getStatus()%></td>
     <td><a href="<%=request.getContextPath()%>/CarrierData?spid=<%=carrier.getSpid()%>">DETALHES</a></td>
    </tr>
<%
};
%>
</tbody>
</table>
</body>
</html>