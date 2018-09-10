<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
.input{
	border: none;
    background: white;
	height: 20px;

}
</style>
</head>
<body>
	<table class="table">
		<tr>
			<th>No.</th>
			<th>Name</th>
			<th>Status</th>
			<th>Start Date</th>
			<th>Started At</th>
			<th>Ended At</th>
			<th>Actions</th>
		</tr>
		<%
			int i = 1;
		%>
		<c:forEach var="todo" items="${todo}">
			<tr>
				<td><%=i++%></td>
				<td>${todo.name}</td>
				<td>${todo.status}</td>
				<td>${todo.startDate}</td>
				<td>${todo.id}</td>
				<td></td>
				<c:if test="${todo.status eq 'New[2]'}">
						<form:form action="todo/update?action=start" method="post">
						<input type="hidden" name="id" value="${todo.id}">
						<td><button class="input" type="submit">Start</button></td>
						</form:form>
					</c:if>
					<c:if test="${todo.status eq 'New[2]'}">
						<form:form action="todo/update?action=cancel" method="post">
							<input type="hidden" name="id" value="${todo.id}">
							<td><button class="input" type="submit">Cancel</button></td>
						</form:form>
					</c:if> 
					<c:if test="${todo.status eq 'New[1]' or todo.status eq 'New[2]'}">
						<form:form action="todo/update?action=view" method="post">
						<input type="hidden" name="id" value="${todo.id}">
						<td><button class="input" type="submit">View</button></td>
						</form:form>
					</c:if> 
					<c:if test="${todo.status eq 'New[1]' or todo.status eq 'New[2]'}">
						<form:form action="todo/update?action=delete" method="post">
						<input type="hidden" name="id" value="${todo.id}">
							<td><button class="input" type="submit">Delete</button></td>
						</form:form>
					</c:if>
			</tr>
		</c:forEach>

	</table>
</body>
</html>