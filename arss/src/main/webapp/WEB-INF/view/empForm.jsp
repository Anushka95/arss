<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Employee</title>
<link href="../../webjars/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" />
<script src="../../webjars/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="../../webjars/jquery/3.0.0/js/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<spring:url value="/company/saveEmp" var="saveUrl" />
		<h2>Employee Form</h2>
		
		<c:if test="${not empty errormsg}">
			<tr>
				<td colspan="2" class="msgSuccess" align="center">

					<div class="msgSuccess" style="color: red; font-weight: bold;">
						<c:out value="${errormsg}"></c:out>
					</div>
				</td>
			</tr>
		</c:if>

		<form:form modelAttribute="empForm" method="post" action="${saveUrl}"
			cssClass="form">
			<form:hidden path="id" />
			<div class="form-group col-md-6">
				<label>Name</label>
				<form:input path="name" cssClass="form-control" id="name" />
			</div>
			<div class="form-group col-md-6">
				<label>Designation</label>
				<form:input path="designation" cssClass="form-control"
					id="designation" />
			</div>
			<div class="form-group col-md-6">
				<label>Experties</label>
				<form:input path="experties" cssClass="form-control" id="experties" />
			</div>
			<button type="submit" class="btn btn-success">Save</button>
		</form:form>

	</div>
</body>
</html>