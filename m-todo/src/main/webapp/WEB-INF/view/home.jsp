<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo</title>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/home.css'/>">
</head>
<body>
	<form:errors path="*" cssClass="error-box" />
	<button type="button"
		onclick="window.location.href='<spring:url value="/todo/create" />'">Create</button>
	<button type="button"
		onclick="window.location.href='<spring:url value="/logout" />'">Logout</button>
	<table class="table">
		<thead>
			<tr>
				<th style="width: 1%">No.</th>
				<th style="width: 15%">Name</th>
				<th style="width: 10%">Status</th>
				<th style="width: 10%">Start Date</th>
				<th style="width: 10%">Started At</th>
				<th style="width: 10%">Ended At</th>
				<th style="width: 15%">Actions</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="todo" items="${todo}">
				<tr class="${todo.status}">
					<td>${i=i+1}</td>
					<td>${todo.name}</td>
					<td>${todo.status}</td>
					<td>${todo.startDate}</td>
					<td>${todo.startedAt}</td>
					<td>${todo.endedAt}</td>
					<td><c:if test="${todo.status eq 'New2' }">
							<form action="todo/start" method="post">
								<input type="hidden" name="id" value="${todo.id}"> <input
									type="hidden" name="number" value="${page3}">
								<div class="b">
									<button class="input" type="submit">Start</button>
								</div>
							</form>
						</c:if> <c:if test="${todo.status eq 'New2'}">
							<form action="todo/cancel" method="post">
								<input type="hidden" name="id" value="${todo.id}"> <input
									type="hidden" name="number" value="${page3}">
								<div class="b">
									<button class="input" type="submit">Cancel</button>
								</div>
							</form>
						</c:if> <c:if test="${todo.status eq 'In-progress' }">
							<form action="todo/end" method="post">
								<input type="hidden" name="id" value="${todo.id}"> <input
									type="hidden" name="number" value="${page3}">
								<div class="b">
									<button class="input" type="submit">End</button>
								</div>
							</form>
						</c:if> <c:if
							test="${todo.status eq 'New1' or todo.status eq 'New2' or todo.status eq 'In-progress' or todo.status eq 'Done'or todo.status eq 'Canceled'}">
							<form action="todo/view" method="get">
								<input type="hidden" name="id" value="${todo.id}"> <input
									type="hidden" name="number" value="${page3}">
								<div class="b">
									<button class="input" type="submit">View</button>
								</div>
							</form>
						</c:if> <c:if test="${todo.status eq 'New1'}">
							<form action="todo/edit" method="get">
								<input type="hidden" name="id" value="${todo.id}"> <input
									type="hidden" name="number" value="${page3}">
								<div class="b">
									<button class="input" type="submit">Edit</button>
								</div>
							</form>
						</c:if> <c:if
							test="${todo.status eq 'New1' or todo.status eq 'New2' or todo.status eq 'Canceled'}">
							<form action="todo/delete" method="post">
								<input type="hidden" name="id" value="${todo.id}"> <input
									type="hidden" name="number" value="${page3}">
								<div class="b">
									<button class="input" type="submit">Delete</button>
								</div>
							</form>
						</c:if></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="center">
		<c:if test="${page2 gt 1}">
			<form action="todo">
				<input type="hidden" name="id" value="${id}"> <input
					type="hidden" name="numberPage" value="1">
				<div class="b">
					<button type="submit" class="input">|<</button>
				</div>
			</form>
			<form action="todo">
				<input type="hidden" name="id" value="${id}"> <input
					type="hidden" name="numberPage" value="${page2=page2-1}">
				<div class="b">
					<button type="submit" class="input"><</button>
				</div>
			</form>
		</c:if>
		<div class="b">
			<button class="input">${page3}</button>
		</div>
		<c:if test="${page lt num2}">
			<form action="todo">
				<input type="hidden" name="id" value="${id}"> <input
					type="hidden" name="numberPage" value="${page=page+1}">
				<div class="b">
					<button class="input" type="submit">></button>
				</div>
			</form>
			<form action="todo">
				<input type="hidden" name="id" value="${id}"> <input
					type="hidden" name="numberPage" value="${num}">
				<div class="b">
					<button class="input" type="submit">>|</button>
				</div>
			</form>
		</c:if>

	</div>


</body>
</html>