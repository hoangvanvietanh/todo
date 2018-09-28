<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/font-awesome.min.css'/>">
<link href='http://fonts.googleapis.com/css?family=Varela+Round'
	rel='stylesheet' type='text/css'>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.13.1/jquery.validate.min.js"></script>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<style type="text/css">
#mydiv {
	position: fixed;
	top: 10%;
	left: 35%;
}
</style>
<title>Login</title>
</head>
<body>
	<div id="mydiv" class="text-center" style="padding: 50px 0">
		<div class="logo">Login</div>
		<!-- Main Form -->
		<div class="login-form-1">
			<c:url value="/handleLogin" var="loginUrl" />
			<form id="login-form" class="text-left" action="${loginUrl}"
				method="post">
				<div class="login-form-main-message"></div>
				<div class="main-login-form">
					<div class="login-group">
						<div class="form-group">
							<label for="email" class="sr-only">Email</label><br> <input
								type="text" class="form-control" placeholder="email"
								name="email" />
						</div>
						<div class="form-group">
							<label for="lg_password" class="sr-only">Password</label> <input
								type="password" name="password" class="form-control"
								placeholder="password" />
						</div>
						<div class="form-group login-group-checkbox">
							<input type="checkbox" id="lg_remember" name="lg_remember">
							<label for="lg_remember">remember</label> <input type="hidden"
								name="${_csrf.parameterName}" value="${_csrf.token}" />
						</div>
					</div>

					<button type="submit" class="login-button">
						<i class="fa fa-chevron-right">Go</i>
					</button>
				</div>
				<div class="etc-login-form">
					<p>
						Forgot your password? <a href="#">click here</a>
					</p>
					<p>
						New user? <a href="<spring:url value='/sign-up'/>">create new
							account</a>
					</p>
				</div>
			</form>
		</div>
		<!-- end:Main Form -->
	</div>

	<br>
	<div>${message}</div>
	<script
		src="<spring:url value='/resources/js/jquery.validate.min.js' />"></script>
</body>
</html>