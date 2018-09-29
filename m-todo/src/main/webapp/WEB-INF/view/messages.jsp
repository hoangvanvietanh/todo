<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.center {
	position: absolute;
	top: 50%;
	left: 50%;
	margin-right: -50%;
	width: 200px;
	height: 300px;
	transform: translate(-50%, -50%);
	background-color: gray;
}
</style>
</head>
<body>
	<div class="center">

		<c:forEach var="mes" items="${mes}">
			<c:if test="${mes.user1.name eq name}">
				<div>${mes.user1.name}</div>
			</c:if>

			<c:if test="${mes.user2.name eq name}">
				<div>${mes.user1.name}</div>
			</c:if>
			<div>${mes.messages}</div>
		</c:forEach>
		<form action="messages" method="post">
			<input type="text" name="mess"> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
			<button type="submit">Send</button>
		</form>
	</div>
</body>
</html>