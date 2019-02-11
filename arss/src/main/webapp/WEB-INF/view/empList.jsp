<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee List</title>
<link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>
<body>
	<div class="container col-md-10">
		<h2>Employee List</h2>
		<table class="table table-striped">
			<thead>
				<th scope="row">ID</th>
				<th scope="row">Name</th>
				<th scope="row">Designation</th>
				<th scope="row">Experties</th>
				<th scope="row">Update</th>
				<th scope="row">Delete</th>
			</thead>
			<tbody>
				<c:forEach items="${empList}" var="emp">
					<tr>
						<td>${emp.id}</td>
						<td>${emp.name}</td>
						<td>${emp.designation}</td>
						<td>${emp.experties}</td>
						<td><spring:url value="/company/updateEmp/${emp.id}"
								var="updateURL" /> <a class="btn btn-warning"
							href="${updateURL}" role="button">Update</a></td>
						<td><spring:url value="/company/removeEmp/${emp.id}"
								var="deleteURL" /> <a class="btn btn-danger"
							href="${deleteURL}" role="button">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<spring:url value="/company/addEmp/" var="addURL" />
		<a class="btn btn-primary" href="${addURL}" role="button">Add
			New Employee</a>
	</div>
</body>
</html>