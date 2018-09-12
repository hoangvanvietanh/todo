<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="org.springframework.web.servlet.ModelAndView"%>
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
		<form:form modelAttribute="todo">
			<form:errors path="*" cssClass="error-box" />
			<div class="input-group">
				<label>Name</label>
				<form:input path="name" readonly="true" />
			</div>

			<div class="input-group">
				<label>Start Date</label>
				<form:input path="startDate" type="date" readonly="true" />
			</div>

			<div class="input-group">
				<label>Status</label>
				<form:input path="status" readonly="true" />
			</div>
			<c:if test="${not empty c.startedAt}">
				<div class="input-group">
					<label>Started At</label>
					<form:input path="startedAt" readonly="true"/>
				</div>
			</c:if>
			<c:if test="${not empty c.endedAt}">
				<div class="input-group">
					<label>Ended At</label>
					<form:input path="endedAt" readonly="true"/>
				</div>
			</c:if>
			<div class="input-group">
				<label>Created At</label>
				<form:input path="createdAt" type="date" readonly="true" />
			</div>

			<div class="input-group">
				<label>Description</label>
				<form:textarea path="description" rows="3" readonly="true" />
			</div>

			<div class="input-group">
				<label>Note</label>
				<form:input path="note" />
			</div>
		</form:form>
		
		<form:form modelAttribute="todo" action="update" method="get">
			<form:input path="id" name="id" type="hidden" />
			<input type="hidden">
			<c:if test="${c.status eq 'New1'}">
				<td><button class="input" type="submit" name="action"
						value="edit">Edit</button></td>
			</c:if>
			<td><button class="input" type="submit" name="action"
					value="delete">Delete</button></td>
			<button type="button"
				onclick="window.location.href='<spring:url value="/todo" />'">Cancel</button>
		</form:form>
	</div>

	<script src="<spring:url value='/resources/js/app.js' />"></script>
</body>
</html>