<%@page import="com.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Product.js"></script>

<meta charset="ISO-8859-1">
<title>Product Management</title>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Product Management</h1>

	<form id="formItem" name="formItem">
		
		Product Name:
		<input id="Pname" name="Pname" type="text" class="form-control form-control-sm"><br> 
		product desc:
		<input id="Pdesc" name="Pdesc" type="text" class="form-control form-control-sm"><br>
		Category:
		<input id="Pcategory" name="Pcategory" type="text" class="form-control form-control-sm"><br>
		product price:
		<input id="Pprice" name="Pprice" type="text" class="form-control form-control-sm"><br>
		 
		
		<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	
	<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
	<br>
	<div id="divItemGrid">
	<%
	Product ProductObj = new Product(); 
	 out.print(ProductObj.readProduct()); 
	%>
	</div>
</div> </div> </div> 
	
</body>
</html>