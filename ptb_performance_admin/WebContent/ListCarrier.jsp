<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import=" java.util.List"%>
<%@ page import=" br.com.cleartech.ptb_performance_admin.util.Carrier"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	// retrieve your list from the request, with casting 
	@SuppressWarnings("unchecked")
	List<Carrier> CarrierList = (List<Carrier>) request.getAttribute("listCarrier");
%>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PTB Performance Admin</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
	integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
	crossorigin="anonymous">
<link href="resources/dashboard.css" rel="stylesheet">
</head>
<body>
	<!-- This page is build using all the includes defined in this file, the design was done in this way
  		  to facilitate the management and maintenance.
  		  Every new resource needed in this page should follow the same design pattern.
  	 -->


	<!-- Including header and navigation bar -->
	<%@include file="resources/includes/mainHeader_Nav.html"%>

	<!-- Including main table -->
	<%@include file="resources/includes/mainTable.html"%>

	<!-- Including delete modal -->
	<%@include file="resources/includes/confirmDeleteModal.html"%>

	<!-- Including carrier detail modal -->
	<%@include file="resources/includes/carrierDetailModal.html"%>

	<!-- Including Insert Carrier modal -->
	<%@include file="resources/includes/insertCarrierModal.html"%>

	<!-- Including all the required scripts -->
	<%@include file="resources/includes/defaulScripts.html"%>

</body>
</html>