<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
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
<title>Insert title here</title>
<style type="text/css">
.center {
	position: absolute;
	top: 50%;
	left: 50%;
	width: 800px;
	height: 300px;
	margin-right: -50%;
	transform: translate(-50%, -50%);
	background-color: lightblue;
	height: 300px;
}

.scroll {
	width: 100%;
	height: 475px;
	background-color: #bfbfbf;
	overflow: auto;
}

.button {
	border: none;
	background: none;
}

.friend {
	float: right;
	width: 200px;
	height: 500px;
	background: rgba(192,192,192,0.3);
	padding-top: 15px;
	overflow: auto;
	
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
::-webkit-scrollbar {
    width: 5px;
}
::-webkit-scrollbar-track {
    background: #f1f1f1; 
}
 
/* Handle */
::-webkit-scrollbar-thumb {
    background: #888; 
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
    background: #555; 
}
</style>
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
			<form action="messages" method="post"
				style="margin: 48px 146px -73% -6%;">
				<div class="scroll" id="test">
					<c:forEach var="mes" items="${mes}">
						<c:if test="${mes.user1.name eq name}">
							
								<p style="text-align: right;margin: 10px 10px 10px 10px">${mes.messages}</p>
						
						</c:if>
						<c:if test="${mes.user2.name eq name}">
						<div style="margin: 10px 10px 10px 10px">
							<span class="tspan"> <img class="timag"
								style="width: 25px; height: 25px; border-radius: 50%; margin-right: 5px;"
								alt="Avatar"
								src="<spring:url value='/user/avatar/${mes.user1.email}'/>">
							</span>
							<span class="tspan">${mes.messages}</span>
							<br></div>
						</c:if>
						<input type="hidden" name="id2" value="${mes.user2.id}">
					</c:forEach>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</div>
				<div style="width: 100%">
				<input type="text" style="width: 94%;" name="mess">
				<button type="submit" style="width: 5%;">Go</button>
				</div>
			</form>
		</div>
		<div class="friend">
			<p style="font-weight: bold;" align="center">List friends</p>

			<div class="tdiv">
				<button class="button" type="button"
					onclick="window.location.href='<spring:url value="/user" />'">
					<span class="tspan"> <img class="timag"
						style="width: 25px; height: 25px; border-radius: 50%; margin-right: 5px;"
						alt="Avatar"
						src="<spring:url value='/user/avatar/${user.email}'/>">
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
	</div>
	<script src="https://code.jquery.com/jquery-2.1.1.min.js"
		type="text/javascript"></script>
	<script>
		$(document).ready(function() {
			setInterval(function() {
				$("#screen").load('messages').fadeIn('slow');
			}, 20000);
		});
	</script>
</body>
</html>