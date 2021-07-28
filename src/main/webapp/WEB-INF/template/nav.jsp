<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark">
	<a class="navbar-brand" href="/">
		<img src="${pageContext.request.contextPath}/assets/images/logo.png" />
	</a>
	<button class="navbar-toggler" data-toggle="collapse" data-target="#navbar-menu">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div id="navbar-menu" class="navbar-collapse collapse">
		<ul class="navbar-nav">
			<c:if test="${sessionScope.userId == null}">
				<li class="nav-item">
					<a class="nav-link" href="/auth/signin">Sign In</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/auth/signup">Sign Up</a>
				</li>
			</c:if>
			<c:if test="${sessionScope.userId != null}">
				<li class="nav-item">
					<a class="nav-link" href="/topics">Topics</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/ranking">Ranking</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="/auth/logout">Logout</a>
				</li>
			</c:if>
		</ul>
	</div>
</nav>