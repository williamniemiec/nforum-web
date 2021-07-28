<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div class="header">
		<h1 class="container">Ranking</h1>
	</div>
	<div class="container content">
		<c:if test="${error != null}">
			<div class="alert alert-danger" role="alert">
				<button 
					class="close" 
					data-dismiss="alert" 
					aria-label="close"
				>
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="alert-heading">Error!</h4>
				<p>${error}</p>
			</div>
		</c:if>
		<div class="panel">
			<div class="actions">
				<button 
					class="btn outline" 
					onclick="window.location.href='/topics'"
				>
					Topics
				</button>
			</div>
			<div class="table-responsive">
				<table class="table table-hover table-striped table-bordered">
					<thead>
						<tr>
							<th>Position</th>
							<th>Name</th>
							<th>Login</th>
							<th>Points</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="user" items="${ranking}" varStatus="loop">
							<tr>
								<td>${loop.count}</td>
								<td>${user.name}</td>
								<td>${user.email}</td>
								<td>${user.points}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
