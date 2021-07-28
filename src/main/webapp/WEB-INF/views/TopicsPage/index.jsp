<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
	<div class="header">
		<h1 class="container">Topics</h1>
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
					onclick="window.location.href='/topics/new'"
				>
					New
				</button>
				<button 
					class="btn outline" 
					onclick="window.location.href='/ranking'"
				>
					Ranking
				</button>
			</div>
			<div class="table-responsive">
				<table class="table table-hover table-striped table-bordered">
					<thead>
						<tr>
							<th>Title</th>
							<th>Author</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="topic" items="${topics}">
							<tr>
								<td>
									<a href="/topics/open/${topic.id}">${topic.title}</a>
								</td>
								<td>${topic.author.name}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>
