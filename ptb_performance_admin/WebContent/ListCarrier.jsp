<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import =" java.util.List"%>
<%@ page import =" br.com.cleartech.ptb_performance_admin.util.Carrier"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%  
// retrieve your list from the request, with casting 
@SuppressWarnings("unchecked")
List<Carrier> CarrierList = (List<Carrier>) request.getAttribute("listCarrier");
%>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PTB Performance Admin</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
<link href="resources/dashboard.css" rel="stylesheet">
</head>

  <body>
    <%@include  file="resources/baseTheme.html" %>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
          <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h1 class="h2">Listagem de Prestadoras</h1>
            <div class="btn-toolbar mb-2 mb-md-0">
              <div class="btn-group mr-2">
                <button class="btn btn-sm btn-outline-secondary">Compartilhar</button>
                <button class="btn btn-sm btn-outline-secondary">Exportar</button>
              </div>
            </div>
          </div>    
          <div class="table-responsive">
            <table class="table table-striped table-sm">
        	<thead class="thead-dark">
            <tr>
                <th scope="col">SPID</th>
                <th scope="col">NOME</th>
                <th scope="col">STATUS</th>
                <th scope="col">A&Ccedil;&Otilde;ES</th>                
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
     <td>
     	<a href="<%=request.getContextPath()%>/CarrierData?spid=<%=carrier.getSpid()%>" data-toggle="modal" data-target="#carrierDetailModal"><span data-feather="edit"></span></a>
     	<a href="#" data-toggle="modal" data-target="#ModalDeleteConfirm" data-spid="<%=carrier.getSpid()%>"><span data-feather="trash-2"></span></a>   	
     	
     </td>
    </tr>
 <%} %>
              </tbody>
            </table>                        
          </div>
        </main>
      </div>
    </div>
    
<%@include  file="resources/confirmDeleteModal.html" %>

<%@include  file="resources/carrierDetailModal.html" %>
<%@include  file="resources/defaulScripts.html" %>
    


  </body>
</html>