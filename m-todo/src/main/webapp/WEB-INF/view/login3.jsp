<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact Management</title>
</head>
<body>
	<h1>Login</h1>
	<c:url value="/handleLogin" var="loginUrl"/>
	<form action="${loginUrl}" method="post">       
		<c:if test="${param.error != null}">        
			<p>
				Invalid username and password.
			</p>
		</c:if>
		<c:if test="${param.logout != null}">       
			<p>
				You have been logged out.
			</p>
		</c:if>
		<p>
			<label for="email">Email</label>
			<input type="text" name="email"/>	
		</p>
		<p>
			<label for="password">Password</label>
			<input type="password" name="password"/>	
		</p>
		<input type="hidden"                        
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
		<button type="submit" class="btn">Log in</button>
	</form>
</body>
</html>