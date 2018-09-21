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
.input {
	border: none;
	background: none;
	height: 20px;
}

.b {
	float: left;
}

.New1 {
	background: white;
}

.New2 {
	background: #CD853F;
}

.Done {
	background: #FFD700;
}

.In-progress {
	background: #00FFFF;
}

.Canceled {
	background: #A52A2A;
}
</style>
</head>
<body>
	<table id="example" class="table">
		<thead>
			<tr>
				<th>No.</th>
				<th>Name</th>
				<th>Status</th>
				<th>Start Date</th>
				<th>Started At</th>
				<th>Ended At</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<%
				int i = 1;
			%>
			<c:forEach var="todo" items="${todo}">
				<tr class="${todo.status}">
					<td>${i=i+1}</td>
					<td>${todo.name}</td>
					<td>${todo.status}</td>
					<td>${todo.startDate}</td>
					<td>${todo.startedAt}</td>
					<td>${todo.endedAt }</td>
					<td>
						
							<c:if test="${todo.status eq 'New2' }">
								<form action="todo/start" method="post">
									<input type="hidden" name="id" value="${todo.id}">
									<div class="b">
									<button class="input" type="submit">Start</button>
									</div>
								</form>
							</c:if>
							<c:if test="${todo.status eq 'New2'}">
								<form action="todo/cancel" method="post">
									<input type="hidden" name="id" value="${todo.id}">
									<div class="b">
									<button class="input" type="submit">Cancel</button>
									</div>
								</form>
							</c:if>
							<c:if test="${todo.status eq 'In-progress' }">
								<form action="todo/end" method="post">
									<input type="hidden" name="id" value="${todo.id}">
									<div class="b">
									<button class="input" type="submit">End</button>
									</div>
								</form>
							</c:if>
							<c:if
								test="${todo.status eq 'New1' or todo.status eq 'New2' or todo.status eq 'In-progress' or todo.status eq 'Done'or todo.status eq 'Canceled'}">
								<form action="todo/view" method="get">
									<input type="hidden" name="id" value="${todo.id}">
									<div class="b">
									<button class="input" type="submit">View</button>
									</div>
								</form>
							</c:if>
							<c:if test="${todo.status eq 'New1'}">
								<form action="todo/edit" method="get">
									<input type="hidden" name="id" value="${todo.id}">
									<div class="b">
									<button class="input" type="submit">Edit</button>
									</div>
								</form>
							</c:if>
							<c:if
								test="${todo.status eq 'New1' or todo.status eq 'New2' or todo.status eq 'Canceled'}">
								<form action="todo/delete" method="post">
									<input type="hidden" name="id" value="${todo.id}">
									<div class="b">
									<button class="input" type="submit">Delete</button>
									</div>
								</form>
							</c:if>
						</div>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<script src="<spring:url value='/resources/js/jquery-3.3.1.js' />"></script>
	<script
		src="<spring:url value='/resources/js/jquery.dataTables.min.js' />"></script>
	<script
		src="<spring:url value='/resources/js/dataTables.bootstrap.min.js' />"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#example').DataTable();
		});
	</script>
	<button type="button"
		onclick="window.location.href='<spring:url value="/todo/create" />'">Create</button>

		<c:forEach var="i" begin="1" end="${num}">
			<form action="todo">
				<input type="hidden" name="id" value="${id}"> <input
					type="hidden" name="numberPage" value="${i}">
				<div class="b">
					<button type="submit">${i}</button>
				</div>
			</form>
		</c:forEach>
</body>
</html>