<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/profile.css'/>">
<style type="text/css">
.center {
	position: absolute;
	top: 50%;
	left: 50%;
	margin-right: -50%;
	transform: translate(-50%, -50%)
}
</style>
<title>Insert title here</title>
</head>
<body>
	<div class="center">
		<form:form modelAttribute="user" action="user/update" method="post"
			enctype="multipart/form-data">
			<div class="row">
				<div style="float: left; padding-top: 30px">
					<span class="profile-picture"> <img style="width: 300px;height: 300px" alt="Avatar"
						src="<spring:url value='/user/avatar/${user.email}'/>">

					</span> <input type="file" name="file" />
				</div>
				<!-- /.col -->

				<div style="float: left; padding-left: 100px">
					<h4 class="blue">
						<span style="padding-left: 50px">${user.name}</span>
					</h4>

					<div class="profile-user-info">
						<div class="profile-info-row">
							<div class="profile-info-name">Username</div>

							<div class="profile-info-value">
								<span><form:input path="name" /></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">Email</div>

							<div class="profile-info-value">
								<span><form:input path="email" /></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">Phone</div>

							<div class="profile-info-value">
								<span><form:input path="phone" /></span>
							</div>
						</div>
						<div class="profile-info-row">
							<div class="profile-info-name">Address</div>

							<div class="profile-info-value">
								<span><form:textarea path="address" rows="3" /></span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">Birthday</div>

							<div class="profile-info-value">
								<span><form:input path="birthDate" /></span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">Joined</div>

							<div class="profile-info-value">
								<span>2010/06/20</span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">Last Online</div>

							<div class="profile-info-value">
								<span>3 hours ago</span>
							</div>
						</div>
					</div>

					<div class="hr hr-8 dotted"></div>

					<div class="profile-user-info">
						<div class="profile-info-row">
							<div class="profile-info-name">Website</div>

							<div class="profile-info-value">
								<a href="#" target="_blank">www.alexdoe.com</a>
							</div>
						</div>
					</div>
				</div>
				<!-- /.col -->
			</div>
			<!-- /.row -->

			<div class="space-20"></div>

			<div style="float: left">
				<div class="">
					<h4 class="">Little About Me</h4>
				</div>

				<div class="">
					<div class="">
						<p>My job is mostly lorem ipsuming and dolor sit ameting as
							long as consectetur adipiscing elit.</p>
					</div>
				</div>
			</div>
			<form:input type="hidden" path="id" />
			<form:input type="hidden" path="password" />
			<button type="submit">Submit</button>
			<button type="button"
				onclick="window.location.href='<spring:url value="/todo" />'">Cancel</button>
			<a href="todo">Todo</a>
		</form:form>
	</div>
</body>
</html>