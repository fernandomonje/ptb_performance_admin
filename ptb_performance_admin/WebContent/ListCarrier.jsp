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
<title>Listagem de Prestadora</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
<link href="resources/dashboard.css" rel="stylesheet">
</head>

  <body>
    <nav class="navbar navbar-dark fixed-top bg-dark flex-md-nowrap p-0 shadow">
      <a class="navbar-brand col-sm-3 col-md-2 mr-0" href="#">PTB Performance Admin</a>
      <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
          <a class="nav-link" href="<%=request.getContextPath()%>/Logout">Sair</a>
        </li>
      </ul>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <nav class="col-md-2 d-none d-md-block bg-light sidebar">
          <div class="sidebar-sticky">
            <ul class="nav flex-column">
              <li class="nav-item">
                <a class="nav-link active" href="#">
                  <span data-feather="home"></span>
                  Funcionalidades <span class="sr-only">(atual)</span>
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/InsertCarrier">
                  <span data-feather="file"></span>
                  Nova Operadora
                </a>
              </li>
          </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
          <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h1 class="h2">Dashboard</h1>
            <div class="btn-toolbar mb-2 mb-md-0">
              <div class="btn-group mr-2">
                <button class="btn btn-sm btn-outline-secondary">Compartilhar</button>
                <button class="btn btn-sm btn-outline-secondary">Exportar</button>
              </div>
              <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
                <span data-feather="calendar"></span>
                Esta semana
              </button>
            </div>
          </div>

          <h2>Título da seção</h2>
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
     	<input class="btn btn-primary btn-sm" type="button" value="Detalhes" onclick="location.href='<%=request.getContextPath()%>/CarrierData?spid=<%=carrier.getSpid()%>';">
     	<form action="<%=request.getContextPath()%>/DeleteCarrier" method="POST">
     		<input type="hidden" value="<%=carrier.getSpid()%>" name="spid">
     		<input class="btn btn-secondary btn-sm" type="submit" value="Excluir">
     	</form>
     </td>
    </tr>
 <%} %>
              </tbody>
            </table>                        
          </div>
        </main>
      </div>
    </div>

    <!-- Principal JavaScript do Bootstrap
    ================================================== -->
    <!-- Foi colocado no final para a página carregar mais rápido -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>

    <!-- Ícones -->
    <script src="https://unpkg.com/feather-icons/dist/feather.min.js"></script>
    <script>
      feather.replace()
    </script>

  </body>
</html>