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
<title>Todo</title>
<link rel="stylesheet"
	href="<spring:url value='/resources/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<spring:url value='/resources/css/home.css'/>">
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
			<form action="todo">
				<div style="margin-top: 10px">
					<div class="b">Name</div>
					<input type="hidden" name="id" value="${id}">
					<div class="b">
						<input type="text" name="name">
						<button class="input" type="submit">Search</button>
						<button class="input" type="button"
							onclick="window.location.href='<spring:url value="/todo/create" />'">Create</button>
					</div>
				</div>
			</form>
			<form:errors path="*" cssClass="error-box" />


			<table class="table">
				<thead>
					<tr>
						<th style="width: 1%">No.</th>
						<th style="width: 15%">Name</th>
						<th style="width: 10%">Status</th>
						<th style="width: 10%">Start Date</th>
						<th style="width: 10%">Started At</th>
						<th style="width: 10%">Ended At</th>
						<th style="width: 15%">Actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="todo" items="${todo}">
						<tr class="${todo.status}">
							<td>${i=i+1}</td>
							<td>${todo.name}</td>
							<td>${todo.status}</td>
							<td>${todo.startDate}</td>
							<td>${todo.startedAt}</td>
							<td>${todo.endedAt}</td>
							<td><c:if test="${todo.status eq 'New2' }">
									<form action="todo/start" method="post">
										<input type="hidden" name="id" value="${todo.id}"> <input
											type="hidden" name="number" value="${page3}"> <input
											type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
										<div class="b">
											<button class="input" type="submit">Start</button>
										</div>
									</form>
								</c:if> <c:if test="${todo.status eq 'New2'}">
									<form action="todo/cancel" method="post">
										<input type="hidden" name="id" value="${todo.id}"> <input
											type="hidden" name="number" value="${page3}"> <input
											type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
										<div class="b">
											<button class="input" type="submit">Cancel</button>
										</div>
									</form>
								</c:if> <c:if test="${todo.status eq 'In-progress' }">
									<form action="todo/end" method="post">
										<input type="hidden" name="id" value="${todo.id}"> <input
											type="hidden" name="number" value="${page3}"> <input
											type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
										<div class="b">
											<button class="input" type="submit">End</button>
										</div>
									</form>
								</c:if> <c:if
									test="${todo.status eq 'New1' or todo.status eq 'New2' or todo.status eq 'In-progress' or todo.status eq 'Done'or todo.status eq 'Canceled'}">
									<form action="todo/view" method="get">
										<input type="hidden" name="id" value="${todo.id}"> <input
											type="hidden" name="number" value="${page3}"> <input
											type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
										<div class="b">
											<button class="input" type="submit">View</button>
										</div>
									</form>
								</c:if> <c:if test="${todo.status eq 'New1'}">
									<form action="todo/edit" method="get">
										<input type="hidden" name="id" value="${todo.id}"> <input
											type="hidden" name="number" value="${page3}"> <input
											type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
										<div class="b">
											<button class="input" type="submit">Edit</button>
										</div>
									</form>
								</c:if> <c:if
									test="${todo.status eq 'New1' or todo.status eq 'New2' or todo.status eq 'Canceled'}">
									<form action="todo/delete" method="post">
										<input type="hidden" name="id" value="${todo.id}"> <input
											type="hidden" name="number" value="${page3}"> <input
											type="hidden" name="${_csrf.parameterName}"
											value="${_csrf.token}" />
										<div class="b">
											<button class="input" type="submit">Delete</button>
										</div>
									</form>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="center">
				<c:if test="${page2 gt 1}">
					<form action="todo">
						<input type="hidden" name="id" value="${id}"> <input
							type="hidden" name="numberPage" value="1">
						<div class="b">
							<button type="submit" class="input">|<</button>
						</div>
					</form>
					<form action="todo">
						<input type="hidden" name="id" value="${id}"> <input
							type="hidden" name="numberPage" value="${page2=page2-1}">
						<div class="b">
							<button type="submit" class="input"><</button>
						</div>
					</form>
				</c:if>
				<div class="b">
					<button class="input">${page3}</button>
				</div>
				<c:if test="${page lt num2}">
					<form action="todo">
						<input type="hidden" name="id" value="${id}"> <input
							type="hidden" name="numberPage" value="${page=page+1}">
						<div class="b">
							<button class="input" type="submit">></button>
						</div>
					</form>
					<form action="todo">
						<input type="hidden" name="id" value="${id}"> <input
							type="hidden" name="numberPage" value="${num}">
						<div class="b">
							<button class="input" type="submit">>|</button>
						</div>
					</form>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>