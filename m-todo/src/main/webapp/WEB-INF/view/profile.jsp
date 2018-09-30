<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="en" class="no-js">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="<spring:url value='https://fonts.googleapis.com/css?family=Open+Sans:300,400,700'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/reset.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/style.css'/>">
<script type="text/javascript"
	src="<spring:url value='/resources/js/modernizr.js' />"></script>

<script type="text/javascript"
	src="<spring:url value='/resources/js/jquery-2.1.4.js'/>"></script>
<script type="text/javascript"
	src="<spring:url value='/resources/js/jquery.menu-aim.js'/>"></script>
<script src="<spring:url value='/resources/js/main.js'/>"
	type="text/javascript"></script>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/profile.css'/>">
<style type="text/css">
.friend {
	float: right;
	width: 200px;
	height: 500px;
	background: rgba(192,192,192,0.3);
	padding-top: 15px;
	overflow: auto;
	
}

}
.top {
	height: 50px;
	background: #2c3136;
}

.tdiv {
	display: table;
}

.timg {
	vertical-align: middle;
	display: table-cell;
}

.tspan {
	vertical-align: middle;
	display: table-cell;
}

.test {
	border: none;
	background-color: transparent;
}

body {
	background-image: url("resources/img/city.jpg");
	background-repeat: no-repeat;
}
</style>
<title>Vietanh</title>
</head>
<body>
	<header class="cd-main-header">
		<a href="#0" class="cd-logo"><img
			style="width: 199px; height: 53px" src="resources/img/bestcoder.gif"
			alt="Logo"></a>

		<div class="cd-search is-hidden">
			<form action="#0">
				<input type="search" placeholder="Search...">
			</form>
		</div>
		<!-- cd-search -->

		<a href="#0" class="cd-nav-trigger">Menu<span></span></a>

		<nav class="cd-nav">
			<ul class="cd-top-nav">
				<li class="has-children account"><a href="#0"><img
						style="width: 25px; height: 25px" alt="Avatar"
						src="<spring:url value='/user/avatar/${user.email}'/>">
						Account </a>

					<ul>

						<li><a href="#0">My Account</a></li>
						<li><a href="#0">Edit Account</a></li>
						<li><a href="logout">Logout</a></li>
					</ul></li>
			</ul>
		</nav>
	</header>

	<div class="cd-main-content">
		<nav class="cd-side-nav">
			<ul>
				<li class="cd-label">Main</li>
				<li class="has-children overview"><a href="user">Profile</a></li>
				<li class="has-children notifications "><a href="#0">Notifications<span
						class="count">3</span></a>

					<ul>
						<li><a href="todo">Todo</a></li>
						<li><a href="friends">Friends</a></li>
						<li><a href="messages">Messenges</a></li>
					</ul></li>

				<li class="has-children comments"><a href="#0">Comments</a>

					<ul>
						<li><a href="#0">All Comments</a></li>
						<li><a href="#0">Edit Comment</a></li>
						<li><a href="#0">Delete Comment</a></li>
					</ul></li>
			</ul>

			<ul>
				<li class="cd-label">Secondary</li>
				<li class="has-children bookmarks"><a href="#0">Bookmarks</a>

					<ul>
						<li><a href="#0">All Bookmarks</a></li>
						<li><a href="#0">Edit Bookmark</a></li>
						<li><a href="#0">Import Bookmark</a></li>
					</ul></li>
				<li class="has-children images"><a href="#0">Images</a>

					<ul>
						<li><a href="#0">All Images</a></li>
						<li><a href="#0">Edit Image</a></li>
					</ul></li>

				<li class="has-children users"><a href="#0">My team</a>

					<ul>
						<li><a href="#0">All Memeber</a></li>
						<li><a href="#0">Edit Memeber</a></li>
						<li><a href="#0">Add Memeber</a></li>
					</ul></li>
			</ul>

			<ul>
				<li class="cd-label">Action</li>
				<li class="action-btn"><a href="#0">+ Button</a></li>
			</ul>
		</nav>

		<div class="content-wrapper">
			<div class="center">
				<form:form id="form" modelAttribute="user" action="user/update"
					method="post" enctype="multipart/form-data">
					<div class="row">
						<div class="profile-picture" style="float: left">

							<img style="width: 290px; height: 290px" alt="Avatar"
								src="<spring:url value='/user/avatar/${user.email}'/>">
							<div>
								<input type="file" name="file" id="file" value="change picture"
									style="display: none" /> <label for="file">Click here
									Change picture</label>
							</div>
						</div>

						<div style="float: left; padding-left: 100px">
							<h4 class="blue">
								<span style="padding-left: 50px">${user.name}</span>
								<button class="button" type="submit">{Save}</button>
							</h4>

							<div class="profile-user-info">
								<div class="profile-info-row">
									<div class="profile-info-name">Username</div>

									<div class="profile-info-value">
										<span><form:input class="test" path="name" /></span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">Email</div>

									<div class="profile-info-value">
										<span><form:input class="test" path="email" /></span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">Phone</div>

									<div class="profile-info-value">
										<span><form:input class="test" path="phone" /></span>
									</div>
								</div>
								<div class="profile-info-row">
									<div class="profile-info-name">Address</div>

									<div class="profile-info-value">
										<span><form:textarea class="test" path="address"
												rows="3" /></span>
									</div>
								</div>

								<div class="profile-info-row">
									<div class="profile-info-name">Birthday</div>

									<div class="profile-info-value">
										<span><form:input class="test" path="birthDate" /></span>
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
					</div>
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
				</form:form>
			</div>
		</div>
	</div>
	<div class="friend">
		<p style="font-weight: bold;" align="center">List friends</p>

		<div class="tdiv">
			<button class="button" type="button"
				onclick="window.location.href='<spring:url value="/user" />'">
				<span class="tspan"> <img class="timag"
					style="width: 25px; height: 25px; border-radius: 50%; margin-right: 5px;"
					alt="Avatar" src="<spring:url value='/user/avatar/${user.email}'/>">
				</span> <span class="tspan" style="font-weight: bold;">${user.name}</span>
			</button>
		</div>
		<c:forEach var="friend" items="${friend}">
			<form action="user" method="post">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
				<div class="tdiv">
					<button class="button" style="padding-left: 6px;" type="submit"
						name="idFriends" value="${friend.user2.id}">
						<span class="tspan"> <img class="timag"
							style="width: 25px; height: 25px; border-radius: 50%; margin-right: 5px;"
							alt="Avatar"
							src="<spring:url value='/user/avatar/${friend.user2.email}'/>">
						</span> <span class="tspan">${friend.user2.name}</span>
					</button>
				</div>
			</form>
			<div></div>
		</c:forEach>
	</div>

	<script type="text/javascript">
		document.getElementById("file").onchange = function() {
			document.getElementById("form").submit();
		};
	</script>
</body>
</html>