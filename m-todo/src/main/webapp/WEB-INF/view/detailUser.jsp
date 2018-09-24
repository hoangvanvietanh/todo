<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/app.css'/>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User</title>
</head>
<body>
	<div class="center">
		<form:form modelAttribute="user" action="user/update" method="post" enctype="multipart/form-data">
			<form:errors path="*" cssClass="error-box" />
			<div class="input-group">
				<form:input type="hidden" path="id" />
			</div>
			<div class="input-group">
				<label>Avatar</label>
				<input type="file" name="file"/>
			</div>
			<div class="input-group">
				<label>Email</label>
				<form:input path="email" />
			</div>
			<div class="input-group">
				<label>Full name</label>
				<form:input path="name" />
			</div>
			<div class="input-group">
				<label>Gender</label>
				<form:input path="gender" />
			</div>
			<div class="input-group">
				<label>Birth Date</label>
				<form:input path="birthDate" />
			</div>
			<div class="input-group">
				<label>Address</label>
				<form:textarea path="address" rows="3" />
			</div>
			<div class="input-group">
				<label>Phone</label>
				<form:input path="phone" />
			</div>
			<div class="input-group">
				<form:input type="hidden" path="password" />
			</div>
			<button type="submit">Submit</button>
			<button type="button"
				onclick="window.location.href='<spring:url value="/todo" />'">Cancel</button>
			<a href="todo">Todo</a>
		</form:form>
	</div>
	<br><div>${message}</div>
	<script src="<spring:url value='/resources/js/app.js' />"></script>
</body>
</html>