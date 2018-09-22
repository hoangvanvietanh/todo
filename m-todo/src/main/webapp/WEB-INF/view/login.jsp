<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/app.css'/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.center {
	position: absolute;
	top: 50%;
	left: 50%;
	margin-right: -50%;
	transform: translate(-50%, -50%)
}

th {
	padding: 5px;
	text-align: right;
}

input {
	border: none;
	background: transparent;
	border-bottom: 2px dotted black;
}
</style>

</head>
<body>
	<div class="center">
		<form:form modelAttribute="userLogin" action="login" method="post">
			<div class="input-group">
				<label>Email</label>
				<form:input path="email" />
			</div>
			<div class="input-group">
				<label>Password</label>
				<form:input path="password" type="password"/>
			</div>
			<form:button type="submit">Login</form:button>
		</form:form>
	</div>

	<script src="<spring:url value='/resources/js/app.js' />"></script>
</body>
</html>