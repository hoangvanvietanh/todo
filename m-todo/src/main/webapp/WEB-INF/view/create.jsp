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
<title>Create</title>

</head>
<body>
	<div class="center">
		<form:form modelAttribute="todo">
			<form:errors path="*" cssClass="error-box" />
			<div class="input-group">
				<label>Name</label>
				<form:input path="name" />
			</div>
			<div class="input-group">
				<label>Description</label>
				<form:textarea path="description" rows="3" />
			</div>
			<div class="input-group">
				<label>Start Date</label>
				<form:input path="startDate" type="date" />
			</div>
			<form:button name="action" value="save">Save</form:button>
			<button type="button"
				onclick="window.location.href='<spring:url value="/todo" />'">Cancel</button>
		</form:form>
	</div>

	<script src="<spring:url value='/resources/js/app.js' />"></script>
</body>
</html>