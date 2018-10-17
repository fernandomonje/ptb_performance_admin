<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import =" java.util.List"%>
<%@ page import =" br.com.cleartech.ptb_performance_admin.util.Carrier"%>
<%  
// retrieve your list from the request, with casting 
@SuppressWarnings("unchecked")
Carrier carrier = (Carrier) request.getAttribute("Carrier");
%>

<div class="modal-header">
  <button type="button" class="close" data-dismiss="modal">X</button>
  <h1>Dados de Prestadora - SPID[<%=carrier.getSpid()%>]</h1>
</div>
<div class="modal-body">
  <div class="panel panel-default">
    <div class="panel-heading text-center">
      Dados de Prestadora
    </div>
    <div class="panel-body">


<form action="">
SPID&nbsp;:&nbsp;<input type="text" value="<%=carrier.getSpid()%>" size="10" disabled><br />
NOME&nbsp;:&nbsp;<input type="text" value="<%=carrier.getName()%>" size="30" disabled><br />
<%String carrier_status = String.valueOf(carrier.getStatus());%>
<%if (carrier_status.equals("1")) { %>
	STATUS&nbsp;:&nbsp;<input type="radio" name="status" value="true" checked="checked" disabled>&nbsp;ATIVA&nbsp;&nbsp;&nbsp;<input type="radio" name="status" value="false" disabled>&nbsp;INATIVA
<%} else { %>
	STATUS&nbsp;:&nbsp;<input type="radio" name="status" value="true" disabled>&nbsp;ATIVA&nbsp;&nbsp;&nbsp;<input type="radio" name="status" value="false" checked="checked" disabled>&nbsp;INATIVA
<%}; %>
</form>
<br />
<br />
<input type="button" value="Editar" onclick="window.location='<%=request.getContextPath()%>/EditCarrier?spid=<%=carrier.getSpid()%>';">&nbsp;&nbsp;<input type="button" value="voltar" onclick="window.location='<%=request.getContextPath()%>/ListCarrier';">
</div>
  </div>
  <div class="modal-footer">
    <div class="panel-footer">
      <input type="button" value="Find Employee" id="empbutton" />
      <div class="col-xs-10" id="lblstatus"></div>
    </div>
  </div>
</div>