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
<title>Login</title>
</head>
<body>
	<div class="text-center" style="padding: 50px 0">
		<div class="logo">register</div>
		<!-- Main Form -->
		<div class="login-form-1">
			<form id="register-form" class="text-left" action="sign-up" method="post">
				<div class="login-form-main-message"></div>
				<div class="main-login-form">
					<div class="login-group">
						<div class="form-group">
							<label for="reg_username" class="sr-only">Email address</label>
							<input type="text" class="form-control" name="email"
								placeholder="username" />
						</div>
						<div class="form-group">
							<label for="reg_password" class="sr-only">Password</label> <input
								type="password" class="form-control" name="pass"
								placeholder="password">
						</div>
						<div class="form-group">
							<label for="reg_password_confirm" class="sr-only">Password
								Confirm</label> <input type="password" class="form-control"
								name="re_pass" placeholder="confirm password">
						</div>
					</div>
					<button type="submit" class="login-button">
						<i class="fa fa-chevron-right">Go</i>
					</button>
				</div>
				<div class="etc-login-form">
					<p>
						already have an account? <a href="#">login here</a>
					</p>
				</div>
			</form>
		</div>
		<!-- end:Main Form -->
	</div>

	<br>
	<div>${message}</div>
	<script src="<spring:url value='/resources/js/app.js' />"></script>
</body>
</html>